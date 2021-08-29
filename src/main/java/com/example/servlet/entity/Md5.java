package com.example.servlet.entity;

import com.alibaba.druid.sql.visitor.functions.Char;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Md5 {
    static MessageDigest md;
    static String salt="42";
    static char key='w';

    public Md5() throws NoSuchAlgorithmException {
        md=MessageDigest.getInstance("MD5");
        salt="42";
        key='w';
    }

    public Md5(MessageDigest md) {
        this.md = md;
    }

    public static String vanilla_encrypt(String input){
        String output="";
        input+=salt;
        for (int i = 0; i < input.length(); i++) {
            output+=(char)(input.charAt(i)^key);
        }
        return output;
    }

    public static String vanilla_decrypt(String input){
        String output="";
        for (int i = 0; i < input.length(); i++) {
            output+=(char)(input.charAt(i)^key);
        }
        output=output.substring(0,output.length()-salt.length());
        return output;
    }

    public static String encode(String input){
        md.update(input.getBytes(StandardCharsets.UTF_8));
        md.update(salt.getBytes(StandardCharsets.UTF_8));
        md.update(String.valueOf(new Date().getTime()).getBytes(StandardCharsets.UTF_8));
        String md_token=new BigInteger(1,md.digest()).toString(16);
        return md_token;
    }
}
