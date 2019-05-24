package com.example.springboot.controller;

import com.example.springboot.entity.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 生产者二号
 * <p>
 *
 * @Author
 * @Date 2019/5/23 15:56
 **/
@Component
public class SendController2 {

    @Autowired
    private AmqpTemplate template;

    public void send(String msg) {
        String context = msg+ LocalDateTime.now();
        System.out.println("生产者二号 : " + context);
        //参数1为队列名称 2为消息内容
        this.template.convertAndSend("testQueue", context);
    }
}
