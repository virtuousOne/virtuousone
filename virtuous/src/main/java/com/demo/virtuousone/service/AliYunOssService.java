package com.demo.virtuousone.service;

import java.io.File;

/**
 * @Auther: 吴宸煊
 * @Date: 2019/1/14
 * @Description: 文件上传和下载 --- 使用阿里云
 */
public interface AliYunOssService {
    /**
     * 下载文件
     *
     * @param objectName
     * @param file
     * @return
     */
    boolean downloadFile(String objectName, File file);

    /**
     * 上传文件
     *
     * @param objectName
     * @param file
     * @return
     */
    boolean uploadFile(String objectName, File file);
}
