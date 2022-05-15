package com.imikasa.movie.pojo;

public class MovieDetail {

    private int id;
    private String name;
    private String category;
    private String cover;
    private int score;
    private int duration;
    private String playDate;
    private int likeNum;
    private String introduction;

    public MovieDetail(int id, String name, String category, String cover, int score, int duration, String playDate, int likeNum, String introduction) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.cover = cover;
        this.score = score;
        this.duration = duration;
        this.playDate = playDate;
        this.likeNum = likeNum;
        this.introduction = introduction;
    }

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPlayDate() {
        return playDate;
    }

    public void setPlayDate(String playDate) {
        this.playDate = playDate;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
