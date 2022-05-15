package com.imikasa.movie;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.imikasa.movie.pojo.MovieDetail;
import com.imikasa.movie.pojo.MovieLunBo;
import com.imikasa.movie.task.MovieTask;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MovieViewModel extends ViewModel {

    private final MutableLiveData<List<MovieLunBo>> lunBoLiveData;
    private final MutableLiveData<List<MovieDetail>> listDetailLiveData;

    public MovieViewModel() {
        lunBoLiveData = new MutableLiveData<>();
        listDetailLiveData = new MutableLiveData<>();
        CompletableFuture<List<MovieLunBo>> lunbos = MovieTask.getLunBo("http://124.93.196.45:10001/prod-api/api/movie/rotation/list");
        lunbos.thenAccept(lunBoLiveData::postValue);
        CompletableFuture<List<MovieDetail>> details = MovieTask.getList("http://124.93.196.45:10001/prod-api/api/movie/film/list");
        details.thenAccept(listDetailLiveData::postValue);
    }

    public MutableLiveData<List<MovieLunBo>> getLunBoLiveData(){return lunBoLiveData;}
    public MutableLiveData<List<MovieDetail>> getListDetailLiveData(){return listDetailLiveData;}

}
