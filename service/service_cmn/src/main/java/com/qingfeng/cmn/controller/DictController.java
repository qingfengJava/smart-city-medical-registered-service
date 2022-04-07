package com.qingfeng.cmn.controller;

import com.qingfeng.cmn.service.DictService;
import com.qingfeng.model.model.cmn.Dict;
import com.qingfeng.smart.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 数据字典的控制层
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/4/5
 */
@RestController
@Api(value = "提供数据字典相关的接口方法",tags = "数据字典接口")
@RequestMapping("/admin/cmn/dict")
@CrossOrigin
public class DictController {

    @Autowired
    private DictService dictService;

    @ApiOperation("根据数据Id查询子数据列表")
    @GetMapping("/findChildData/{id}")
    public Result findChildData(@PathVariable("id") Long id){
        List<Dict> list = dictService.findChildData(id);
        return Result.ok(list);
    }

    /**
     * 导出实际上就是下载操作，从用户角度考虑，用户可以指定路径和名字
     * @param response
     * @return
     */
    @ApiOperation("导出数据字典接口")
    @GetMapping("exportData")
    public void exportDict(HttpServletResponse response){
        try {
            dictService.exportDictData(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation("导入数据字典接口")
    @PostMapping("importData")
    public Result importData(MultipartFile file){
        dictService.importDictData(file);
        return Result.ok();
    }

}
