package com.qingfeng.cmn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qingfeng.model.model.cmn.Dict;
import org.springframework.stereotype.Repository;

/**
 * 数据字典的持久层接口
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/4/5
 */
@Repository
public interface DictMapper extends BaseMapper<Dict> {
}
