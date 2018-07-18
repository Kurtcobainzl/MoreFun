package yeelion.bf.com.framelibrary.ImageLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import yeelion.bf.com.baselibrary.imageloader.BaseImageLoaderStrategy;
import yeelion.bf.com.baselibrary.imageloader.listener.ImageBitmapListener;
import yeelion.bf.com.baselibrary.imageloader.listener.ImageSaveListener;
import yeelion.bf.com.baselibrary.imageloader.listener.ProgressLoadListener;
import yeelion.bf.com.baselibrary.imageloader.listener.SourceReadyListener;

/**
 * 作者：Kurt on 2018/7/4 15:04
 * 邮箱：876506231@qq.com
 */
public class GlideImageLoaderStrategy implements BaseImageLoaderStrategy {
    @Override
    public void loadImage(String url, ImageView imageView) {
        // TODO: 2018/7/4
        RequestOptions options = new RequestOptions().error(android.R.drawable.stat_notify_error).placeholder(android.R.drawable.btn_star).priority(Priority.HIGH).centerCrop().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).dontAnimate();
        Glide.with(imageView.getContext()).load(url).apply(options).into(imageView);
    }

    @Override
    public void loadImage(int url, ImageView imageView) {
        RequestOptions options = new RequestOptions().error(android.R.drawable.stat_notify_error).placeholder(android.R.drawable.btn_star).priority(Priority.HIGH).centerCrop().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).dontAnimate();
        Glide.with(imageView.getContext()).load(url).apply(options).into(imageView);
    }

    @Override
    public void loadImageAsGif(int url, ImageView imageView) {
        loadImage(url, imageView);
    }

    @Override
    public void loadImageWithAppCxt(String url, ImageView imageView) {
        loadImage(url, imageView);
    }

    @Override
    public void loadImage(String url, int placeholder, ImageView imageView) {
        loadNormal(imageView.getContext(), url, placeholder, imageView);
    }

    @Override
    public void loadImage(Context context, String url, int placeholder, ImageView imageView) {
        loadNormal(context, url, placeholder, imageView);
    }

    @Override
    public void loadCircleImage(String url, int placeholder, ImageView imageView) {
        RequestOptions options = new RequestOptions().error(android.R.drawable.stat_notify_error).placeholder(android.R.drawable.btn_star).priority(Priority.HIGH).centerCrop().bitmapTransform(new CircleCrop())//
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE).dontAnimate();

        Glide.with(imageView.getContext()).load(url).apply(options)
                .into(imageView);

    }

    @Override
    public void loadCircleBorderImage(String url, int placeholder, ImageView imageView, float borderWidth, int borderColor) {
    }

    @Override
    public void loadCircleBorderImage(String url, int placeholder, ImageView imageView, float borderWidth, int borderColor, int heightPx, int widthPx) {

    }

    @Override
    public void loadGifImage(String url, int placeholder, ImageView imageView) {
        loadImage(url, placeholder, imageView);
    }

    @Override
    public void loadImageWithProgress(String url, ImageView imageView, ProgressLoadListener listener) {

    }

    @Override
    public void loadImageWithPrepareCall(String url, ImageView imageView, int placeholder, SourceReadyListener listener) {

    }

    @Override
    public void loadImageDisplay(String url, Context context, SourceReadyListener listener) {

    }

    @Override
    public Bitmap loadImageBitmap(String url, Context context, ImageBitmapListener listener) {
        return null;
    }

    @Override
    public void loadGifWithPrepareCall(String url, ImageView imageView, SourceReadyListener listener) {

    }

    @Override
    public void loadImageWithPix(String url, ImageView imageView, int widthPix, int heightPix) {

    }

    @Override
    public void clearImageDiskCache(Context context) {

    }

    @Override
    public void clearImageMemoryCache(Context context) {

    }

    @Override
    public void trimMemory(Context context, int level) {

    }

    @Override
    public String getCacheSize(Context context) {
        return null;
    }

    @Override
    public void saveImage(Context context, String url, String savePath, String saveFileName, ImageSaveListener listener) {

    }


    /**
     * load image with Glide
     */
    private void loadNormal(Context context, String url, int placeholder, ImageView imageView) {
/**
 *  为其添加缓存策略,其中缓存策略可以为:Source及None,None及为不缓存,Source缓存原型.如果为ALL和Result就不行.然后几个issue的连接:
 https://github.com/bumptech/glide/issues/513
 https://github.com/bumptech/glide/issues/281
 https://github.com/bumptech/glide/issues/600
 modified by xuqiang
 */

        if (TextUtils.isEmpty(url)) {
            loadImage(placeholder, imageView);
            return;
        }
        RequestOptions options = new RequestOptions().error(placeholder).placeholder(placeholder).priority(Priority.HIGH).centerCrop().diskCacheStrategy(DiskCacheStrategy.RESOURCE).dontAnimate();
        Glide.with(context).load(url).apply(options).into(imageView);
    }

}
