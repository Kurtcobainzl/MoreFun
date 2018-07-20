package com.more.fun.common;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Kurt on 2018/7/19 下午6:22
 */

public class SharedPreferencesManager {
    private volatile static SharedPreferencesManager INSTANCE;
    private static final String PREFERENCE_NAME = "preferences_hxcx";
    private static SharedPreferences mPreferences;

    private SharedPreferencesManager() {
        mPreferences = MyApplication.mApplication.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public static SharedPreferencesManager getInstance() {
        if (INSTANCE == null) {
            synchronized (SharedPreferencesManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SharedPreferencesManager();
                }
            }
        }
        return INSTANCE;
    }

    //-----------线上为单例初始化-------------线下为功能部分--------------------------------------------------

    private static final String TEST = "test";


    void seetTest(String test) {
        mPreferences.edit().putString(TEST, test).commit();
    }

    String getTest() {
        return mPreferences.getString(TEST, "");
    }


}
