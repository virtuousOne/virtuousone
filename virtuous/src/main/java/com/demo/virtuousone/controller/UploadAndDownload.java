package com.demo.virtuousone.controller;

import com.demo.virtuousone.common.ResponseMsg;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @Author: 吴宸煊
 * Date: Created in  2019/1/20/0020
 * Description:
 */
public class UploadAndDownload {

    /**
     * 文件下载
     *
     * @param response
     * @return
     */
    public HttpServletResponse download(HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File("d:\\abc.csv");
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream("d:\\abc.csv"));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (Exception e) {

        }
        return response;
    }


    /**
     * 文件下载 推荐
     *
     * @param response
     * @param request
     * @throws Exception
     */
    @RequestMapping("download")
    public void downloadLocal(HttpServletResponse response, HttpServletRequest request) throws Exception {
        // 下载本地文件
        String fileName = "男.csv"; // 文件的默认保存名
        // 获得请求头中的User-Agent
        String agent = request.getHeader("User-Agent");
        // 根据不同的客户端进行不同的编码
        String filenameEncoder = "";
        if (agent.contains("MSIE")) {
            // IE浏览器
            filenameEncoder = URLEncoder.encode(fileName, "utf-8");
            filenameEncoder = filenameEncoder.replace("+", " ");
        } else if (agent.contains("Firefox")) {
            // 火狐浏览器
            BASE64Encoder base64Encoder = new BASE64Encoder();
            filenameEncoder = "=?utf-8?B?" + base64Encoder.encode(fileName.getBytes("utf-8")) + "?=";
        } else {
            // 其它浏览器
            filenameEncoder = URLEncoder.encode(fileName, "utf-8");
        }

        // 读到流中
        InputStream inStream = new FileInputStream("d:/男.csv");// 文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + filenameEncoder + "\"");
//        response.addHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(filenameEncoder,
// "utf-8") + "\"");
        // 循环取出流中的数据
        byte[] b = new byte[1024];
        int len;
        try {
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 实现单文件上传
     */
    @RequestMapping("fileUpload")
    public String fileUpload(@RequestParam("fileName") MultipartFile file) {
        if (file.isEmpty()) {
            return "false";
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);
        String path = "d:/test";
        File dest = new File(path + "/" + fileName);
        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            return "true";
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        }
    }

    /**
     * 实现多文件上传
     */
    @RequestMapping(value = "muschFileUpload", method = RequestMethod.POST)
    public String muschFileUpload(@RequestParam("fileName") List<MultipartFile> files) {
        if (files.isEmpty()) {
            return "false";
        }
        String path = "d:/test";
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                //return "false";
                continue;
            } else {
                String fileName = file.getOriginalFilename();
                int size = (int) file.getSize();
                System.out.println(fileName + "-->" + size);

                File dest = new File(path + "/" + fileName);
                if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                try {
                    file.transferTo(dest);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return "false";
                }
            }
        }
        return "true";
    }

    /**
     * 下载网络文件
     *
     * @param url      文件url
     * @param saveDir  文件保存路径
     * @param fileName 文件名称 注意需要带扩展名
     */
    @RequestMapping("downloadByApacheCommonIO")
    public Map downloadByApacheCommonIO(String url, String saveDir, String fileName) {
        //downloadByApacheCommonIO("https://nanhaidetianzhi.com/alipay.jpg","d:\\","支付宝.jpg");
        try {

            FileUtils.copyURLToFile(new URL(url), new File(saveDir, fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseMsg.success();
    }


}
