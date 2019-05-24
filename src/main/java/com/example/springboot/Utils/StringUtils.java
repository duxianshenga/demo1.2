package com.example.springboot.Utils;

/**
 * 字符串工具类
 * <p>
 *
 * @Date 2019/5/17 16:07
 **/
public class StringUtils {


    /**
     *判断字符串是否包含内容
     *<p>
      * @param str
     *@return java.lang.Boolean
     *@date 2019/5/17
     */
    public static Boolean hasText(String str){
        if(str==null){
            return false;
        }
        return str.trim().length()>0;
    }
}
