package com.qingfeng.service.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/7/21
 */
public interface FileService {

    /**
     * 上传图片到阿里云OSS
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
