package com.qingfeng.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qingfeng.model.model.order.OrderInfo;
import com.qingfeng.model.vo.order.OrderCountQueryVo;
import com.qingfeng.model.vo.order.OrderCountVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/7/24
 */
@Repository
public interface OrderMapper extends BaseMapper<OrderInfo> {

    List<OrderCountVo> selectOrderCount(@Param("vo") OrderCountQueryVo orderCountQueryVo);
}

