package com.example.springboot.controller;

import com.example.springboot.entity.User;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *
 * @Author
 * @Date 2019/5/23 16:14
 **/
@RestController
@RequestMapping("/rabbit")
public class RabbitTestController {

    @Autowired
    private SendController helloSender1;

    @Autowired
    private SendController2 helloSender2;

    @Autowired
    private SendCallBackController SendCallBack;

    @Autowired
    private UserService userService;

    /**
     * 单生产者-消费者
     */
    @RequestMapping("/oneToOne")
    public void oneToOne() {
        helloSender1.send("oneToOne");
    }

    /**
     * 单生产者-多消费者
     */
    @RequestMapping("/oneToMany")
    public void oneToMany() {
        for(int i=0;i<10;i++){
            helloSender1.send("oneToMany:"+i);
        }
    }

    /**
     * 多生产者-多消费者
     */
    @RequestMapping("/manyToMany")
    public void manyToMany() {
        for(int i=0;i<10;i++){
            helloSender1.send("manyToMany:"+i);
            helloSender2.send("manyToMany:"+i);
        }
    }

    /**
     * 多生产者-多消费者  实体类传输(实体必须实现序列化)
     */
    @RequestMapping("/oneToOneEntity")
    public void oneToOneEntity() {
        List<User> userList = userService.selectByUser();
        userList.forEach(user -> {
            helloSender1.send(user);
        });
    }

    /**
     * 参数二比参数一多了一个s  用以区分topic的传输结果
     */
    @RequestMapping("/topicTest")
    public void topicTest() {
        helloSender1.send( "topic.1","topic.only","topic.messages");
    }

    /**
     * 测试是否所有fanout.开头的队列都可以接受消息  消费者三号未实现fanout.开头的队列 测试是否可以接受到消息
     */
    @RequestMapping("/fanoutTest")
    public void fanoutTest() {
        helloSender1.sendFanout("fanout message");
    }

    /*
     *回调消息
     */
    @RequestMapping("/confimCallBacK")
    public void confimCallBacK(){
        String msg ="生产者：发送回调消息";
        SendCallBack.sendConfirm(msg);
    }
}
