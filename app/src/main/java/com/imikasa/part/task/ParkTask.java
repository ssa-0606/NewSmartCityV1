package com.imikasa.part.task;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.imikasa.part.pojo.Park;
import com.imikasa.part.pojo.ParkRecord;
import com.imikasa.tools.MyUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ParkTask {

    public static CompletableFuture<List<Park>> getPark(String url){
        List<Park> parkList = new ArrayList<>();
        CompletableFuture<List<Park>> completableFuture = CompletableFuture.supplyAsync(()->{
            try {
                String result = MyUtils.GET(url);
                JsonArray rows = new JsonParser().parse(result).getAsJsonObject().getAsJsonArray("rows");
                for (JsonElement row : rows) {
                    Park park = new Gson().fromJson(row,new TypeToken<Park>(){}.getType());
                    parkList.add(park);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return parkList;
        });
        return completableFuture;
    }

    public static CompletableFuture<List<ParkRecord>> getRecord(String url){
        List<ParkRecord> records = new ArrayList<>();
        CompletableFuture<List<ParkRecord>> recordCompletableFuture = CompletableFuture.supplyAsync(()->{
            try {
                String result = MyUtils.GET(url);
                JsonArray rows = new JsonParser().parse(result).getAsJsonObject().getAsJsonArray("rows");
                for (JsonElement row : rows) {
                    ParkRecord record = new Gson().fromJson(row,new TypeToken<ParkRecord>(){}.getType());
                    records.add(record);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return records;
        });
        return recordCompletableFuture;
    }


}
