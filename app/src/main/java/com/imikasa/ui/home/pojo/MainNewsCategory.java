package com.imikasa.ui.home.pojo;

import java.util.List;

public class MainNewsCategory {
    private int id;
    private String name;
    private int sort;
    private List<MainNewsItem> newsItemList;


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

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public List<MainNewsItem> getNewsItemList() {
        return newsItemList;
    }

    public void setNewsItemList(List<MainNewsItem> newsItemList) {
        this.newsItemList = newsItemList;
    }

    public MainNewsCategory(int id, String name, int sort, List<MainNewsItem> newsItemList) {
        this.id = id;
        this.name = name;
        this.sort = sort;
        this.newsItemList = newsItemList;
    }
}
