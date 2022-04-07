package com.qingfeng.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellData;

import java.util.Map;

/**
 * Excel： 读数据的监听器，要监听一行一行的读取
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/4/6
 */
public class ExcelListener extends AnalysisEventListener<UserData> {

    /**
     * 一行一行读取Excel内容，从第二行读取，第一行是表头不读取
     * @param userData
     * @param analysisContext
     */
    @Override
    public void invoke(UserData userData, AnalysisContext analysisContext) {
        //直接输出每一行读到的内容
        System.out.println(userData);
    }

    /**
     * 读取Excel表头第一行的数据
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHead(Map<Integer, CellData> headMap, AnalysisContext context) {
        System.out.println("表头信息："+ headMap);
    }

    /**
     * 读取Excel结束后调用
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
