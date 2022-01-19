package com.example.gocars.ui.MyBooking;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyBookingViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MyBookingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is My Booking fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
