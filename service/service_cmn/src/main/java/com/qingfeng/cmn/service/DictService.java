package com.qingfeng.cmn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingfeng.model.model.cmn.Dict;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 数据字典的业务层接口
 *
 * IService<Dict>：MP提供的业务层接口：对应的泛型是数据字典的实体类
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/4/1
 */
public interface DictService extends IService<Dict> {

    /**
     * 根据数据Id查询子数据列表
     * @param id
     * @return
     */
    List<Dict> findChildData(Long id);

    /**
     * 导出数据字典
     * @param response
     * @throws IOException
     */
    void exportDictData(HttpServletResponse response) throws IOException;

    /**
     * 导入数据字典
     * @param file
     */
    void importDictData(MultipartFile file);

    /**
     * 根据dictName和value查询数据字典等级名称
     * @param dictCode
     * @param value
     * @return
     */
    String getDictName(String dictCode, String value);
}
