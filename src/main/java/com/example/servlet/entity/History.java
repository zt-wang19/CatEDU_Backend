package com.example.servlet.entity;

public class History {
    private Long userId;
    private String insUri;
    private String detail;
    private String time;
    private Long id;
    private String token;
    private int times;

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public History(){}
    public History(String token, String insUri, String detail, String time) {
        this.insUri = insUri;
        this.detail = detail;
        this.time = time;
        this.id=Long.valueOf(0);
        this.token=token;
        this.userId=Long.valueOf(Md5.vanilla_decrypt(token));
    }

    public History(String insUri, String detail) {
        this.insUri = insUri;
        this.detail = detail;
    }

    public History(String insUri, String detail, String time, int times) {
        this.insUri = insUri;
        this.detail = detail;
        this.time = time;
        this.times = times;
    }

    public History(String insUri, String detail, String time) {
        this.insUri = insUri;
        this.detail = detail;
        this.time = time;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "History{" +
                "userId=" + userId +
                ", insUri='" + insUri + '\'' +
                ", detail='" + detail + '\'' +
                ", time='" + time + '\'' +
                ", id=" + id +
                ", token='" + token + '\'' +
                '}';
    }



    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }



    public Long getId() {
        return userId;
    }

    public void setId(Long userId) {
        this.userId = userId;
    }

    public String getInsUri() {
        return insUri;
    }

    public void setInsUri(String insUri) {
        this.insUri = insUri;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
