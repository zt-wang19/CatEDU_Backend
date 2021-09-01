package com.example.servlet.service;

import com.example.servlet.entity.Md5;
import com.example.servlet.entity.Result;
import com.example.servlet.entity.User;
import com.example.servlet.mapper.UserMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
                String login_token=Md5.vanilla_encrypt(userId.toString());
//                System.out.println(login_token);
                result.setToken(login_token);
            }

        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Result mod_selfie(String requestBody) {
        HashMap<String,String> map= new HashMap<>();
        map=new Gson().fromJson(requestBody,map.getClass());
        String token=map.get("token");
        Integer selfie= Integer.valueOf(map.get("selfie"));
        Result result=new Result();
        result.setSuccess(false);
        String username=Md5.vanilla_decrypt(token);
        User user= userMapper.findUserById(Long.valueOf(username));
        if(user==null)
        {
            result.setMsg("user not exists");
        }
        else {
            if(selfie>0&&selfie<13)
            {
                userMapper.mod_selfie(user.getUsername(),selfie);
                result.setMsg("selfie modified");
                result.setSuccess(true);
            }
            else{
                result.setMsg("no matched selfie");
            }
        }
        return result;
    }

    public Result mod_nickname(String requestBody) {
        HashMap<String,String> map= new HashMap<>();
        map=new Gson().fromJson(requestBody,map.getClass());
        String token=map.get("token");
        String nickname=map.get("nickname");
        Result result=new Result();
        result.setSuccess(false);
        String username=Md5.vanilla_decrypt(token);
        User user= userMapper.findUserById(Long.valueOf(username));
        if(user==null)
        {
            result.setMsg("user not exists");
        }
        else {
            if(nickname.length()<100)
            {
                userMapper.mod_nickname(user.getUsername(),nickname);
                result.setMsg("nickname modified");
                result.setSuccess(true);
            }
            else{
                result.setMsg("illegal nickname");
            }
        }
        return result;
    }
    public Result mod_email(String requestBody) {
        HashMap<String,String> map= new HashMap<>();
        map=new Gson().fromJson(requestBody,map.getClass());
        String token=map.get("token");
        String email=map.get("email");
        Result result=new Result();
        result.setSuccess(false);
        String username=Md5.vanilla_decrypt(token);
        User user= userMapper.findUserById(Long.valueOf(username));
        boolean flag=true;
        String email_pattern = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
        flag = Pattern.matches(email_pattern, email);
        if(user==null)
        {
            result.setMsg("user not exists");
        }
        else {
            if(flag)
            {
                userMapper.mod_email(user.getUsername(),email);
                result.setMsg("email modified");
                result.setSuccess(true);
            }
            else{
                result.setMsg("illegal email");
            }
        }
        return result;
    }

    public Result get_userinfo(String token) {
        Result result=new Result();
        result.setSuccess(false);
        String userId=Md5.vanilla_decrypt(token);
        User user= userMapper.findUserById(Long.valueOf(userId));
        if(user==null)
        {
            result.setMsg("user not exists");
        }
        else {
            result.setDetail(user);
            result.setSuccess(true);
        }
        return result;
    }
}
