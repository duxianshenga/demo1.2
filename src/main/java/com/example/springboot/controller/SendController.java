package com.example.springboot.controller;

import com.example.springboot.entity.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 生产者一号
 * <p>
 *
 * @Author
 * @Date 2019/5/23 15:56
 **/
@Component
public class SendController {

    @Autowired
    private AmqpTemplate template;

    //字符串传输使用
    public void send(String msg) {
        String context = msg+ LocalDateTime.now();
        System.out.println("生产者一号 : " + context);
        //参数1为队列名称 2为消息内容
        template.convertAndSend("testQueue", context);
    }

    //实体传输使用
    public void send(User user){
        System.out.println("生产者一号 : " + user.getName());
        //参数1为队列名称 2为消息内容
        template.convertAndSend("testQueue", user);
    }

    //数组传输使用  topic exchange
    public void send(String... msgs) {
        System.out.println("消息1: " + msgs[0]);
        template.convertAndSend("exchange", "topic.1", msgs[0]);
        System.out.println("消息2: " + msgs[1]);
        template.convertAndSend("exchange", "topic.only", msgs[1]);
        System.out.println("消息3: " + msgs[2]);
        template.convertAndSend("exchange", "topic.messages", msgs[2]);
    }

    //fanout exchange使用  routingKey些什么都可以
    public void sendFanout (String msg) {
        System.out.println("生产者一号 : " + msg);
        template.convertAndSend("fanoutExchange", "nonentityQueue",msg);
    }
}
