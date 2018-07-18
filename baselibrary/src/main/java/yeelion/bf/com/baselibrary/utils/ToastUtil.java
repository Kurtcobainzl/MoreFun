package yeelion.bf.com.baselibrary.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 作者：Kurt on 2018/7/18 22:17
 * 邮箱：876506231@qq.com
 */

public final class ToastUtil {

    public static void showToast(Context context, int resourceId) {
        if (context == null) {
            return;
        }
        Toast.makeText(context, resourceId, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Context context, String message) {
        if (context == null) {
            return;
        }
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Context context, String message, int duration) {
        if (context == null) {
            return;
        }
        Toast.makeText(context, message, duration).show();
    }

}

