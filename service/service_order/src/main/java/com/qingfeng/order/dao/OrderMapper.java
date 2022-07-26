package com.qingfeng.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qingfeng.model.model.order.OrderInfo;
import org.springframework.stereotype.Repository;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/7/24
 */
@Repository
public interface OrderMapper extends BaseMapper<OrderInfo> {
}

