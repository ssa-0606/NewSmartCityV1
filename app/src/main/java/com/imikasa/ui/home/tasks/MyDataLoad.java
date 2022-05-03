package com.imikasa.ui.home.tasks;

import android.util.Log;

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
import com.imikasa.ui.usercenter.pojo.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

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
    //获取新闻分类、新闻列表数据，老土写法
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
    //获取新闻分类
    public static CompletableFuture<List<MainNewsCategory>> getNewsCategory(String url){
        List<MainNewsCategory> mainNewsCategories = new ArrayList<>();
        CompletableFuture<List<MainNewsCategory>> completableFuture = CompletableFuture.supplyAsync(()->{
            try {
                String result = MyUtils.GET(url);
                JsonArray rows = new JsonParser().parse(result).getAsJsonObject().getAsJsonArray("data");
                for (JsonElement row : rows) {
                    MainNewsCategory newsCategory = new Gson().fromJson(row, new TypeToken<MainNewsCategory>() {}.getType());
                    mainNewsCategories.add(newsCategory);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return mainNewsCategories;
        });
        return completableFuture;
    }
    //获取新闻列表
    public static CompletableFuture<List<MainNewsItem>> getNewsItem(String url){
        List<MainNewsItem> mainNewsItems = new ArrayList<>();
        CompletableFuture<List<MainNewsItem>> completableFuture = CompletableFuture.supplyAsync(()->{
            try {
                String result = MyUtils.GET(url);
                JsonArray rows = new JsonParser().parse(result).getAsJsonObject().getAsJsonArray("rows");
                for (JsonElement row : rows) {
                    MainNewsItem newsItem = new Gson().fromJson(row, new TypeToken<MainNewsItem>() {}.getType());
                    mainNewsItems.add(newsItem);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return mainNewsItems;
        });
        return completableFuture;
    }


    public static CompletableFuture<UserInfo> getUserInfo(String url,String token){
        CompletableFuture<UserInfo> completableFuture = CompletableFuture.supplyAsync(() -> {
            UserInfo userInfo;
            try {
                String result = MyUtils.GET_T(url, token);
                int code = new JSONObject(result).getInt("code");
                if(code == 200){
                    JsonObject userJson = new JsonParser().parse(result).getAsJsonObject().getAsJsonObject("user");
                    userInfo = new Gson().fromJson(userJson,new TypeToken<UserInfo>(){}.getType());
                }else{
                    userInfo = null;
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                userInfo = null;
            }
            return userInfo;
        });
        return completableFuture;
    }


    public static CompletableFuture<String> updateUser(String url,String token,String msg){
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
            try {
                String result = MyUtils.PUT_T(url, msg, token);
                int code = new JSONObject(result).getInt("code");
                if(code == 200){
                    return "修改成功";
                }else{
                    return "修改失败";
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                return "修改失败";
            }
        });
        return completableFuture;
    }




}
