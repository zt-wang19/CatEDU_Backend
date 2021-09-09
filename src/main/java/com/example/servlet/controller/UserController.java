package com.example.servlet.controller;

import com.example.servlet.entity.*;
import com.example.servlet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/getHistoryCloud")
    public Result getHistoryCloud(@RequestBody String requestBody){
        return userService.getHistoryCloud(requestBody);
    }
    @PostMapping("/searchStars")
    public Result searchStars(@RequestBody String requestBody){
        return userService.searchStars(requestBody);
    }
    @PostMapping("/getStars")
    public Result getStars(@RequestBody String requestBody){
//        stars.setUserId(Long.valueOf(Md5.vanilla_decrypt(stars.getToken())));
        return userService.getStars(requestBody);
    }
    @PostMapping("/addStars")
    public Result addStars(@RequestBody Stars stars){
        stars.setUserId(Long.valueOf(Md5.vanilla_decrypt(stars.getToken())));
        return userService.addStars(stars);
    }
    @PostMapping("/rmStars")
    public Result rmStars(@RequestBody Stars stars){
        stars.setUserId(Long.valueOf(Md5.vanilla_decrypt(stars.getToken())));
        return userService.rmStars(stars);
    }

    @PostMapping("/getHistory")
    public Result getHistory(@RequestBody String requestBody){
        return userService.getHistory(requestBody);
    }
    @PostMapping("/addHistory")
    public Result addHistory(@RequestBody History history){
        history.setUserId(Long.valueOf(Md5.vanilla_decrypt(history.getToken())));
        return userService.addHistory(history);
    }

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
