package com.example.springboot.controller;

import com.example.springboot.entity.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.util.StringUtil;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 生产者
 * <p>
 *
 * @Author
 * @Date 2019/5/23 15:56
 **/
@Component
public class SendCallBackController implements RabbitTemplate.ConfirmCallback{
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendConfirm(String msg) {
        rabbitTemplate.setConfirmCallback(this);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        System.out.println("回调消息主键: " + correlationData.getId());
        rabbitTemplate.convertAndSend("exchange", "callbackQueue", msg, correlationData);
    }

    //回调函数 实现RabbitTemplate.ConfirmCallback接口中的方法
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("执行回调: " + correlationData.getId());
    }
}
