package com.imikasa.activity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.imikasa.activity.pojo.ActivityCategory;
import com.imikasa.activity.pojo.ActivityDetail;
import com.imikasa.activity.pojo.ActivityLunBo;
import com.imikasa.activity.task.ActivityTask;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ActivityViewModel extends ViewModel {

    private final MutableLiveData<List<ActivityLunBo>> lunBoLiveData;
    private final MutableLiveData<List<ActivityCategory>> categoryLiveData;
    private final MutableLiveData<List<ActivityDetail>> detailLiveData;
    private final MutableLiveData<ActivityDetail> detailMutableLiveData;

    public ActivityViewModel() {
        lunBoLiveData = new MutableLiveData<>();
        categoryLiveData = new MutableLiveData<>();
        detailLiveData = new MutableLiveData<>();
        detailMutableLiveData = new MutableLiveData<>();
        CompletableFuture<List<ActivityLunBo>> lunBo = ActivityTask.getLunBo("http://124.93.196.45:10001/prod-api/api/activity/rotation/list");
        lunBo.thenAccept(lunBoLiveData::postValue);
        CompletableFuture<List<ActivityCategory>> category = ActivityTask.getCategory("http://124.93.196.45:10001/prod-api/api/activity/category/list");
        category.thenAccept(categoryLiveData::postValue);
        CompletableFuture<List<ActivityDetail>> detail = ActivityTask.getActivity("http://124.93.196.45:10001/prod-api/api/activity/activity/list?categoryId=1");
        detail.thenAccept(detailLiveData::postValue);
    }

    public MutableLiveData<List<ActivityLunBo>> getLunBoLiveData(){return lunBoLiveData;}
    public MutableLiveData<List<ActivityCategory>> getCategoryLiveData(){return categoryLiveData;}
    public MutableLiveData<List<ActivityDetail>> getDetailLiveData(){return detailLiveData;}
    public MutableLiveData<ActivityDetail> getDetailMutableLiveData(){return detailMutableLiveData;}


    public void setDetailLiveData(int id){
        CompletableFuture<List<ActivityDetail>> detail = ActivityTask.getActivity("http://124.93.196.45:10001/prod-api/api/activity/activity/list?categoryId="+id);
        detail.thenAccept(detailLiveData::postValue);
    }
    public void setDetailMutableLiveData(int id){
        CompletableFuture<ActivityDetail> detailCompletableFuture = ActivityTask.getDetail("http://124.93.196.45:10001/prod-api/api/activity/activity/"+id);
        detailCompletableFuture.thenAccept(detailMutableLiveData::postValue);
    }

}
