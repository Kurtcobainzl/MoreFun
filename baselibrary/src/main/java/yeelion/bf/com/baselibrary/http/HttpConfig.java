package yeelion.bf.com.baselibrary.http;

import android.content.Context;

import com.google.gson.JsonElement;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：Kurt on 2018/7/3 13:02
 * 邮箱：876506231@qq.com
 */
public class HttpConfig {

    private Context context;
    private String mUrl;
    private boolean mCache;
    private int mLoadType;
    public static final int TYPE_GET = 0x0001;
    public static final int TYPE_POST = 0x0002;
    public static final int TYPE_PUT = 0x0003;
    public static final int TYPE_DELETE = 0x0004;
    Map<String, Object> params = new HashMap<>();
    Map<String, String> headerParams = new HashMap<>();
    private static HttpEngine mHttpEngine;


    public HttpConfig(Context context) {
        this.context = context;
    }

    public static void initEngine(HttpEngine httpEngine) {
        mHttpEngine = httpEngine;
    }

    public HttpConfig url(String url) {
        this.mUrl = url;
        return this;
    }

    public HttpConfig needCatch(boolean needCatch) {
        this.mCache = needCatch;
        return this;
    }

    public HttpConfig putParam(String key, Object value) {
        params.put(key, value);
        return this;
    }

    public HttpConfig putParams(Map<String, Object> params) {
        if (params != null && params.size() > 0) {
            this.params = params;
        }
        return this;
    }

    public HttpConfig header(String key, String value) {
        this.headerParams.put(key, value);
        return this;
    }

    public HttpConfig headers(Map<String, String> headerParams) {
        if (headerParams != null && headerParams.size() > 0) {
            this.headerParams = headerParams;
        }
        return this;
    }

    public HttpConfig get() {
        mLoadType = TYPE_GET;
        return this;
    }

    public HttpConfig post() {
        mLoadType = TYPE_POST;
        return this;
    }

    public HttpConfig put() {
        mLoadType = TYPE_PUT;
        return this;
    }

    public HttpConfig delete() {
        mLoadType = TYPE_DELETE;
        return this;
    }

    public void execute() {
        execute(null);
    }

    public <T extends Object> void execute(HttpCallBack<T> httpCallBack) {
        if (httpCallBack == null) {
            httpCallBack = (HttpCallBack<T>) new HttpCallBack<JsonElement>(JsonElement.class) {
                @Override
                public void onSuccess(JsonElement jsonElement) {

                }
            };
        }
        startLoad(this, httpCallBack);
    }

    private <T extends Object> void startLoad(HttpConfig httpConfig, HttpCallBack<T> httpCallBack) {
        mHttpEngine.startLoad(httpConfig, httpCallBack);
    }


    public Context getContext() {
        return context;
    }

    public String getmUrl() {
        return mUrl;
    }

    public boolean ismCache() {
        return mCache;
    }

    public int getmLoadType() {
        return mLoadType;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public Map<String, String> getHeaderParams() {
        return headerParams;
    }

    @Override
    public String toString() {
        return "HttpConfig{" + "context=" + context + ", mUrl='" + mUrl + '\'' + ", mCache=" + mCache + ", mLoadType=" + mLoadType + ", params=" + params + ", headerParams=" + headerParams + '}';
    }
}
