package com.imikasa.ui.usercenter;

import android.app.Application;
import android.content.SharedPreferences;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.imikasa.tools.MyUtils;
import com.imikasa.ui.home.tasks.MyDataLoad;
import com.imikasa.ui.usercenter.pojo.UserInfo;

import java.util.concurrent.CompletableFuture;

public class UserCenterViewModel extends AndroidViewModel {

    private final MutableLiveData<UserInfo> userInfoLiveData;
    private final MutableLiveData<String> updateLiveData;
    private Application application;

    public UserCenterViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        userInfoLiveData = new MutableLiveData<>();
        updateLiveData = new MutableLiveData<>();
        updateLiveData.setValue("test");
        SharedPreferences data = application.getSharedPreferences("data", 0);
        String token = data.getString("token", "k");
        CompletableFuture<UserInfo> user = MyDataLoad.getUserInfo("http://124.93.196.45:10001/prod-api/api/common/user/getInfo", token);
        user.thenAccept(userInfoLiveData::postValue);
    }

    public LiveData<UserInfo> getUserInfoLiveData() {
        return userInfoLiveData;
    }
    public LiveData<String> stringLiveData(){return updateLiveData;}
    public void setUserInfoLiveData(String json){
        CompletableFuture<String> completableFuture = MyDataLoad.updateUser("http://124.93.196.45:10001/prod-api/api/common/user", application.getSharedPreferences("data", 0).getString("token", "k"), json);
        completableFuture.thenAccept(updateLiveData::postValue);
    }
}