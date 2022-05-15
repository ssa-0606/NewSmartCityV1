package com.imikasa.movie.task;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.imikasa.movie.pojo.MovieDetail;
import com.imikasa.movie.pojo.MovieLunBo;
import com.imikasa.tools.MyUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MovieTask {

    public static CompletableFuture<List<MovieLunBo>> getLunBo(String url){
        List<MovieLunBo> lunBos = new ArrayList<>();
        CompletableFuture<List<MovieLunBo>> completableFuture = CompletableFuture.supplyAsync(()->{
            try {
                String result = MyUtils.GET(url);
                JsonArray rows = new JsonParser().parse(result).getAsJsonObject().getAsJsonArray("rows");
                for (JsonElement row : rows) {
                    MovieLunBo lunBo = new Gson().fromJson(row,new TypeToken<MovieLunBo>(){}.getType());
                    lunBos.add(lunBo);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return lunBos;
        });
        return completableFuture;
    }

    public static CompletableFuture<List<MovieDetail>> getList(String url){
        List<MovieDetail> details = new ArrayList<>();
        CompletableFuture<List<MovieDetail>> completableFuture = CompletableFuture.supplyAsync(()->{
            try {
                String result = MyUtils.GET(url);
                JsonArray rows = new JsonParser().parse(result).getAsJsonObject().getAsJsonArray("rows");
                for (JsonElement row : rows) {
                    MovieDetail detail = new Gson().fromJson(row,new TypeToken<MovieDetail>(){}.getType());
                    details.add(detail);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return details;
        });
        return completableFuture;
    }

}
