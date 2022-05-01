package com.imikasa.ui.home.tasks;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.imikasa.tools.MyUtils;
import com.imikasa.ui.home.pojo.MainLunBo;
import com.imikasa.ui.home.pojo.MainNewsCategory;
import com.imikasa.ui.home.pojo.MainNewsItem;
import com.imikasa.ui.home.pojo.MainService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MyDataLoad {

    //获取网络中的轮播图数据
    public static CompletableFuture<List<MainLunBo>> getLunBo(String url){
        List<MainLunBo> lunBos = new ArrayList<>();
        CompletableFuture<List<MainLunBo>> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                String result = MyUtils.GET(url);
                JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
                JsonArray rows = jsonObject.getAsJsonArray("rows");
                for (JsonElement row : rows) {
                    MainLunBo lunBo = new Gson().fromJson(row,new TypeToken<MainLunBo>(){}.getType());
                    lunBos.add(lunBo);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return lunBos;
        });
        return completableFuture;
    }
    //获取服务数据
    public static CompletableFuture<List<MainService>> getService(String url){
        List<MainService> services = new ArrayList<>();
        CompletableFuture<List<MainService>> uCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                String result = MyUtils.GET(url);
                JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
                JsonArray rows = jsonObject.getAsJsonArray("rows");
                for (JsonElement row : rows) {
                    MainService service = new Gson().fromJson(row,new TypeToken<MainService>(){}.getType());
                    services.add(service);
                }
                services.sort((t1, t2) -> t2.getSort()-t1.getSort());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return services;
        });
        return uCompletableFuture;
    }
    //获取新闻分类、新闻列表数据
    public static CompletableFuture<List<MainNewsCategory>> getNews(String url,String url1){
        List<MainNewsCategory> newsCategories = new ArrayList<>();
        CompletableFuture<List<MainNewsCategory>> completableFuture  = CompletableFuture.supplyAsync(() -> {
            try {
                String result = MyUtils.GET(url);
                JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
                JsonArray rows = jsonObject.getAsJsonArray("data");
                for (JsonElement row : rows) {
                    MainNewsCategory newsCategory = new Gson().fromJson(row,new TypeToken<MainNewsCategory>(){}.getType());
                    List<MainNewsItem> newsItemList = new ArrayList<>();
                    String result1 = MyUtils.GET(url1 + newsCategory.getId());
                    JsonArray rows1 = new JsonParser().parse(result1).getAsJsonObject().getAsJsonArray("rows");
                    for (JsonElement rwo1 : rows1) {
                        MainNewsItem newsItem = new Gson().fromJson(rwo1,new TypeToken<MainNewsItem>(){}.getType());
                        newsItemList.add(newsItem);
                    }
                    newsCategory.setNewsItemList(newsItemList);
                    newsCategories.add(newsCategory);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return newsCategories;
        });
        return completableFuture;
    }




}
