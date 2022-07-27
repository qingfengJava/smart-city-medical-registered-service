package com.qingfeng.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingfeng.hosp.client.HospitalFeignClient;
import com.qingfeng.model.enums.OrderStatusEnum;
import com.qingfeng.model.enums.PaymentStatusEnum;
import com.qingfeng.model.model.order.OrderInfo;
import com.qingfeng.model.model.order.PaymentInfo;
import com.qingfeng.model.vo.order.SignInfoVo;
import com.qingfeng.order.dao.PaymentInfoMapper;
import com.qingfeng.order.service.OrderService;
import com.qingfeng.order.service.PaymentService;
import com.qingfeng.smart.exception.GlobalException;
import com.qingfeng.smart.helper.HttpRequestHelper;
import com.qingfeng.smart.result.ResultCodeEnum;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/7/26
 */
@Service
public class PaymentServiceImpl extends
        ServiceImpl<PaymentInfoMapper, PaymentInfo> implements PaymentService {

    @Autowired
    private OrderService orderService;
    @Autowired
    private HospitalFeignClient hospitalFeignClient;

    /**
     * 保存交易记录
     * @param orderInfo
     * @param paymentType 支付类型（1：微信 2：支付宝）
     */
    @Override
    public void savePaymentInfo(OrderInfo orderInfo, Integer paymentType) {
        QueryWrapper<PaymentInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", orderInfo.getId());
        queryWrapper.eq("payment_type", paymentType);
        Integer count = baseMapper.selectCount(queryWrapper);
        if(count >0) {
            return;
        }
        // 保存交易记录
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setCreateTime(new Date());
        paymentInfo.setOrderId(orderInfo.getId());
        paymentInfo.setPaymentType(paymentType);
        paymentInfo.setOutTradeNo(orderInfo.getOutTradeNo());
        paymentInfo.setPaymentStatus(PaymentStatusEnum.UNPAID.getStatus());
        String subject = new DateTime(orderInfo.getReserveDate()).toString("yyyy-MM-dd")+"|"+orderInfo.getHosname()+"|"+orderInfo.getDepname()+"|"+orderInfo.getTitle();
        paymentInfo.setSubject(subject);
        paymentInfo.setTotalAmount(orderInfo.getAmount());
        baseMapper.insert(paymentInfo);
    }

    /**
     * 支付成功
     * @param outTradeNo
     * @param paymentType
     * @param paramMap
     */
    @Override
    public void paySuccess(String outTradeNo, Integer paymentType, Map<String, String> paramMap) {
        //1、根据订单编号得到支付记录
        PaymentInfo paymentInfo = this.getPaymentInfo(outTradeNo, paymentType);
        System.out.println("paymentInfo: "+paymentInfo);
        if (null == paymentInfo) {
            throw new GlobalException(ResultCodeEnum.PARAM_ERROR);
        }
        if (!paymentInfo.getPaymentStatus().equals(PaymentStatusEnum.UNPAID.getStatus())) {
            return;
        }
        //2、更新支付记录信息
        PaymentInfo paymentInfoUpd = new PaymentInfo();
        paymentInfoUpd.setPaymentStatus(PaymentStatusEnum.PAID.getStatus());
        paymentInfoUpd.setTradeNo(paramMap.get("transaction_id"));
        paymentInfoUpd.setCallbackTime(new Date());
        paymentInfoUpd.setCallbackContent(paramMap.toString());
        System.out.println("paymentInfoUpd: "+paymentInfoUpd);
        this.updatePaymentInfo(outTradeNo, paymentInfoUpd);
        //3、更新订单号得到订单信息
        OrderInfo orderInfo = orderService.getById(paymentInfo.getOrderId());
        orderInfo.setOrderStatus(OrderStatusEnum.PAID.getStatus());
        System.out.println("orderInfo: "+orderInfo);
        orderService.updateById(orderInfo);
        //4、调用医院接口，通知更新支付状态
        SignInfoVo signInfoVo = hospitalFeignClient.getSignInfoVo(orderInfo.getHoscode());
        if(null == signInfoVo) {
            throw new GlobalException(ResultCodeEnum.PARAM_ERROR);
        }
        Map<String, Object> reqMap = new HashMap<>(16);
        reqMap.put("hoscode",orderInfo.getHoscode());
        reqMap.put("hosRecordId",orderInfo.getHosRecordId());
        reqMap.put("timestamp", HttpRequestHelper.getTimestamp());
        String sign = HttpRequestHelper.getSign(reqMap, signInfoVo.getSignKey());
        reqMap.put("sign", sign);
        JSONObject result = HttpRequestHelper.sendRequest(reqMap, signInfoVo.getApiUrl()+"/order/updatePayStatus");
        if(result.getInteger("code") != 200) {
            throw new GlobalException(result.getString("message"), ResultCodeEnum.FAIL.getCode());
        }

    }

    /**
     * 获取支付记录
     * @param orderId
     * @param paymentType
     * @return
     */
    @Override
    public PaymentInfo getPaymentInfo(Long orderId, Integer paymentType) {
        QueryWrapper<PaymentInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", orderId);
        queryWrapper.eq("payment_type", paymentType);
        return baseMapper.selectOne(queryWrapper);
    }

    /**
     * 获取支付记录
     * @param outTradeNo
     * @param paymentType
     * @return
     */
    private PaymentInfo getPaymentInfo(String outTradeNo, Integer paymentType) {
        QueryWrapper<PaymentInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("out_trade_no", outTradeNo);
        queryWrapper.eq("payment_type", paymentType);
        return baseMapper.selectOne(queryWrapper);
    }

    /**
     * 更改支付记录
     * @param outTradeNo
     * @param paymentInfoUpd
     */
    private void updatePaymentInfo(String outTradeNo, PaymentInfo paymentInfoUpd) {
        QueryWrapper<PaymentInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("out_trade_no", outTradeNo);
        baseMapper.update(paymentInfoUpd, queryWrapper);
    }

}

