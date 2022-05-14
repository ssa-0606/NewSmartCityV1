package com.imikasa.activity.pojo;

public class ActivityDetail {

    private int id;
    private String name;
    private String content;
    private String imgUrl;
    private int signupNum;
    private String publishTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getSignupNum() {
        return signupNum;
    }

    public void setSignupNum(int signupNum) {
        this.signupNum = signupNum;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public ActivityDetail(int id, String name, String content, String imgUrl, int signupNum, String publishTime) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.imgUrl = imgUrl;
        this.signupNum = signupNum;
        this.publishTime = publishTime;
    }
}
