package com.qingfeng.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingfeng.model.model.order.PaymentInfo;
import com.qingfeng.model.model.order.RefundInfo;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/7/27
 */
public interface RefundInfoService extends IService<RefundInfo> {

    /**
     * 保存退款记录
     *
     * @param paymentInfo
     * @return
     */
    RefundInfo saveRefundInfo(PaymentInfo paymentInfo);
}

