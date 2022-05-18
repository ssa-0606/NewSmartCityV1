package com.imikasa.house;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.imikasa.house.pojo.House;
import com.imikasa.house.task.HouseTask;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class HouseViewModel extends ViewModel {

    private final MutableLiveData<List<House>> houseListLiveData;

    public HouseViewModel() {
        houseListLiveData = new MutableLiveData<>();
        CompletableFuture<List<House>> houseList = HouseTask.getHouse("http://124.93.196.45:10001/prod-api/api/house/housing/list");
        houseList.thenAccept(houseListLiveData::postValue);
    }


    public MutableLiveData<List<House>> getHouseListLiveData(){return houseListLiveData;}

}
