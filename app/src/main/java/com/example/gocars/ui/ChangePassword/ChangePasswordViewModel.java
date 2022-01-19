package com.example.gocars.ui.ChangePassword;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ChangePasswordViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ChangePasswordViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Password fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
