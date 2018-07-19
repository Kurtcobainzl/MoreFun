package yeelion.bf.com.baselibrary.baseui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import yeelion.bf.com.baselibrary.R;
import yeelion.bf.com.baselibrary.mvp.IBaseView;
import yeelion.bf.com.baselibrary.utils.ToastUtil;

/**
 * 作者：Kurt on 2018/7/18 21:33
 * 邮箱：876506231@qq.com
 * <p>
 * 规范使用逻辑，这一层不对UI相关内容进行管理
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseView {
    protected Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        initView();
        initData();
        initListener();

    }


    public abstract void initView();

    public abstract void initListener();

    public abstract void initData();


    public void startActivity(Class<?> clazz) {
        startActivity(new Intent(this, clazz));
        overridePendingTransition(R.anim.activity_stay, R.anim.activity_top_to_bottom);
        //动画
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_stay, R.anim.activity_top_to_bottom);
    }

    public void finish(int enterAnim, int exitAnim) {
        super.finish();
        overridePendingTransition(enterAnim, exitAnim);
    }


    @Override
    public void showToast(String str) {
        ToastUtil.showToast(mActivity, str);
    }

    @Override
    public void showToast(final int resId) {
        if (!Thread.currentThread().getName().equals("main")) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ToastUtil.showToast(mActivity, resId);
                }
            });
        } else {
            ToastUtil.showToast(mActivity, resId);
        }
    }

    @Override
    public void showProgressDialog(int resId) {
// TODO: 2018/7/18  
    }

    @Override
    public void dismissProgressDialog() {
// TODO: 2018/7/18  
    }
}
