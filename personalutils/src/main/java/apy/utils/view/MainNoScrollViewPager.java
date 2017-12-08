package apy.utils.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by apy on 2017/7/20.
 * 主界面的ViewPager禁止滑动
 */

public class MainNoScrollViewPager extends ViewPager {
    public MainNoScrollViewPager(Context context) {
        super(context);
    }

    public MainNoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;//不拦击事件
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;//不处理事件
    }
}
