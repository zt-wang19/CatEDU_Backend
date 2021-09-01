package com.example.servlet.controller;

import com.example.servlet.entity.Result;
import com.example.servlet.entity.User;
import com.example.servlet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/modify/selfie")
    public Result mod_selfie(@RequestBody String requestBody){
        return userService.mod_selfie(requestBody);
    }

    @PostMapping("/modify/nickname")
    public Result mod_nickname(@RequestBody String requestBody){
        return userService.mod_nickname(requestBody);
    }
    @PostMapping("/modify/email")
    public Result mod_email(@RequestBody String requestBody){
        return userService.mod_email(requestBody);
    }

    @PostMapping("/info")
    public Result get_userinfo(@RequestParam("token") String token){
        return userService.get_userinfo(token);
    }
}
