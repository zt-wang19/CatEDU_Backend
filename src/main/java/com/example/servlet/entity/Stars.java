package com.example.servlet.entity;

public class Stars {
    private Long userId;

    public Stars() {
    }

    @Override
    public String toString() {
        return "Stars{" +
                "userId=" + userId +
                ", insUri='" + insUri + '\'' +
                ", detail='" + detail + '\'' +
                ", id=" + id +
                ", token='" + token + '\'' +
                '}';
    }

    public Stars(String insUri, String detail) {
        this.insUri = insUri;
        this.detail = detail;
    }

    private String insUri;
    private String detail;
    private Long id;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;
}
