package com.example.baitiwb303_hw_f20_c.activity.ui.course;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CourseViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CourseViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is course fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}