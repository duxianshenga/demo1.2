package com.example.springboot.Utils;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * 时间类
 * <p>
 *
 * @Author
 * @Date 2019/5/17 17:03
 **/
public class DateUtils {

    /**
     * 一秒的毫秒数
     */
    public static final long ONE_SECOND = 1000;
    /**
     * 一分钟的毫秒数
     */
    public static final long ONE_MINUTE = ONE_SECOND * 60;
    /**
     * 一小时的毫秒数
     */
    public static final long ONE_HOUR = ONE_MINUTE * 60;
    /**
     * 一天的毫秒数
     */
    public static final long ONE_DAY = ONE_HOUR * 24;

    /**
     *转换/获取时间
     *<p>
      * @param dataTime 时间毫秒值 不填则默认当前
     * @param zoneId    时区 不填则使用系统默认时间
     *@return java.time.LocalDateTime
     *@date 2019/5/17
     *@author dutongkai
     */
    public static LocalDateTime getDate(Long dataTime,ZoneId zoneId){
        if(dataTime==null){
            return LocalDateTime.now();
        }
        //替代毫秒值
        Instant instant = Instant.ofEpochMilli(dataTime);
        //时区 此处采用默认时区
        if(zoneId==null){
            zoneId = ZoneId.systemDefault();
        }
        return LocalDateTime.ofInstant(instant,zoneId);
    }

}
