package yeelion.bf.com.framelibrary.http;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import yeelion.bf.com.baselibrary.http.HttpCallBack;
import yeelion.bf.com.baselibrary.http.HttpConfig;
import yeelion.bf.com.baselibrary.http.HttpEngine;
import yeelion.bf.com.baselibrary.log.Logger;
import yeelion.bf.com.baselibrary.utils.GsonUtils;

/**
 * 作者：Kurt on 2018/7/3 14:02
 * 邮箱：876506231@qq.com
 */
public class OKHttpEngine implements HttpEngine {
    private static final long TIMEOUT = 30L;
    private static final String TAG = OKHttpEngine.class.getSimpleName();

    @Override
    public <T> void startLoad(HttpConfig httpConfig, HttpCallBack<T> httpCallBack) {
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(TIMEOUT, TimeUnit.SECONDS).build();
        switch (httpConfig.getmLoadType()) {
            case HttpConfig.TYPE_POST:
                post(client, httpConfig, httpCallBack);
                break;
            case HttpConfig.TYPE_PUT:
                put(client, httpConfig, httpCallBack);
                break;
            case HttpConfig.TYPE_DELETE:
                delete(client, httpConfig, httpCallBack);
                break;
            case HttpConfig.TYPE_GET:
            default:
                get(client, httpConfig, httpCallBack);
                break;

        }
    }

    private <T> void get(OkHttpClient client, HttpConfig httpConfig, final HttpCallBack<T> callBack) {
        Request request = new Request.Builder().headers(OkEngineUtils.getHeaders(httpConfig.getHeaderParams())).url(OkEngineUtils.getUrlWithParams(httpConfig.getmUrl(), httpConfig.getParams())).get().build();
        clientCall(client, request, callBack);

    }


    private <T> void post(OkHttpClient client, HttpConfig httpConfig, HttpCallBack<T> callBack) {
        FormBody.Builder builder = OkEngineUtils.getFormBodyBuilder(httpConfig.getParams());
        RequestBody formBody = builder.build();
        Request request = new Request.Builder().headers(OkEngineUtils.getHeaders(httpConfig.getHeaderParams())).url(httpConfig.getmUrl()).post(formBody).build();
        clientCall(client, request, callBack);
    }

    private <T> void put(OkHttpClient client, HttpConfig httpConfig, HttpCallBack<T> callBack) {

    }

    private <T> void delete(OkHttpClient client, HttpConfig httpConfig, HttpCallBack<T> callBack) {

    }


    /**
     * 开始发起请求
     *
     * @param client
     * @param request
     * @param callBack
     * @param <T>
     */
    private <T> void clientCall(OkHttpClient client, Request request, final HttpCallBack<T> callBack) {
        callBack.onStart();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFail(e.getMessage());
                callBack.onFinish();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String json = response.body().string();
                    Logger.e(TAG, json);
                    T bean = GsonUtils.getInstance().fromJson(json, callBack.getType());
                    callBack.onSuccess(bean);
                } catch (Exception e) {
                    callBack.onFail(e.toString());
                } finally {
                    callBack.onFinish();
                }
            }
        });
    }
}
