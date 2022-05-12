package com.imikasa.hospital;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.imikasa.hospital.fragments.HospitalFragment;
import com.imikasa.hospital.pojo.HospitalInfo;
import com.imikasa.hospital.pojo.HospitalLunBo;
import com.imikasa.hospital.pojo.Patient;
import com.imikasa.hospital.task.HospitalTask;
import com.imikasa.ui.home.tasks.MyDataLoad;
import com.imikasa.ui.usercenter.pojo.UserInfo;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class HospitalViewModel extends ViewModel {

    private final MutableLiveData<List<HospitalLunBo>> hospitalLunBoListLiveData;
    private final MutableLiveData<List<HospitalInfo>> hospitalInfoListLiveData;
    private final MutableLiveData<HospitalInfo> hospitalInfoMutableLiveData;
    private final MutableLiveData<UserInfo> userInfoMutableLiveData;
    private final MutableLiveData<List<Patient>> listPatientMutableLiveData;
    private final MutableLiveData<Patient> patientMutableLiveData;



    public HospitalViewModel() {
        hospitalLunBoListLiveData = new MutableLiveData<>();
        hospitalInfoListLiveData = new MutableLiveData<>();
        hospitalInfoMutableLiveData = new MutableLiveData<>();
        userInfoMutableLiveData = new MutableLiveData<>();
        listPatientMutableLiveData = new MutableLiveData<>();
        patientMutableLiveData = new MutableLiveData<>();
        CompletableFuture<List<HospitalInfo>> info = HospitalTask.getInfo("http://124.93.196.45:10001/prod-api/api/hospital/hospital/list");
        info.thenAccept(hospitalInfoListLiveData::postValue);
    }

    public MutableLiveData<List<HospitalLunBo>> getHospitalLunBoListLiveData (){return hospitalLunBoListLiveData;}
    public MutableLiveData<List<HospitalInfo>> getHospitalInfoListLiveData(){return hospitalInfoListLiveData;}
    public MutableLiveData<HospitalInfo> getHospitalInfoMutableLiveData(){return hospitalInfoMutableLiveData;}
    public MutableLiveData<UserInfo> getUserInfoMutableLiveData(){return userInfoMutableLiveData;}
    public MutableLiveData<List<Patient>> getListPatientMutableLiveData(){return listPatientMutableLiveData;}
    public MutableLiveData<Patient> getPatientMutableLiveData(){return patientMutableLiveData;}

    public void setHospitalLunBoListLiveData(int id){
        CompletableFuture<List<HospitalLunBo>> lunBo = HospitalTask.getLunBo("http://124.93.196.45:10001/prod-api/api/hospital/banner/list?hospitalId="+id);
        lunBo.thenAccept(hospitalLunBoListLiveData::postValue);
    }

    public void setHospitalInfoMutableLiveData(int id){
        CompletableFuture<HospitalInfo> infoCompletableFuture = HospitalTask.getInfoById("http://124.93.196.45:10001/prod-api/api/hospital/hospital/"+id);
        infoCompletableFuture.thenAccept(hospitalInfoMutableLiveData::postValue);
    }

    public void setUserInfoMutableLiveData(String token){
        CompletableFuture<UserInfo> userInfoCompletableFuture = MyDataLoad.getUserInfo("http://124.93.196.45:10001/prod-api/api/common/user/getInfo",token);
        userInfoCompletableFuture.thenAccept(userInfoMutableLiveData::postValue);
    }

    public void setListPatientMutableLiveData(String token){
        CompletableFuture<List<Patient>> completableFuture = HospitalTask.getPatient("http://124.93.196.45:10001/prod-api/api/hospital/patient/list",token);
        completableFuture.thenAccept(listPatientMutableLiveData::postValue);
    }

    public void setPatientMutableLiveData(int id){
        List<Patient> value = listPatientMutableLiveData.getValue();
        patientMutableLiveData.setValue(value.get(id));
        Log.d("TAG", "setPatientMutableLiveData: "+value.get(id).getName());
    }

}
