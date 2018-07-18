package yeelion.bf.com.baselibrary.imageload.listener;

/**
 * 通知图片加载进度
 */
public interface ProgressLoadListener {

    void update(int bytesRead, int contentLength);

    void onException();

    void onResourceReady();
}
