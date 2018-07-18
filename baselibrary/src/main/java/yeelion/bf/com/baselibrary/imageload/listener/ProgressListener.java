package yeelion.bf.com.baselibrary.imageload.listener;
/**
 * @author  "https://github.com/square/okhttp/blob/master/samples/guide/src/main/java/okhttp3/recipes/Progress.java"
 * @see <a href="https://github.com/square/okhttp/blob/master/samples/guide/src/main/java/okhttp3/recipes/Progress.java">OkHttp sample</a>
 * 通知下载进度
 */
interface ProgressListener {
    void update(long bytesRead, long contentLength, boolean done);
}
