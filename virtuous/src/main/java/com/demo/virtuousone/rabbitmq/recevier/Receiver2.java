package com.demo.virtuousone.rabbitmq.recevier;

import com.demo.virtuousone.dto.OrderInfo;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: 吴宸煊
 * Date: Created in  2019/3/17/0017
 * Description:
 */
@Component
@RabbitListener(queues = "order")
public class Receiver2 {
    @RabbitHandler
    public void receiver(OrderInfo orderInfo) {
        System.out.println("order receiver:" + orderInfo.toString());
    }

}
