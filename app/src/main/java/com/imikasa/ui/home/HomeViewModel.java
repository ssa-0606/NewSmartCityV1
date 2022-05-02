package com.imikasa.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.imikasa.ui.home.pojo.MainLunBo;
import com.imikasa.ui.home.pojo.MainNewsCategory;
import com.imikasa.ui.home.pojo.MainNewsItem;
import com.imikasa.ui.home.pojo.MainService;
import com.imikasa.ui.home.tasks.MyDataLoad;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<List<MainLunBo>> lunbos;
    private final MutableLiveData<List<MainService>> services;
    private final MutableLiveData<List<MainNewsCategory>> newsCategories;
    private final MutableLiveData<List<MainNewsItem>> newsItems;


    public HomeViewModel() {
        lunbos = new MutableLiveData<>();
        services = new MutableLiveData<>();
        newsCategories = new MutableLiveData<>();
        newsItems = new MutableLiveData<>();

        CompletableFuture<List<MainLunBo>> lunBo = MyDataLoad.getLunBo("http://124.93.196.45:10001/prod-api/api/rotation/list?type=2");
        lunBo.thenAccept(lunbos::postValue);
        CompletableFuture<List<MainService>> service = MyDataLoad.getService("http://124.93.196.45:10001/prod-api/api/service/list");
        service.thenAccept(services::postValue);
//        CompletableFuture<List<MainNewsCategory>> newsCategory = MyDataLoad.getNews("http://124.93.196.45:10001/prod-api/press/category/list", "http://124.93.196.45:10001/prod-api/press/press/list?type=");
//        newsCategory.thenAccept(newsCategories::postValue);
        CompletableFuture<List<MainNewsCategory>> newsCategory = MyDataLoad.getNewsCategory("http://124.93.196.45:10001/prod-api/press/category/list");
        newsCategory.thenAccept(newsCategories::postValue);
        CompletableFuture<List<MainNewsItem>> newsItem = MyDataLoad.getNewsItem("http://124.93.196.45:10001/prod-api/press/press/list?type=9");
        newsItem.thenAccept(newsItems::postValue);

    }

    public LiveData<List<MainLunBo>> getLunBo() {
        return lunbos;
    }
    public LiveData<List<MainService>> getService(){
        return services;
    }
    public LiveData<List<MainNewsCategory>> getNewsCategory(){
        return newsCategories;
    }
    public LiveData<List<MainNewsItem>> getNewsItem(){
        return newsItems;
    }

    public void setNewsItems(int id){
        CompletableFuture<List<MainNewsItem>> newsItem = MyDataLoad.getNewsItem("http://124.93.196.45:10001/prod-api/press/press/list?type="+id);
        newsItem.thenAccept(newsItems::postValue);
    }

}