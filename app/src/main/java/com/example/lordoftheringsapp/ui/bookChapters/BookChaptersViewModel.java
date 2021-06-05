package com.example.lordoftheringsapp.ui.bookChapters;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BookChaptersViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BookChaptersViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is book chapter fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
