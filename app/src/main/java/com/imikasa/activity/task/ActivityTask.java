package com.imikasa.activity.task;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.imikasa.activity.pojo.ActivityCategory;
import com.imikasa.activity.pojo.ActivityDetail;
import com.imikasa.activity.pojo.ActivityLunBo;
import com.imikasa.tools.MyUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ActivityTask {

    public static CompletableFuture<List<ActivityLunBo>> getLunBo(String url){
        List<ActivityLunBo> lunBos = new ArrayList<>();
        CompletableFuture<List<ActivityLunBo>> completableFuture = CompletableFuture.supplyAsync(()->{
            try {
                String result = MyUtils.GET(url);
                JsonArray rows = new JsonParser().parse(result).getAsJsonObject().getAsJsonArray("rows");
                for (JsonElement row : rows) {
                    ActivityLunBo activityLunBo = new Gson().fromJson(row,new TypeToken<ActivityLunBo>(){}.getType());
                    lunBos.add(activityLunBo);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return lunBos;
        });
        return completableFuture;
    }

    public static CompletableFuture<List<ActivityCategory>> getCategory(String url){
        List<ActivityCategory> categories = new ArrayList<>();
        CompletableFuture<List<ActivityCategory>> completableFuture = CompletableFuture.supplyAsync(()->{
            try {
                String result = MyUtils.GET(url);
                JsonArray data = new JsonParser().parse(result).getAsJsonObject().getAsJsonArray("data");
                for (JsonElement datum : data) {
                    ActivityCategory category = new Gson().fromJson(datum,new TypeToken<ActivityCategory>(){}.getType());
                    categories.add(category);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return categories;
        });
        return completableFuture;
    }

    public static CompletableFuture<List<ActivityDetail>> getActivity(String url){
        List<ActivityDetail> activityDetails = new ArrayList<>();
        CompletableFuture<List<ActivityDetail>> completableFuture = CompletableFuture.supplyAsync(()->{
            try {
                String result = MyUtils.GET(url);
                JsonArray rows = new JsonParser().parse(result).getAsJsonObject().getAsJsonArray("rows");
                for (JsonElement row : rows) {
                    ActivityDetail activityDetail = new Gson().fromJson(row,new TypeToken<ActivityDetail>(){}.getType());
                    activityDetails.add(activityDetail);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return activityDetails;
        });
        return completableFuture;
    }

    public static CompletableFuture<ActivityDetail> getDetail(String url){

        CompletableFuture<ActivityDetail> completableFuture = CompletableFuture.supplyAsync(()->{
            ActivityDetail activityDetail = null;
            try {
                String result = MyUtils.GET(url);
                JsonObject data = new JsonParser().parse(result).getAsJsonObject().getAsJsonObject("data");
                activityDetail = new Gson().fromJson(data,new TypeToken<ActivityDetail>(){}.getType());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return activityDetail;
        });
        return completableFuture;
    }


}
