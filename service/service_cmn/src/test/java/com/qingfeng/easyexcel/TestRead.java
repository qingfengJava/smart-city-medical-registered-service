package com.qingfeng.easyexcel;

import com.alibaba.excel.EasyExcel;

/**
 * 测试excel读数据
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/4/6
 */
public class TestRead {

    public static void main(String[] args) {
        //读取文件路径
        String fileName = "D:\\1.xlsx";
        //调用方法实现读取操作
        EasyExcel.read(fileName,UserData.class,new ExcelListener()).sheet().doRead();
    }
}
