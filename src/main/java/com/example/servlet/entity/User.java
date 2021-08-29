package com.example.servlet.entity;

import java.util.regex.Pattern;

public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String nickname;
    private int selfie;
    static public int selfie_num=11;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSelfie() {
        return selfie;
    }

    public void setSelfie(int selfie) {
        this.selfie = selfie;
    }

    public User() {
    }

    public User(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean checkValid() {
        final boolean[] input_valid = new boolean[3];
        String username_pattern = "\\w{5,}";
        input_valid[0] = Pattern.matches(username_pattern, username);
        String password_pattern = "[a-z0-9A-Z]{8,}";
        input_valid[1] = Pattern.matches(password_pattern, password);
        String email_pattern = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
        input_valid[2] = Pattern.matches(email_pattern, email);
        boolean flag = true;
        for (boolean i :
                input_valid) {
            if (!i) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}
