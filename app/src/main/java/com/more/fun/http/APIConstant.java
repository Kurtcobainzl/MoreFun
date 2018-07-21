package com.more.fun.http;

import static com.more.fun.http.Api_Type.*;

/**
 * Created by Kurt on 2018/7/19 下午6:28
 * 网络请求地址
 */

public class APIConstant {

    private static String HOST_URL, WEB_HOST_URL;
    private static Api_Type API_TYPE = DEV;// TODO: 此处切换域名

    //开发环境域名
    private static final String DEV_HOST_URL = "http://test-app.baicm.com.cn";
    private static final String DEV_WEB_HOST_URL = "http://test-app.baicm.com.cn";

    //测试环境域名
    private static final String TEST_HOST_URL = "http://test-app.baicm.com.cn";
    private static final String TEST_WEB_HOST_URL = "http://test-app.baicm.com.cn";

    //预发布环境域名
    private static final String PRE_RELEASE_HOST_URL = "";
    private static final String PRE_RELEASE_WEB_HOST_URL = "";

    //正式环境域名
    private static final String RELEASE_HOST_URL = "";
    private static final String RELEASE_WEB_HOST_URL = "";


    static {
        switch (API_TYPE) {
            case DEV:
                HOST_URL = "http://test-app.baicm.com.cn";
                WEB_HOST_URL = "http://test-app.baicm.com.cn";
                break;
            case TEST:
                HOST_URL = "http://test-app.baicm.com.cn";
                WEB_HOST_URL = "http://test-app.baicm.com.cn";
                break;
            case PRE_RELEASE:
                HOST_URL = "";
                WEB_HOST_URL = "";
                break;
            case RELEASE:
                HOST_URL = "";
                WEB_HOST_URL = "";
                break;
        }
    }


    //测试url
    public static final String TestUrl = HOST_URL + "/favPayTesst";
    public static final String TestWebUrl = WEB_HOST_URL + "/index.html";


}
