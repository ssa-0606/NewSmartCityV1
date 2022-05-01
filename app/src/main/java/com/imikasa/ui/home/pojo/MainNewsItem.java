package com.imikasa.ui.home.pojo;

public class MainNewsItem {

    private int id;
    private String title;
    private String cover;
    private String content;
    private String publishDate;
    private int commentNum;
    private int readNum;
    private int likeNum;

    public MainNewsItem(int id, String title, String cover, String content, String publishDate, int commentNum, int readNum, int likeNum) {
        this.id = id;
        this.title = title;
        this.cover = cover;
        this.content = content;
        this.publishDate = publishDate;
        this.commentNum = commentNum;
        this.readNum = readNum;
        this.likeNum = likeNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getReadNum() {
        return readNum;
    }

    public void setReadNum(int readNum) {
        this.readNum = readNum;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }
}
