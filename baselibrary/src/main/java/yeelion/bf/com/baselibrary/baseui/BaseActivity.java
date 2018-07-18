package yeelion.bf.com.baselibrary.baseui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import yeelion.bf.com.baselibrary.mvp.IBaseView;
import yeelion.bf.com.baselibrary.utils.ToastUtil;

/**
 * 作者：Kurt on 2018/7/18 21:33
 * 邮箱：876506231@qq.com
 * <p>
 * 规范使用逻辑，这一层不对UI相关内容进行管理
 */
public class BaseActivity extends AppCompatActivity implements IBaseView {
    protected Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
    }

    public void startActivity(Class<?> clazz) {
        startActivity(new Intent(this, clazz));
        //动画
    }

    @Override
    public void finish() {
        super.finish();
        //动画  overridePendingTransition
    }

    public void finish(int enterAnim, int exitAnim) {
        super.finish();
        //动画  overridePendingTransition
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
