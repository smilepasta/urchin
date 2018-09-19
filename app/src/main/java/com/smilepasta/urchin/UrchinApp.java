package com.smilepasta.urchin;

import android.app.Application;

/**
 * Author: huangxiaoming
 * Date: 2018/9/18
 * Desc:
 * Version: 1.0
 */
public class UrchinApp extends Application {

    private static UrchinApp instance;

    public static UrchinApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
