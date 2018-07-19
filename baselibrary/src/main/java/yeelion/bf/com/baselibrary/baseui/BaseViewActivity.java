package yeelion.bf.com.baselibrary.baseui;

import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import yeelion.bf.com.baselibrary.R;

/**
 * 作者：Kurt on 2018/7/18 21:42
 * 邮箱：876506231@qq.com
 */
public abstract class BaseViewActivity extends BaseActivity {
    protected static final int MODE_LEFT = 0;
    protected static final int MODE_TITLE = 1;
    protected static final int MODE_RIGHT = 2;
    protected static final int MODE_BASE_LINE = 3;

    protected LinearLayout mContentLayout, RootView;
    //顶部导航栏，顶部导航栏底部横线
    protected View mTopBarView, mTopBaseLineView;
    protected TextView mTopLeftTv, mTopRightTv, mTitleTv;

    /**
     * 控制顶部导航栏及其内部组件的展示
     *
     * @param mode
     */
    protected void setMode(int... mode) {

        if (mode.length == 0) {
            mTopBarView.setVisibility(View.GONE);
            return;
        }
        //初始化，全部隐藏
        mTopBaseLineView.setVisibility(View.GONE);
        mTopLeftTv.setVisibility(View.GONE);
        mTopRightTv.setVisibility(View.GONE);
        mTitleTv.setVisibility(View.GONE);
        mTopBarView.setVisibility(View.VISIBLE);

        for (int i : mode) {
            switch (i) {
                case MODE_LEFT:
                    mTopLeftTv.setVisibility(View.VISIBLE);
                    break;
                case MODE_RIGHT:
                    mTopRightTv.setVisibility(View.VISIBLE);
                    break;
                case MODE_TITLE:
                    mTitleTv.setVisibility(View.VISIBLE);
                    break;
                case MODE_BASE_LINE:
                    mTopBaseLineView.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }


    @Override
    public void setContentView(int layoutResID) {
        setContentView(layoutResID, R.color.white);
    }

    /**
     * @param layoutResID
     * @param barCompatColor
     */
    public void setContentView(int layoutResID, int barCompatColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.status_bar_background));
        }

        super.setContentView(R.layout.activity_base_top);

        getSupportActionBar().hide();
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        RootView = (LinearLayout) findViewById(R.id.root_view);
        mContentLayout = (LinearLayout) findViewById(R.id.body_content);
//        View mContentView =
        View.inflate(this, layoutResID, mContentLayout);
//        setStatusBarCompat(barCompatColor);  //TODO 沉浸模式
        ButterKnife.bind(this, RootView);
        mTopBarView = findViewById(R.id.top_bar_base);
        mTopBaseLineView = findViewById(R.id.top_base_line);
        mTopLeftTv = findViewById(R.id.top_left);
        mTopRightTv = findViewById(R.id.top_right);
        mTitleTv = findViewById(R.id.top_title);
    }


    /**
     * 常用的Mode配置之一
     */
    protected void setLeftTitleMode() {
        setMode(MODE_LEFT, MODE_TITLE);
    }

    /**
     * 常用的Mode配置之二
     */
    protected void setSingleTitleMode() {
        setMode(MODE_TITLE);
    }

    /**
     * 常用的Mode配置之三
     */
    protected void setLeftTitleRightMode() {
        setMode(MODE_LEFT, MODE_TITLE, MODE_RIGHT);
    }

    /**
     * 常用的Mode配置之四
     */
    protected void setTitleRightMode() {
        setMode(MODE_TITLE, MODE_RIGHT);
    }

}
