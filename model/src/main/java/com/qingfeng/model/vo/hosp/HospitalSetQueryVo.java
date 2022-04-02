package com.qingfeng.model.vo.hosp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 封装医院设置条件查询的相关实体属性的Vo类
 *
 * @author 清风学Java
 */
@Data
public class HospitalSetQueryVo {

    @ApiModelProperty(value = "医院名称")
    private String hosname;

    @ApiModelProperty(value = "医院编号")
    private String hoscode;
}
