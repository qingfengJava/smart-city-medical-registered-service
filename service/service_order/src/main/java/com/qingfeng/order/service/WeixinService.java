package com.qingfeng.order.service;

import java.util.Map;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/7/26
 */
public interface WeixinService {

    /**
     * 下单生成二维码
     * @param orderId
     * @return
     */
    Map createNative(Long orderId);

    /**
     * 根据订单号去微信第三方查询支付状态
     * @param orderId
     * @param name
     * @return
     */
    Map<String, String> queryPayStatus(Long orderId, String name);

    /**
     * 退款
     * @param orderId
     * @return
     */
    Boolean refund(Long orderId);

}
