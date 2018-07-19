package com.more.fun;

import android.view.View;

import yeelion.bf.com.baselibrary.baseui.BaseViewActivity;

public class Main2Activity extends BaseViewActivity {


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
    }


    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }
}
