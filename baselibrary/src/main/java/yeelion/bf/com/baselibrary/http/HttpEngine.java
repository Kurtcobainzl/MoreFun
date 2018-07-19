package yeelion.bf.com.baselibrary.http;

/**
 * 作者：Kurt on 2018/7/3 13:03
 * 邮箱：876506231@qq.com
 */
public interface HttpEngine {
     <T extends Object> void startLoad(HttpConfig httpConfig, HttpCallBack<T> httpCallBack);
}
