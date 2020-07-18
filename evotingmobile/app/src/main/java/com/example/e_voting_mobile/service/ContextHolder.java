package com.example.e_voting_mobile.service;

import android.app.Application;
import android.content.Context;

public class ContextHolder extends Application {
    private static ContextHolder contextHolder = null;

    @Override
    public void onCreate() {
        super.onCreate();
        contextHolder = this;
    }

    public static Context getContext(){
        return contextHolder.getApplicationContext();
    }
}