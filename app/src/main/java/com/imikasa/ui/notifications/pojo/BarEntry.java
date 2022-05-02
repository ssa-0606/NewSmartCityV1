package com.imikasa.ui.notifications.pojo;

public class BarEntry {
    private int likeNum;
    private String title;

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BarEntry(int likeNum, String title) {
        this.likeNum = likeNum;
        this.title = title;
    }
}
