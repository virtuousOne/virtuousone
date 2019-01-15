package com.demo.virtuousone.controller;

import com.demo.virtuousone.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 吴宸煊
 * Date: Created in  2019/1/13/0013
 * Description:  邮件发送模拟controller层
 */
@RestController
public class EmailController {
    private static Logger logger = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    private EmailService emailService;

    @RequestMapping("mail")
    public Boolean sendMail() {
        return emailService.sendSimpleEmial("806440556@qq.com", "virtuousOne", "测试发送邮件是否成功，请勿在意！！！virtuousOne");
    }
}
