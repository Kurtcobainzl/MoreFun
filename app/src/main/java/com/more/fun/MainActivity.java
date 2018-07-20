package com.more.fun;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.BinderThread;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.more.fun.bean.TestBean;

import butterknife.Bind;
import yeelion.bf.com.baselibrary.baseui.BaseViewActivity;
import yeelion.bf.com.baselibrary.handler.CommonHandler;
import yeelion.bf.com.baselibrary.handler.IHandlerMessage;
import yeelion.bf.com.baselibrary.http.HttpCallBack;
import yeelion.bf.com.baselibrary.http.HttpConfig;
import yeelion.bf.com.baselibrary.http.HttpHelper;
import yeelion.bf.com.baselibrary.utils.ToastUtil;
import yeelion.bf.com.framelibrary.http.OKHttpEngine;

public class MainActivity extends BaseViewActivity implements IHandlerMessage {

    @Bind(R.id.web)
    BridgeWebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HttpConfig.initEngine(new OKHttpEngine());
        HttpHelper.with(this).url("http://app.baicm.com.cn/app/check_update")
                .putParam("platform", "android")
                .putParam("timestamp", "1530602208604")
                .putParam("mobileSystem", "Android")
                .putParam("imei", "99001020018787")
                .putParam("version", "3.9.5")
                .putParam("operatorId", "1002")
                .post().execute(new HttpCallBack<TestBean>(TestBean.class) {
            @Override
            public void onSuccess(TestBean s) {
                Log.e("OKHttpEngine", "onSuccess:" + s.toString());
            }

            @Override
            public void onFail(String str) {
                super.onFail(str);
            }
        });


        Glide.with(this).load("").into(new ImageView(this));
        Glide.with(this).load("").listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                return false;
            }
        });
//        Glide.with(this).load("")
        //.listener().into()
        //.into(new ImageView(this));

//        HttpHelper.with(this).url("http://www.baidu.com").header("key", "value").putParam("kkey", "vvalue").get().execute(new HttpCallBack<String>(String.class) {
//            @Override
//            public void onSuccess(String s) {
//            }
//        });


//        commonHandler.sendEmptyMessage(i);
    }

    int i = 0;
    CommonHandler<MainActivity> commonHandler = new CommonHandler<>(this);


    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        setLeftTitleRightMode();
    }

    @Override
    public void initListener() {
        findViewById(R.id.iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToastCenter(MainActivity.this,"捐献");
//                startActivity(Main2Activity.class);

            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void handlerCallback(Message msg) {
        switch (msg.what % 5) {
            case 0:
                setMode();
                break;
            case 1:
                setLeftTitleMode();
                break;
            case 2:
                setLeftTitleRightMode();
                break;
            case 3:
                setTitleRightMode();
                break;
            case 4:
                setSingleTitleMode();
                break;
        }
//        i++;
//        commonHandler.sendEmptyMessageDelayed(i, 3000);
    }
}
