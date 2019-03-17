package com.demo.virtuousone.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Author: 吴宸煊
 * Date: Created in  2019/3/17/0017
 * Description: 配置队列
 */
@Configuration
public class AmqpConfig {
    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }

    @Bean
    public Queue orderQueue() {
        return new Queue("order");
    }
}
