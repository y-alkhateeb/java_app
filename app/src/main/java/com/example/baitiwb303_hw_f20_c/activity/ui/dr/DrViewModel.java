package com.example.baitiwb303_hw_f20_c.activity.ui.dr;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DrViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DrViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is doctor fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

}