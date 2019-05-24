package com.example.springboot.controller;

import com.example.springboot.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者三号
 * <p>
 *
 * @Author
 * @Date 2019/5/23 15:58
 **/
@Component
@RabbitListener(queues = {"testQueue"})
public class ReceiverController3 {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("消费者三号 : " + msg);
    }
}
