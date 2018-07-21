package com.more.fun;

import android.os.Message;
import android.view.View;

import yeelion.bf.com.baselibrary.baseui.BaseViewActivity;
import yeelion.bf.com.baselibrary.handler.CommonHandler;
import yeelion.bf.com.baselibrary.handler.IHandlerMessage;
import yeelion.bf.com.baselibrary.log.Logger;

/**
 * test
 */
public class Main2Activity extends BaseViewActivity implements IHandlerMessage {


    @Override
    public void initView() {
        setContentView(R.layout.activity_main2);
        setTitleRightMode();
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        MainActivity.getCCC(this);
    }

    CommonHandler<Main2Activity> handler = new CommonHandler<>(this);


    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
//        MainActivity.getCCC(this);
//        handler.sendEmptyMessageDelayed(1, 1000);

    }

    @Override
    public void handlerCallback(Message msg) {
        Logger.e("NIRVANA", "main2--->" + msg.what);
//        Main2Activity.this.finish();

    }
}
