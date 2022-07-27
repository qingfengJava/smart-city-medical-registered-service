package com.qingfeng.order.client;

import com.qingfeng.model.vo.order.OrderCountQueryVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/7/27
 */
@FeignClient(value = "service-order")
@Repository
public interface OrderFeignClient {

    /**
     * 获取订单统计数据
     * @param orderCountQueryVo
     * @return
     */
    @PostMapping("/api/order/orderInfo/inner/getCountMap")
    Map<String, Object> getCountMap(@RequestBody OrderCountQueryVo orderCountQueryVo);

}

