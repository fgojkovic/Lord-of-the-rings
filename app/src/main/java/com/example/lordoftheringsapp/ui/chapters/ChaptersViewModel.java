package com.example.lordoftheringsapp.ui.chapters;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ChaptersViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ChaptersViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is chapters fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
