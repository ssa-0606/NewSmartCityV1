package com.imikasa.bus.task;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.imikasa.bus.pojo.BusLine;
import com.imikasa.bus.pojo.BusStop;
import com.imikasa.tools.MyUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BusTask {

    public static CompletableFuture<List<BusLine>> getBusLine(String url){
        CompletableFuture<List<BusLine>> completableFuture = CompletableFuture.supplyAsync(()->{
            List<BusLine> busLines = new ArrayList<>();
            try {
                String result = MyUtils.GET(url);
                JsonArray rows = new JsonParser().parse(result).getAsJsonObject().getAsJsonArray("rows");
                for (JsonElement row : rows) {
                    BusLine busLine = new Gson().fromJson(row,new TypeToken<BusLine>(){}.getType());
                    busLines.add(busLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return busLines;
        });
        return completableFuture;
    }

    public static CompletableFuture<List<BusStop>> getBusStop(String url){
        CompletableFuture<List<BusStop>> completableFuture = CompletableFuture.supplyAsync(()->{
            List<BusStop> busStops = new ArrayList<>();
            try {
                String result = MyUtils.GET(url);
                JsonArray rows = new JsonParser().parse(result).getAsJsonObject().getAsJsonArray("rows");
                for (JsonElement row : rows) {
                    BusStop busLine = new Gson().fromJson(row,new TypeToken<BusStop>(){}.getType());
                    busStops.add(busLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("TAG", "getBusStop: "+busStops);
            return busStops;
        });
        return completableFuture;
    }

    public static CompletableFuture<BusLine> getBusLineDetail(String url){
        CompletableFuture<BusLine> completableFuture = CompletableFuture.supplyAsync(()->{
            BusLine busLine = null;
            try {
                String result = MyUtils.GET(url);
                JsonObject data = new JsonParser().parse(result).getAsJsonObject().getAsJsonObject("data");
                busLine = new Gson().fromJson(data,new TypeToken<BusLine>(){}.getType());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return busLine;
        });
        return completableFuture;
    }


}
