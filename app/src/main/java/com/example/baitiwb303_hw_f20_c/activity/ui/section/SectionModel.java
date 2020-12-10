package com.example.baitiwb303_hw_f20_c.activity.ui.section;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SectionModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SectionModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is section fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}