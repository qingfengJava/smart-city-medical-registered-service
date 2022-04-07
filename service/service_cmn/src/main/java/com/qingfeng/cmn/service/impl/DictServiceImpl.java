package com.qingfeng.cmn.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingfeng.cmn.listener.DictListener;
import com.qingfeng.cmn.mapper.DictMapper;
import com.qingfeng.cmn.service.DictService;
import com.qingfeng.model.model.cmn.Dict;
import com.qingfeng.model.vo.cmn.DictEeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据字典的业务层接口实现
 *
 * ServiceImpl<DictMapper, Dict> ：MP提供的业务层接口：对应的泛型是相应的接口和实体
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/4/5
 */
@Service
public class DictServiceImpl  extends ServiceImpl<DictMapper, Dict> implements DictService {
    //注意： 使用ServiceImpl之后，MP已经自动帮助我们注入好了对应的持久层接口（dao/mapper）

    /**
     * 根据数据Id查询子数据列表
     * @param id
     * @return
     */
    @Override
    public List<Dict> findChildData(Long id) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", id);
        //因为是继承自baseMapper，所以可以直接使用
        List<Dict> dictList = baseMapper.selectList(queryWrapper);
        for (Dict dict : dictList) {
            Long dictId = dict.getId();
            boolean isChild = this.isChildren(dictId);
            dict.setHasChildren(isChild);
        }
        return dictList;
    }

    /**
     * 导出数据字典
     * @param response
     */
    @Override
    public void exportDictData(HttpServletResponse response) throws IOException {
        // 设置为Excel的类型
        response.setContentType("application/vnd.ms-excel");
        // 设置编码
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("数据字典", "UTF-8");
        /**
         * Content-disposition: 表示以下载方式打开文件
         */
        response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xlsx");

        // 查询数据库
        List<Dict> dictList = baseMapper.selectList(null);
        // 将Dict对象转换指定的DictEeVO对象
        List<DictEeVo> dictVoList = new ArrayList<>(dictList.size());
        for(Dict dict : dictList) {
            DictEeVo dictVo = new DictEeVo();
            BeanUtils.copyProperties(dict, dictVo);
            dictVoList.add(dictVo);
        }

        //用Excel导出数据
        EasyExcel.write(response.getOutputStream(), DictEeVo.class).sheet("数据字典").doWrite(dictVoList);
    }

    /**
     * 导入数据字典
     * @param file
     */
    @Override
    public void importDictData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), DictEeVo.class, new DictListener(baseMapper)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断id下面是否有子节点
     * @param id
     * @return
     */
    private boolean isChildren(Long id){
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",id);
        Integer count = baseMapper.selectCount(queryWrapper);
        return count > 0;
    }
}
