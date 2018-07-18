package yeelion.bf.com.baselibrary.http;

import java.lang.reflect.Type;

/**
 * 作者：Kurt on 2018/7/3 13:03
 * 邮箱：876506231@qq.com
 */
public abstract class HttpCallBack<T> {

    public Type getType() {
        return type;
    }

    private Type type;

    public HttpCallBack(Type type) {
        this.type = type;
    }

    public void onStart() {

    }

    public abstract void onSuccess(T t);

    public void onFail(String str) {

    }

    public void onFinish() {

    }

}
