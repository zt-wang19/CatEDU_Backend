package com.example.servlet.service;

import com.example.servlet.entity.Result;
import com.example.servlet.entity.User;
import com.example.servlet.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 注册
     * @param user
     * @return
     */
    public Result register(User user){
        Result result=new Result();
        result.setSuccess(false);
        result.setDetail(null);
        if(!user.checkValid())
        {
            result.setMsg("输入不合法");
        }
        else {
            try {
                User userByName = userMapper.findUserByName(user.getUsername());
                if (userByName != null) {
                    //如果查询到 说明存在
                    result.setMsg("用户名存在");
                } else {
                    userMapper.register(user);
                    result.setMsg("注册成功");
                    result.setSuccess(true);
                    result.setDetail(user);
                }
            } catch (Exception e) {
                result.setMsg(e.getMessage());
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 登录
     */
    public Result login(User user){
        Result result=new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            Long userId = userMapper.login(user);
            if(userId==null){
                result.setMsg("用户名或密码错误");
            }else {
                result.setMsg("登录成功");
                result.setSuccess(true);
                user.setId(userId);
                User userById = userMapper.findUserById(userId);
                result.setDetail(userById);
            }

        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
