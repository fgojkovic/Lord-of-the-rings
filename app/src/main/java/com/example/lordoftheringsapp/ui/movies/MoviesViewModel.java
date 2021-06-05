package com.example.lordoftheringsapp.ui.movies;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;

import com.example.lordoftheringsapp.models.movieModels.Movie;

public class MoviesViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    //private Movie movie;
    //public final LiveData movieLiveData;

    public MoviesViewModel() {
        /*movieLiveData = new LivePagedListBuilder(
                movie.getName(), 2).build();*/
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

}