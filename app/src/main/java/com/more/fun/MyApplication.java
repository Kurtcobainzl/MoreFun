package com.more.fun;

import yeelion.bf.com.baselibrary.BaseApplication;
import yeelion.bf.com.baselibrary.http.HttpConfig;
import yeelion.bf.com.baselibrary.log.Logger;
import yeelion.bf.com.framelibrary.http.OKHttpEngine;

/**
 * Created by Kurt on 2018/7/20 下午8:17
 */

public class MyApplication extends BaseApplication {
    public static MyApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        //初始化第三方库
        Logger.init(this);
        HttpConfig.initEngine(new OKHttpEngine());


    }
}
