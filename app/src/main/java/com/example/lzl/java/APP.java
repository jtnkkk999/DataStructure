package com.example.lzl.java;

import android.app.Application;
import android.content.Context;

import com.example.lzl.java.savecrash.CrashHandler;

public class APP extends Application {
    private static Context mContext;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        CrashHandler.getInstance().init(this);
    }

    public static Context getContext() {
        return mContext;
    }
}
