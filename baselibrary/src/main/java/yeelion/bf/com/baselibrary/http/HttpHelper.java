package yeelion.bf.com.baselibrary.http;

import android.content.Context;

/**
 * 作者：Kurt on 2018/7/3 13:03
 * 邮箱：876506231@qq.com
 */
public class HttpHelper {


    public static HttpConfig with(Context context) {
        return new HttpConfig(context);
    }

}
