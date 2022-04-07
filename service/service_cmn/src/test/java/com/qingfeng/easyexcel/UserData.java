package com.qingfeng.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 测试的实体类
 *
 * 在对应属性上添加注解，设置表头内容
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/4/6
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserData {

    /**
     * value： 表示名称
     * index: 表示第几列  从0开始
     */
    @ExcelProperty(value = "用户编号",index = 0)
    private int uid;

    @ExcelProperty(value = "用户名称",index = 1)
    private String username;
}
