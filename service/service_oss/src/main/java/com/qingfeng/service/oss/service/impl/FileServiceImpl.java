package com.qingfeng.service.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.qingfeng.service.oss.service.FileService;
import com.qingfeng.service.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/7/21
 */
@Service
public class FileServiceImpl implements FileService {


    @Override
    public String upload(MultipartFile file) {
        OSS ossClient = null;
        try {
            // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
            String endpoint = ConstantPropertiesUtils.ENDPOINT;
            // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
            String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
            String accessKeySecret = ConstantPropertiesUtils.SECRECT;
            // 填写Bucket名称，例如examplebucket。
            String bucketName = ConstantPropertiesUtils.BUCKET;

            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build("https://"+endpoint, accessKeyId, accessKeySecret);

            InputStream inputStream  = file.getInputStream();

            //生成随机唯一值，使用uuid，添加到文件名称里面
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            String fileName = uuid+file.getOriginalFilename();

            //按照当前的日期，创建文件夹，上传到创建的文件夹里面
            String timeUrl = new DateTime().toString("yyyy/MM/dd");
            fileName = timeUrl +"/"+ fileName;

            // 创建PutObject请求。实现文件上传
            ossClient.putObject(bucketName, fileName, inputStream);

            if (ossClient != null){
                ossClient.shutdown();
            }

            //上传之后返回文件路径 https://medical-gh-qingfeng.oss-cn-chengdu.aliyuncs.com/
            String url = "https://"+bucketName+"."+endpoint+"/"+fileName;

            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
