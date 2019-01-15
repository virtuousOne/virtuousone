package com.demo.virtuousone.service;

/**
 * @Author: 吴宸煊
 * Date: Created in  2019/1/13/0013
 * Description: 发送邮件 service接口
 */
public interface EmailService {

    /**
     * 发送简单邮件
     *
     * @param to
     * @param subject
     * @param content
     * @return
     */
    Boolean sendSimpleEmial(String to, String subject, String content);

    /**
     * 发送 html 格式邮件
     *
     * @param to
     * @param subject
     * @param content
     * @return
     */
    Boolean sendHtmlEmail(String to, String subject, String content);

    /**
     * 发送带附件的邮件
     *
     * @param to
     * @param subject
     * @param content
     * @param filePath
     * @return
     */
    Boolean sendAttachmentsEmail(String to, String subject, String content, String filePath);

    /**
     * 发送带静态资源的邮件
     *
     * @param to
     * @param subject
     * @param conten
     * @param rscPath
     * @param rscId
     * @return
     */
    Boolean sendInlineResourceEmail(String to, String subject, String conten, String rscPath, String rscId);


}
