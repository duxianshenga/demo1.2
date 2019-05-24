package com.example.springboot.controller;

import com.example.springboot.Utils.DateUtils;
import com.example.springboot.Utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeUnit;

@Controller
//@RestController   @ResponseBody+@Controller的结合体 直接返回JSON数据
@RequestMapping("/redis")
@EnableAutoConfiguration//开启自动配置
public class TestCRedis{

    private static Logger LOG = LoggerFactory.getLogger(TestCRedis.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @ResponseBody
    @RequestMapping("/testCRedis")
    public Object testCRedis(){
        ValueOperations<String, Object> stringStringValueOperations = redisTemplate.opsForValue();
        stringStringValueOperations.set("testkey","testvalue");
        Object testkey = stringStringValueOperations.get("testkey");
        stringStringValueOperations.set("testkey一分钟",testkey,DateUtils.ONE_MINUTE, TimeUnit.HOURS);
        return testkey;
    }
}