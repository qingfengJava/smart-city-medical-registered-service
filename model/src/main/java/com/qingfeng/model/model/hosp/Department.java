package com.qingfeng.model.model.hosp;

import com.qingfeng.model.model.base.BaseMongoEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 科室的实体类
 *
 * @author 清风学Java
 */
@Data
@ApiModel(description = "Department")
@Document("Department")
public class Department extends BaseMongoEntity {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 普通索引
	 */
	@ApiModelProperty(value = "医院编号")
	@Indexed
	private String hoscode;

	/**
	 * 唯一索引
	 */
	@ApiModelProperty(value = "科室编号")
	@Indexed(unique = true)
	private String depcode;

	@ApiModelProperty(value = "科室名称")
	private String depname;

	@ApiModelProperty(value = "科室描述")
	private String intro;

	@ApiModelProperty(value = "大科室编号")
	private String bigcode;

	@ApiModelProperty(value = "大科室名称")
	private String bigname;

}

