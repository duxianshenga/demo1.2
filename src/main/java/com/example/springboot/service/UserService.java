package com.example.springboot.service;

import com.example.springboot.Utils.StringUtils;
import com.example.springboot.dao.UserMapper;
import com.example.springboot.entity.User;
import com.example.springboot.entity.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *
 * @Date 2019/5/17 15:53
 **/
@Service("userService")
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     *条件查询
     *<p>
      * @param user
     *@return java.util.List<com.example.springboot.entity.User>
     *@date 2019/5/17
     */
    public List<User> selectByUser(User user){
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        if(StringUtils.hasText(user.getId())){
            criteria.andIdEqualTo(user.getId());
        }
        if(StringUtils.hasText(user.getName())){
            criteria.andNameEqualTo(user.getName());
        }
        return userMapper.selectByExample(example);
    }

    public List<User> selectByUser(){
        User user = new User();
        return selectByUser(user);
    }
}
