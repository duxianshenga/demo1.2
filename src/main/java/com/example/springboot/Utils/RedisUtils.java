package com.example.springboot.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * @Author
 * @Date 2019/5/17 16:52
 **/
public class RedisUtils{

    @Autowired
    private static RedisTemplate<String, Object> redisTemplate = new RedisTemplate();

    /**
     *向redis中缓存数据
     *<p>
      * @param key
     * @param value
     *@return void
     *@date 2019/5/17
     *@author dutongkai
     */
    public static void setRedis(String key,Object value){
        setRedis(key,value,null);
    }

    /**
     *向redis中存入定时数据
     *<p>
      * @param key
     * @param value
     * @param saveTime
     *@return void
     *@date 2019/5/17
     *@author dutongkai
     */
    public static void setRedis(String key,Object value,Long saveTime){
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        if(saveTime==null){
            operations.set(key,value);
        }else{
            operations.set(key,value,DateUtils.ONE_MINUTE, TimeUnit.HOURS);
        }
    }

    /**
     *从redis中取出数据
     *<p>
     * @param key
     *@return void
     *@date 2019/5/17
     *@author dutongkai
     */
    public static Object getRedis(String key){
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }
}
