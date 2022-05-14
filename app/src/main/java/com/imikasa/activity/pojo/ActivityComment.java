package com.imikasa.activity.pojo;

public class ActivityComment {
    private int id;
    private int userId;
    private String content;
    private String commentTime;
    private String nickName;
    private String avatar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public ActivityComment(int id, int userId, String content, String commentTime, String nickName, String avatar) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.commentTime = commentTime;
        this.nickName = nickName;
        this.avatar = avatar;
    }
}
