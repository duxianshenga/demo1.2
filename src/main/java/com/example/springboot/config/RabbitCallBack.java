package com.example.springboot.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
/**
 * RabbitMQ的回调实现
 * <p>
 * @Date 2019/5/24 15:11
 **/
public class RabbitCallBack {
    //@Value 获取配置文件的对应的值  此处设置是为了设定回调地址
    @Value("${spring.rabbitmq.host}")
    private String addresses;

    @Value("${spring.rabbitmq.port}")
    private String port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    @Value("${spring.rabbitmq.publisher-confirms}")
    private boolean publisherConfirms;

    @Bean
    /*
     *可设置为prototype singleton也就是不同的作用域
     * singleton类似于单例模式 返回的都是一个实例 spring容器只会保存一个Bean    线程之间资源 完全共享
     * prototype每一次请求都会生成一个新的bean  多线程情况下必须一个线程对应一个独立的实例
     * 无状态的可以使用singleton  有状态的使用prototype
     *<p>
     */
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate getRabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(getConnectionFactory());
        return template;
    }

    @Bean
    public ConnectionFactory getConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(addresses+":"+port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setPublisherConfirms(publisherConfirms);
        return connectionFactory;
    }
}
