package yeelion.bf.com.baselibrary.mvp;

/**
 * 作者：Kurt on 2018/7/18 22:15
 * 邮箱：876506231@qq.com
 */
public interface IBaseView {

    void showToast(String str);

    void showToast(int resId);

    void showProgressDialog(int resId);

    void dismissProgressDialog();
}
