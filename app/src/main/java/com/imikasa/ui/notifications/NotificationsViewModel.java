package com.imikasa.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.imikasa.ui.home.pojo.MainNewsItem;
import com.imikasa.ui.home.tasks.MyDataLoad;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class NotificationsViewModel extends ViewModel {

    private final MutableLiveData<List<MainNewsItem>> newsItemLiveData;

    public NotificationsViewModel() {
        newsItemLiveData = new MutableLiveData<>();
        CompletableFuture<List<MainNewsItem>> newsItem = MyDataLoad.getNewsItem("http://124.93.196.45:10001/prod-api/press/press/list?pageNum=2&pageSize=5");
        newsItem.thenAccept(newsItemLiveData::postValue);
    }

    public LiveData<List<MainNewsItem>> getText() {
        return newsItemLiveData;
    }
}