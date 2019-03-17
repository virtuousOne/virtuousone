package com.demo.virtuousone.rabbitmq.sender;

import com.demo.virtuousone.dto.OrderInfo;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: 吴宸煊
 * Date: Created in  2019/3/17/0017
 * Description:
 */
@Service
public class Sender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(int i) {
        String context = "VirtuousOne  spring boot rabbitmq project " + i;

        amqpTemplate.convertAndSend("hello", context);
    }

    public void sendDocker() {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setAge(500);
        orderInfo.setCreateTime(new Date());
        orderInfo.setUserName("VirtuousOne");
        amqpTemplate.convertAndSend("order", orderInfo);

    }

}
