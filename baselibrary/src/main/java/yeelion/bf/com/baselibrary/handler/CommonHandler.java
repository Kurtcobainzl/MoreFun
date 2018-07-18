package yeelion.bf.com.baselibrary.handler;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;

import java.lang.ref.WeakReference;

/**
 * 作者：Kurt on 2018/7/18 22:25
 * 邮箱：876506231@qq.com
 */
public class CommonHandler<T extends IHandlerMessage> extends Handler {
    private static final String TAG = CommonHandler.class.getSimpleName();

    private WeakReference<T> reference;
    private String className;

    public CommonHandler(T t) {
        className = t.getClass().getSimpleName();
        reference = new WeakReference<T>(t);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if (reference == null) {
            return;
        }
        T t = reference.get();
        if (t == null) {
            return;
        }
        if (t instanceof Fragment) {
            Fragment fragment = (Fragment) t;
            if (fragment.getActivity() == null || !(fragment).isAdded()) {
                return;
            }
        }
        t.handlerCallback(msg);

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
