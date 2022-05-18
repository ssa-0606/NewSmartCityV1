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
    private final MutableLiveData<House> houseMutableLiveData;

    public HouseViewModel() {
        houseListLiveData = new MutableLiveData<>();
        houseMutableLiveData = new MutableLiveData<>();
        CompletableFuture<List<House>> houseList = HouseTask.getHouses("http://124.93.196.45:10001/prod-api/api/house/housing/list");
        houseList.thenAccept(houseListLiveData::postValue);
    }


    public MutableLiveData<List<House>> getHouseListLiveData(){return houseListLiveData;}
    public MutableLiveData<House> getHouseMutableLiveData(){return houseMutableLiveData;}
    public void setHouseListLiveData(String type){
        CompletableFuture<List<House>> houseList = HouseTask.getHouses("http://124.93.196.45:10001/prod-api/api/house/housing/list?houseType="+type);
        houseList.thenAccept(houseListLiveData::postValue);
    }
    public void setHouseListLiveDataByName(String msg){
        CompletableFuture<List<House>> houseList = HouseTask.getHouses("http://124.93.196.45:10001/prod-api/api/house/housing/list?sourceName="+msg);
        houseList.thenAccept(houseListLiveData::postValue);
    }
    public void setHouseMutableLiveData(String id){
        CompletableFuture<House> house = HouseTask.getHouse("http://124.93.196.45:10001/prod-api/api/house/housing/"+id);
        house.thenAccept(houseMutableLiveData::postValue);
    }

}
