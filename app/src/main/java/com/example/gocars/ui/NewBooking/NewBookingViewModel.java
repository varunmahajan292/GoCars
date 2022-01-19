package com.example.gocars.ui.NewBooking;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewBookingViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NewBookingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is New Booking fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
