package com.example.gocars;

import android.app.Application;

public class global_vars extends Application {
    private int LoginUserID;

    public int getLoginUserID() {
        return LoginUserID;
    }

    public void setLoginUserID(int LoginUserID) {
        this.LoginUserID = LoginUserID;
    }
}
