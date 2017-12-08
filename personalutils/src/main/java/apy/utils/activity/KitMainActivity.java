package apy.utils.activity;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import apy.utils.R;
import apy.utils.base.BaseUtilsActivity;
import apy.utils.utils.UIUtils;
import apy.utils.view.MainNoScrollViewPager;
import apy.utils.view.TextDrawable;

/**
 * 主界面
 * 设置底部数据 initVpAdapter();
 * 参数1：adapter（FragmentPagerAdapter）
 * 参数2：底部文字（ViewPager长度）(String[])
 * 参数3：文字颜色(int[])
 * 1：默认颜色
 * 2：选中颜色
 * 参数:4：底部图片（int[][]）
 * 1：第一个条目图片
 * 1：默认图片
 * 2：选中图片
 * 底部状态栏颜色,高度 setBottomRootColor(int color,int height)
 * 底部状态栏图片宽高 setTopWidthAndHeight(int topWidth,int tipHeight)
 */
public abstract class KitMainActivity extends BaseUtilsActivity {
    ViewPager mainVp;
    TextDrawable mainPswRootTv;
    TextDrawable mainTallyRootTv;
    TextDrawable mainDiaryRootTv;
    TextDrawable mainMeRootTv;
    //底部导航文字
    private List<TextDrawable> mTvList = new ArrayList<>();
    private int pageSize = 4;
    private int[] ints;
    private int[][] ints1;
    private LinearLayout mainBottomRoot;

    @Override
    public void initView() {
        super.initView();
        mainVp = (MainNoScrollViewPager) findViewById(R.id.main_vp);
        mainPswRootTv = (TextDrawable) findViewById(R.id.main_psw_root);
        mainTallyRootTv = (TextDrawable) findViewById(R.id.main_tally_root);
        mainDiaryRootTv = (TextDrawable) findViewById(R.id.main_diary_root);
        mainMeRootTv = (TextDrawable) findViewById(R.id.main_me_root);
        mainBottomRoot = (LinearLayout) findViewById(R.id.main_bottom_root);
    }

    @Override
    public void initBottom() {
        super.initBottom();
        setSelector();
        mainPswRootTv.setSelected(true);
        mainPswRootTv.setTextColor(getResources().getColor(ints[1]));
        mainPswRootTv.setDrawableTop(ints1[0][1]);
        mainPswRootTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelector();
                mainPswRootTv.setSelected(true);
                mainVp.setCurrentItem(0, false);
//                mainPswRootTv.setTextColor(Color.parseColor("#fff"));
                mainPswRootTv.setTextColor(getResources().getColor(ints[1]));
                mainPswRootTv.setDrawableTop(ints1[0][1]);
            }
        });
        mainTallyRootTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelector();
                mainTallyRootTv.setSelected(true);
                mainVp.setCurrentItem(1, false);
                mainTallyRootTv.setTextColor(getResources().getColor(ints[1]));
                mainTallyRootTv.setDrawableTop(ints1[1][1]);
            }
        });
        mainDiaryRootTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelector();
                mainDiaryRootTv.setSelected(true);
                mainDiaryRootTv.setDrawableTop(ints1[2][1]);
                mainVp.setCurrentItem(2, false);
                mainDiaryRootTv.setTextColor(getResources().getColor(ints[1]));
            }
        });
        mainMeRootTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelector();
                mainMeRootTv.setSelected(true);
                mainVp.setCurrentItem(3, false);
                mainMeRootTv.setTextColor(getResources().getColor(ints[1]));
                mainMeRootTv.setDrawableTop(ints1[3][1]);
            }
        });
    }

    protected void initData() {

    }

    public int getPageSize() {
        return pageSize;
    }

    @Override
    protected int getViewId() {
        return R.layout.apy_main_bottom_layout;
    }

    private void initTv(String[] titleName) {
        pageSize = titleName.length;
        mTvList.add(mainPswRootTv);
        mainPswRootTv.setText(titleName[0]);
        if (pageSize == 2) {
            mainTallyRootTv.setVisibility(View.VISIBLE);
            mTvList.add(mainTallyRootTv);
            mainTallyRootTv.setText(titleName[1]);
        }
        if (pageSize == 3) {
            mainTallyRootTv.setVisibility(View.VISIBLE);
            mTvList.add(mainTallyRootTv);
            mainDiaryRootTv.setVisibility(View.VISIBLE);
            mTvList.add(mainDiaryRootTv);
            mainTallyRootTv.setText(titleName[1]);
            mainDiaryRootTv.setText(titleName[2]);
        }
        if (pageSize == 4) {
            mainTallyRootTv.setVisibility(View.VISIBLE);
            mTvList.add(mainTallyRootTv);
            mainDiaryRootTv.setVisibility(View.VISIBLE);
            mTvList.add(mainDiaryRootTv);
            mainMeRootTv.setVisibility(View.VISIBLE);
            mTvList.add(mainMeRootTv);

            mainTallyRootTv.setText(titleName[1]);
            mainDiaryRootTv.setText(titleName[2]);
            mainMeRootTv.setText(titleName[3]);
        }
    }

    private void setSelector() {
        for (int i = 0; i < mTvList.size(); i++) {
            mTvList.get(i).setSelected(false);
            mTvList.get(i).setTextColor(getResources().getColor(ints[0]));
            mTvList.get(i).setDrawableTop(ints1[i][0]);
        }
    }

    /**
     * ViewPager个数由文字数组长度决定
     * new String[]{"第一个文字", "第二个文字"},
     * new int[]{R.color.文字默认颜色,R.color.文字选中颜色},
     * new int[][]{{R.drawable.第一个条目默认图片, R.drawable.第一个条目选中图片},
     * {R.drawable.第二个条目默认图片, R.drawable.第二个条目选中图片},
     * {R.drawable.第三个条目默认图片, R.drawable.第三个条目选中图片},
     * {R.drawable.第四个条目默认图片, R.drawable.第四个条目选中图片}}
     */
    public void initVpAdapter(FragmentPagerAdapter adapter, String[] titleName, int[] ints, int[][] ints1) {
        this.ints = ints;
        this.ints1 = ints1;
        initTv(titleName);
        mainVp.setAdapter(adapter);
        mainVp.setOffscreenPageLimit(pageSize);
        mainVp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                for (int i = 0; i < mTvList.size(); i++) {
                    mTvList.get(i).setSelected(false);
                }
                mTvList.get(position).setSelected(true);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    /**
     * @param color 底部状态栏颜色,高度
     */
    public void setBottomRootColor(int color, int height) {
        mainBottomRoot.setBackgroundColor(color);
        ViewGroup.LayoutParams layoutParams = mainBottomRoot.getLayoutParams();
        layoutParams.height = UIUtils.dip2px(this, height);
        mainBottomRoot.setLayoutParams(layoutParams);
    }

    /**
     * 设置底部状态栏top图片宽高
     *
     * @param topWidth  图片宽度
     * @param tipHeight 图片高度
     */
    public void setTopWidthAndHeight(int topWidth, int tipHeight) {
        mainPswRootTv.setTopWidthAndHeight(UIUtils.dip2px(this, topWidth), UIUtils.dip2px(this, tipHeight));
        mainTallyRootTv.setTopWidthAndHeight(UIUtils.dip2px(this, topWidth), UIUtils.dip2px(this, tipHeight));
        mainDiaryRootTv.setTopWidthAndHeight(UIUtils.dip2px(this, topWidth), UIUtils.dip2px(this, tipHeight));
        mainMeRootTv.setTopWidthAndHeight(UIUtils.dip2px(this, topWidth), UIUtils.dip2px(this, tipHeight));
    }
}
