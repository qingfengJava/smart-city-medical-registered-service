package com.qingfeng.cmn.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/4/15
 */
@FeignClient("service-cmn")
@Component
public interface DictFeignClient {

    /**
     * 根据dictcode和value查询数据字典等级名称
     * @param dictCode
     * @param value
     * @return
     */
    @GetMapping("/admin/cmn/dict/getName/{dictCode}/{value}")
    public String getName(@PathVariable("dictCode") String dictCode,
                          @PathVariable("value") String value);

    /**
     * 根据value查询数据字典等级名称
     * @param value
     * @return
     */
    @GetMapping("/admin/cmn/dict/getName/{value}")
    public String getName(@PathVariable("value") String value);
}
