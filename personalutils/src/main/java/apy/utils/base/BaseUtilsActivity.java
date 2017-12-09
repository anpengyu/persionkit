package apy.utils.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import apy.utils.R;
import apy.utils.listener.OnLoadDataListener;
import apy.utils.net.BaseResponse;
import apy.utils.utils.SystemBarTintManager;
import apy.utils.utils.UIUtils;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/17.
 * 所有Activity鸡肋，啥都有。。。。
 */

public abstract class BaseUtilsActivity<T extends BaseResponse> extends AppCompatActivity  implements OnLoadDataListener<T> {

    //页面标题栏
    public RelativeLayout baseTitleRl;
    public View mView;
    private TextView baseTitleTv;
    public TextView baseTitleRight;
    public ImageView baseTitleRightIv;
    public FrameLayout mContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        baseTitleTv = (TextView) findViewById(R.id.base_title_tv);
        baseTitleRl = (RelativeLayout) findViewById(R.id.base_title_rl);
        mContainer = (FrameLayout) findViewById(R.id.base_container);
        baseTitleRight = (TextView) findViewById(R.id.base_title_right_tv);
        baseTitleRightIv = (ImageView) findViewById(R.id.base_title_right_Iv);
        mView = View.inflate(this, getViewId(), null);
        mContainer.addView(mView);
        ButterKnife.bind(this);
        setTitleTv();
        initView();
        initData();
        initBottom();
        controlTitleRl();
        EventBus.getDefault().register(this);
    }

    public void initBottom() {

    }

    public void initView() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Object event) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void initStatusBar(ViewGroup rootRl){
        int statusBar = UIUtils.getStatusBar(this);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rootRl.getLayoutParams();
        layoutParams.topMargin = statusBar;
        rootRl.setLayoutParams(layoutParams);
    }

    public void setStatusBar(int color){
        // 透明状态栏
        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(color);//通知栏颜色
    }

    public void back(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            finish();
        }
    }

    //退出时的时间
    private long mExitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(UIUtils.getTopActivity(this).getClassName().contains("MainActivity")){
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                exit();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
    /**
     * 设置布局
     * @return
     */
    protected abstract int getViewId();

    /**
     * 设置标题栏标题
     * @return
     */
    public String rightText() {
        return "默认";
    }

    private void setTitleTv() {
        baseTitleTv.setText(title());
    }

    protected abstract String title();

    /**
     * 控制是否显示标题栏
     * @return
     */
    protected abstract boolean controlTitle();


    /**
     * 加载数据
     */
    protected abstract void initData();


    public void controlTitleRl() {
        if (controlTitle()) {
            baseTitleRl.setVisibility(View.VISIBLE);
        } else {
            baseTitleRl.setVisibility(View.GONE);
        }
    }

    public void controlTitleRlIv() {
        baseTitleRightIv.setVisibility(View.VISIBLE);
        baseTitleRightIv.setImageResource(rightImg());
    }

    public int rightImg() {
        return 0;
    }

    public  void startThisActivity(Class<? extends AppCompatActivity> clazz){
        Intent intent = new Intent(this,clazz);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    public  void startThisActivityForResult(Class<? extends AppCompatActivity> clazz,int requestCode){
        Intent intent = new Intent(this,clazz);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivityForResult(intent,requestCode);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
//            System.exit(0);
        }
    }

    @Override
    public void onSuccess(boolean state) {

    }
}
