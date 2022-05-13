package com.imikasa.metro.tast;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.imikasa.metro.pojo.MetroLine;
import com.imikasa.tools.MyUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MetroTask {


    public static CompletableFuture<List<MetroLine>> getMainLines(String url){
        CompletableFuture<List<MetroLine>> completableFuture = CompletableFuture.supplyAsync(()->{
            List<MetroLine> metroLines = new ArrayList<>();
            try {
                String result = MyUtils.GET(url);
                JSONArray data = new JSONObject(result).getJSONArray("data");
                for (int i = 0; i < data.length(); i++) {
                    JSONObject jsonObject = data.getJSONObject(i);
                    int lineId = jsonObject.getInt("lineId");
                    String lineName = jsonObject.getString("lineName");
                    int reachTime = jsonObject.getInt("reachTime");
                    String nextStep = jsonObject.getJSONObject("nextStep").getString("name");
                    metroLines.add(new MetroLine(lineId,lineName,reachTime,nextStep));
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return metroLines;
        });
        return completableFuture;
    }



}
