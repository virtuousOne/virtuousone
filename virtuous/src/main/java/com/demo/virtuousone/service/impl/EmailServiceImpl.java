package com.demo.virtuousone.service.impl;

import com.demo.virtuousone.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @Author: 吴宸煊
 * Date: Created in  2019/1/13/0013
 * Description: 邮件发送实现service接口
 */
@Service
public class EmailServiceImpl implements EmailService {
    private static Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender sender;

    @Value("${mail.fromMail.addr}")
    private String from;

    @Override
    public Boolean sendSimpleEmial(String to, String subject, String content) {
        // 创建简单邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        try {
            // 设置自定义发件人昵称
            String nick = javax.mail.internet.MimeUtility.encodeText("virtuousOne");
            message.setFrom(String.valueOf(new InternetAddress(nick + "<" + from + ">")));
            // 设置发送人
          /*   测试群发邮件
           String [] adds ={"1021492971@qq.com","745034066@qq.com","806440556@qq.com","1395813087@qq.com","3426000683@qq.com"};
            message.setTo(adds);*/
            message.setTo(to);
            // 设置主题
            message.setSubject(subject);
            // 设置内容
            message.setText(content);
            // 执行发送邮件
            sender.send(message);
            return true;
        } catch (Exception e) {
            logger.info("邮件发送异常:" + e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean sendHtmlEmail(String to, String subject, String content) {
        // 创建一个 MINE消息
        MimeMessage message = sender.createMimeMessage();
        try {
            // true 表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            // 设置自定义发件人昵称
            String nick = javax.mail.internet.MimeUtility.encodeText("virtuousOne");
            logger.info("中文打印:" + from);
            message.setFrom(String.valueOf(new InternetAddress(nick + "<" + from + ">")));
            // helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            sender.send(message);
            return true;
        } catch (Exception e) {
            logger.info("邮件发送异常:" + e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean sendAttachmentsEmail(String to, String subject, String content, String filePath) {
        // 创建一个MINE消息
        MimeMessage message = sender.createMimeMessage();
        try {
            // true 表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            // 设置自定义发件人昵称
            String nick = javax.mail.internet.MimeUtility.encodeText("virtuousOne");
            message.setFileName(String.valueOf(new InternetAddress(nick + "<" + from + ">")));
            // helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            // true 表示这个邮件是有附件的
            helper.setText(content, true);
            // 创建文件系统资源
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            // 添加附件
            helper.addAttachment(fileName, file);
            sender.send(message);
            return true;
        } catch (Exception e) {
            logger.info("邮件发送异常:" + e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean sendInlineResourceEmail(String to, String subject, String conten, String rscPath, String rscId) {
        // 创建一个MINE消息
        MimeMessage message = sender.createMimeMessage();
        try {
            // true 表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            // 设置自定义发件人的昵称
            String nick = javax.mail.internet.MimeUtility.encodeText("virtuousOne");
            message.setFrom(String.valueOf(new InternetAddress(nick + "<" + from + ">")));
            // helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(conten, true);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            // 添加内联资源，一个id对应一个资源，最终通过id来找到该资源
            // 添加多个图片可以使用多条 <img src='cid:" + rscId + "' > 和 helper.addInline(rscId, res) 来实现
            helper.addInline(rscId, res);
            sender.send(message);
            return true;
        } catch (Exception e) {
            logger.info("邮件发送异常");
            return false;
        }
    }
}
