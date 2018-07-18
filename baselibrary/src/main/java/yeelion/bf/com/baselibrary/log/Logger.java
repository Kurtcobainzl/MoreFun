package yeelion.bf.com.baselibrary.log;

import android.content.Context;
import android.util.Log;

import yeelion.bf.com.baselibrary.R;

/**
 * 作者：Kurt on 2018/7/3 15:45
 * 邮箱：876506231@qq.com
 */
public class Logger {
    private static boolean logable = false;

    public static void init(Context context) {
        logable = context.getResources().getBoolean(R.bool.log_able);
    }

    public static void e(String tag, String msg) {
        if (logable) Log.e(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (logable) Log.i(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (logable) Log.v(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (logable) Log.w(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (logable) Log.d(tag, msg);
    }
}
