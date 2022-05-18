package com.imikasa.house.task;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.imikasa.house.pojo.House;
import com.imikasa.tools.MyUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class HouseTask {

    public static CompletableFuture<List<House>> getHouses(String url){
        CompletableFuture<List<House>> completableFuture = CompletableFuture.supplyAsync(()->{
            List<House> houses = new ArrayList<>();
            try {
                String result = MyUtils.GET(url);
                JsonArray rows = new JsonParser().parse(result).getAsJsonObject().getAsJsonArray("rows");
                for (JsonElement row : rows) {
                    House house = new Gson().fromJson(row,new TypeToken<House>(){}.getType());
                    houses.add(house);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return houses;
        });
        return completableFuture;
    }

    public static CompletableFuture<House> getHouse(String url){
        CompletableFuture<House> completableFuture = CompletableFuture.supplyAsync(()->{
            House house = null;
            try {
                String result = MyUtils.GET(url);
                JsonObject data = new JsonParser().parse(result).getAsJsonObject().getAsJsonObject("data");
                house = new Gson().fromJson(data,new TypeToken<House>(){}.getType());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return house;
        });
        return completableFuture;
    }

}
