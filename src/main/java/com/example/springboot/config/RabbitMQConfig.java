package com.example.springboot.config;

import javafx.application.Application;
import org.springframework.amqp.core.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 *此方法之目的是为了初始化队列 否则消费者无法引用
 * <p>
 *
 * @Author
 * @Date 2019/5/23 15:52
 **/
@SpringBootApplication
public class RabbitMQConfig {
    //以下方法的方法名无任何特别作用 队列名为括号中的值   例如：new Queue("oneToOne")的队列名为oneToOne
    @Bean
    public Queue TestQueue(){
        return new Queue("testQueue");
    }
    //验证topic传输  下方法比上方法的队列名多了一个s 是两个不同的队列
    @Bean
    public Queue messageQueue() {
        return new Queue("topic.message");
    }

    @Bean
    public Queue messagesQueue() {
        return new Queue("topic.messages");
    }

    /**
     *下方分别将队列topic.message  topic.messages与exchange绑定并设定此队列可以接受到的消息队列格式
     * 此处Queue的名称必须与上方的初始化队列的方法名一致  根据方法名对应具体的队列
     * 关键字不可随意设置 必须由点隔开
     * 网上有人说*代表一个 #代表多个 经过本人测试*和#的作用是一样的
     *<p>
     */
    @Bean
    Binding bindingExchangeMessage(Queue messageQueue, TopicExchange exchange) {
        return BindingBuilder.bind(messageQueue).to(exchange).with("topic.*");
    }

    @Bean
    Binding bindingExchangeMessages(Queue messagesQueue, TopicExchange exchange) {
        return BindingBuilder.bind(messagesQueue).to(exchange).with("topic.only");
    }


    //Fanout Exchange
    @Bean
    public Queue AMessage() {
        return new Queue("fanout.A");
    }

    @Bean
    public Queue BMessage() {
        return new Queue("fanout.B");
    }

    @Bean
    Binding bindingExchangeA(Queue AMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }



    @Bean
    TopicExchange exchange() {
        return new TopicExchange("exchange");
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    public Queue callbackQueue() {
        return new Queue("callbackQueue");
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
