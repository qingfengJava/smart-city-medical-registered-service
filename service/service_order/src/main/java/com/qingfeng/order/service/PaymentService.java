package com.qingfeng.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingfeng.model.model.order.OrderInfo;
import com.qingfeng.model.model.order.PaymentInfo;

import java.util.Map;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/7/26
 */
public interface PaymentService extends IService<PaymentInfo> {

    /**
     * 保存交易记录
     * @param order
     * @param paymentType 支付类型（1：微信 2：支付宝）
     */
    void savePaymentInfo(OrderInfo order, Integer paymentType);

    /**
     * 支付成功
     * @param out_trade_no
     * @param status
     * @param resultMap
     */
    void paySuccess(String out_trade_no, Integer status, Map<String, String> resultMap);
}

