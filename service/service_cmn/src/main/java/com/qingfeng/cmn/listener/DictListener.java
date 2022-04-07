package com.qingfeng.cmn.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.qingfeng.cmn.mapper.DictMapper;
import com.qingfeng.model.model.cmn.Dict;
import com.qingfeng.model.vo.cmn.DictEeVo;
import org.springframework.beans.BeanUtils;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/4/7
 */
public class DictListener extends AnalysisEventListener<DictEeVo>{

    private DictMapper dictMapper;

    public DictListener(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }

    /**
     * 从第二行开始 一行一行的读取
     * @param dictEeVo
     * @param analysisContext
     */
    @Override
    public void invoke(DictEeVo dictEeVo, AnalysisContext analysisContext) {
        //调用方法添加到数据库
        Dict dict = new Dict();
        BeanUtils.copyProperties(dictEeVo,dict);
        dictMapper.insert(dict);
    }

    /**
     * 读取结束之后执行的方法
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
