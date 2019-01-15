package com.demo.virtuousone.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import com.demo.virtuousone.service.AliYunOssService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @Auther: 吴宸煊
 * @Date: 2019/1/14
 * @Description: 实现文件上传和下载的service接口
 */
@Service
public class AliYunOssServiceImpl implements AliYunOssService {

    @Value("${oss.endPoint}")
    private String endPoint;
    @Value("${oss.accessKeyId}")
    private String accessKeyId;
    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${oss.bucketName}")
    private String bucketName;

    @Override
    public boolean downloadFile(String objectName, File file) {
        OSSClient ossClient = null;
        try {
            ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
            ossClient.getObject(new GetObjectRequest(bucketName, objectName), file);
            return true;
        } catch (Exception e) {
            if (ossClient != null) {
                ossClient.shutdown();
            }

            return false;
        }
    }

    @Override
    public boolean uploadFile(String objectName, File file) {
        OSSClient ossClient = null;
        try {
            ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
            ossClient.putObject(bucketName, objectName, file);

            return true;
        } catch (Exception e) {
            if (ossClient != null) {
                ossClient.shutdown();
            }
            return false;
        }
    }
}
