package com.demo.virtuousone.controller;

import com.demo.virtuousone.rabbitmq.sender.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 吴宸煊
 * Date: Created in  2019/3/17/0017
 * Description:
 */
@RestController
public class RabbbitMQController {
    @Autowired
    private Sender sender;

    @RequestMapping("/rabbitmq")
    public String hello() {
        for (int i = 0; i < 100; i++) {
            sender.send(i);
        }
        return "mq发送成功";
    }

    @RequestMapping("/rabbitmq/docker")
    public String docker() {
        sender.sendDocker();
        return "mq消息发送成功";
    }

}
