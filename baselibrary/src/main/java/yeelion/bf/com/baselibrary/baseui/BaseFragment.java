package yeelion.bf.com.baselibrary.baseui;

import android.os.Message;
import android.support.v4.app.Fragment;

import yeelion.bf.com.baselibrary.handler.IHandlerMessage;
import yeelion.bf.com.baselibrary.mvp.IBaseView;

/**
 * 作者：Kurt on 2018/7/18 21:34
 * 邮箱：876506231@qq.com
 */
public class BaseFragment extends Fragment implements IBaseView, IHandlerMessage {

    // TODO: 2018/7/18
    @Override
    public void showToast(String str) {
    }

    @Override
    public void showToast(int resId) {

    }

    @Override
    public void showProgressDialog(int resId) {

    }

    @Override
    public void dismissProgressDialog() {

    }

    @Override
    public void handlerCallback(Message msg) {

    }
}
