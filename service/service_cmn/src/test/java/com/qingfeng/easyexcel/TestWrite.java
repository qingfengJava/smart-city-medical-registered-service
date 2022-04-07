package com.qingfeng.easyexcel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;

/**
 * 测试Java读写Excel
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/4/6
 */
public class TestWrite {

    public static void main(String[] args) {
        //构建数据list集合
        ArrayList<UserData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserData userData = new UserData();
            userData.setUid(i);
            userData.setUsername("lucy"+i);

            list.add(userData);
        }

        // 设置Excel文件路径和文件名称
         String fileName = "D:\\1.xlsx";
        // 调用方法实现写操作
        EasyExcel.write(fileName,UserData.class)
                .sheet("用户信息")
                .doWrite(list);
    }
}
