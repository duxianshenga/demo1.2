package com.example.springboot.controller;

import com.example.springboot.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者二号
 * <p>
 *
 * @Author
 * @Date 2019/5/23 15:58
 **/
@Component
@RabbitListener(queues = {"testQueue","topic.messages","fanout.B"})
public class ReceiverController2 {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("消费者二号: " + msg);
    }

    @RabbitHandler
    public void process(User user) {
        System.out.println("消费者二号 : " + user.getName());
    }
}
