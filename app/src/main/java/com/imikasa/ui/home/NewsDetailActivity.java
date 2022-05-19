package com.imikasa.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.imikasa.R;
import com.imikasa.ui.home.pojo.MainNewsItem;
import com.imikasa.ui.home.tasks.MyDataLoad;

import java.util.concurrent.CompletableFuture;

public class NewsDetailActivity extends AppCompatActivity {
    private WebView webView;
    private Toolbar toolbar;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main_news_detail);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 20);
        webView = findViewById(R.id.main_news_web);
        toolbar = findViewById(R.id.news_detail_tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        dotask(id);
    }

    private void dotask(int id) {
        Thread thread = new Thread(()->{
            CompletableFuture<MainNewsItem> news = MyDataLoad.getNews("http://124.93.196.45:10001/prod-api/press/press/" + id);
            news.thenAccept(mainNewsItem -> {
                runOnUiThread(()->{
                    webView.loadDataWithBaseURL(null,mainNewsItem.getContent(),"text/html","UTF-8",null);
                });
            });
        });
        thread.start();
    }
}
