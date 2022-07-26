package com.qingfeng.order.controller;

import com.qingfeng.model.enums.PaymentTypeEnum;
import com.qingfeng.order.service.PaymentService;
import com.qingfeng.order.service.WeixinService;
import com.qingfeng.smart.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 微信支付的控制层接口
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/7/26
 */
@RestController
@Api(value="提供微信支付相关的接口功能" , tags = "微信支付")
@RequestMapping("/api/order/weixin")
public class WeixinController {

    @Autowired
    private WeixinService weixinPayService;
    @Autowired
    private PaymentService paymentService;


    @ApiOperation("下单生成二维码")
    @GetMapping("/createNative/{orderId}")
    public Result createNative(
            @ApiParam(name = "orderId", value = "订单id", required = true)
            @PathVariable("orderId") Long orderId) {
        return Result.ok(weixinPayService.createNative(orderId));
    }

    @ApiOperation(value = "查询支付状态")
    @GetMapping("/queryPayStatus/{orderId}")
    public Result queryPayStatus(
            @ApiParam(name = "orderId", value = "订单id", required = true)
            @PathVariable("orderId") Long orderId) {
        //调用微信接口实现支付状态查询
        Map<String, String> resultMap = weixinPayService.queryPayStatus(orderId, PaymentTypeEnum.WEIXIN.name());
        //出错
        if (resultMap == null) {
            return Result.fail().message("支付出错");
        }
        System.out.println("resultMap: "+resultMap);
        //如果成功
        if ("SUCCESS".equals(resultMap.get("trade_state"))) {
            System.out.println("扫码啦----------------------------");
            //更改订单状态，处理支付结果
            String out_trade_no = resultMap.get("out_trade_no");
            paymentService.paySuccess(out_trade_no, PaymentTypeEnum.WEIXIN.getStatus(), resultMap);
            System.out.println("结束啦--------------------");
            return Result.ok().message("支付成功");
        }
        return Result.ok().message("支付中");
    }


}

