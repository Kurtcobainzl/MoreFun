package yeelion.bf.com.baselibrary.utils;

/**
 * 作者：Kurt on 2018/7/3 14:55
 * 邮箱：876506231@qq.com
 */

import com.google.gson.Gson;

/**
 * 作者：Kurt on 2018/6/21 14:45
 * 邮箱：876506231@qq.com
 */
public class GsonUtils {
    public static  Gson instance;

    public static Gson getInstance() {
        if (instance==null){
            synchronized (GsonUtils.class){
                if (instance==null)
                    instance=new Gson();
            }
        }
    return instance;
    }

}
