package com.more.fun.common;

import android.app.Application;

/**
 * Created by Kurt on 2018/7/19 下午6:23
 */

public class MyApplication extends Application {
    public static MyApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }
}
