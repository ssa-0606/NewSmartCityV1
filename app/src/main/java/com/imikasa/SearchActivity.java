package com.imikasa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.imikasa.ui.home.adapter.NewsAdapter;
import com.imikasa.ui.home.pojo.MainNewsItem;
import com.imikasa.ui.home.tasks.MyDataLoad;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent intent = getIntent();
        String query = intent.getStringExtra("query");
        RecyclerView recyclerView = findViewById(R.id.search_recycler);
        TextView textView = findViewById(R.id.search_text);
        CompletableFuture<List<MainNewsItem>> http = MyDataLoad.getNewsItem("http://124.93.196.45:10001/prod-api/press/press/list");
        http.thenAccept(mainNewsItems -> {
            List<MainNewsItem> collect = mainNewsItems.stream().filter(mainNewsItem -> mainNewsItem.getTitle().contains(query)).collect(Collectors.toList());
            Log.d("TAG", "onCreate: "+mainNewsItems);
            runOnUiThread(()->{
                if(collect==null||collect.size()==0){
//                    Toast.makeText(this, "当前搜索无结果", Toast.LENGTH_SHORT).show();
                    textView.setVisibility(View.VISIBLE);
                }else{
                    recyclerView.setLayoutManager(new GridLayoutManager(SearchActivity.this,1));
                    recyclerView.setAdapter(new NewsAdapter(R.layout.layout_main_news,collect));
                }

            });


        });
    }
}