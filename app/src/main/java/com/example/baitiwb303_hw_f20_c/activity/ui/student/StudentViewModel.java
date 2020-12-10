package com.example.baitiwb303_hw_f20_c.activity.ui.student;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StudentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public StudentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is student fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}