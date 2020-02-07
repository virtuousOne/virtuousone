package com.demo.virtuousone.config;

import lombok.Data;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: 吴宸煊
 * Date: 2020/2/7 10:40
 * Description:通过交换机连接队列
 */
@Data
@Configuration
@PropertySource("classpath:rabbitMQ.properties")
public class RabbitmqConfig {

    /**
     * 直连交换机
     */
    @Value("directExchange")
    private String directExchange;

    /**
     * 主题交换机
     */
    @Value("topicExchange")
    private String topicExchange;

    /**
     * 广播交换机--1号
     */
    @Value("radioExchangeFirst")
    private String radioExchangeFirst;


    /**
     * 直连队列
     */
    @Value("directQueue")
    private String directQueue;

    /**
     * 主题队列
     */
    @Value("topicQueue")
    private String topicQueue;

    /**
     * 广播队列--1号
     */
    @Value("radioQueueFirst")
    private String radioQueueFirst;

    /**
     * 广播队列--2号
     */
    @Value("radioQueueSecond")
    private String radioQueueSecond;

    /**
     * 创建四个队列
     */
    @Bean("vipDirectQueue")
    public Queue getDirectQueue() {
        return new Queue(directQueue);
    }

    @Bean("vipTopicQueue")
    public Queue getTopicQueue() {
        return new Queue(topicQueue);
    }

    @Bean("vipRadioQueue")
    public Queue getRadioQueueFirstQueue() {
        return new Queue(radioQueueFirst);
    }

    @Bean("vipRadioSecondQueue")
    public Queue getRadioQueueSecondQueue() {
        return new Queue(radioQueueSecond);
    }

    //创建三个交换机

    @Bean("vipDirectExchange")
    public DirectExchange getDirectExchange() {
        return new DirectExchange(directExchange);
    }

    @Bean("vipTopicExchange")
    public TopicExchange getTopicExchange() {
        return new TopicExchange(topicExchange);
    }

    @Bean("vipRadioExchange")
    public FanoutExchange getRadioExchange() {
        return new FanoutExchange(radioExchangeFirst);
    }

    //创建四种绑定关系

    @Bean
    public Binding bindDirect(@Qualifier("vipDirectQueue") Queue queue, @Qualifier("vipDirectExchange") DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with("sms1");
    }

    @Bean
    public Binding bindTopic(@Qualifier("vipTopicQueue") Queue queue, @Qualifier("vipTopicExchange") TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with("sms2");
    }

    @Bean
    public Binding bindRadioFirst(@Qualifier("vipRadioQueue") Queue queue, @Qualifier("vipRadioExchange") FanoutExchange radioQueueFirst) {
        return BindingBuilder.bind(queue).to(radioQueueFirst);
    }

    @Bean
    public Binding bindRadioSecond(@Qualifier("vipRadioSecondQueue") Queue queue, @Qualifier("vipRadioExchange") FanoutExchange radioQueueSecond) {
        return BindingBuilder.bind(queue).to(radioQueueSecond);
    }


}
