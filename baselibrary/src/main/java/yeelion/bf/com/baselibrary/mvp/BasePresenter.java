package yeelion.bf.com.baselibrary.mvp;

import android.app.Activity;

/**
 * 作者：Kurt on 2018/7/18 22:20
 * 邮箱：876506231@qq.com
 */
public class BasePresenter<T extends IBaseView> {
    protected T mView;

    public BasePresenter(T view) {
        this.mView = view;
    }

    public void showToast(String str) {
        mView.showToast(str);
    }

    public void showToast(int resId) {
        mView.showToast(resId);
    }

    public void showProgressDialog(int resId) {
        mView.showProgressDialog(resId);
    }

    public void dismissProgressDialog() {
        mView.dismissProgressDialog();
    }

    public Activity getContext() {
        return (Activity) mView;
    }


}

