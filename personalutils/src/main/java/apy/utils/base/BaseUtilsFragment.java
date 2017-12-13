package apy.utils.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import apy.utils.listener.OnLoadDataListener;
import apy.utils.net.BaseResponse;
import apy.utils.utils.SystemBarTintManager;
import apy.utils.utils.UIUtils;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/10/19.
 */

public abstract class BaseUtilsFragment<T extends BaseResponse> extends Fragment  implements OnLoadDataListener<T> {

    public View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(layoutId(), container, false);
        ButterKnife.bind(this,rootView);
        EventBus.getDefault().register(this);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Object event) {

    }
    public void initData(){

    }

    protected abstract int layoutId();

    public void initStatusBar(ViewGroup rootRl){
        int statusBar = UIUtils.getStatusBar(getActivity());
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rootRl.getLayoutParams();
        layoutParams.topMargin = statusBar;
        rootRl.setLayoutParams(layoutParams);
    }

    public void setStatusBar(int color){
        // 透明状态栏
        getActivity().getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(color);//通知栏颜色
    }

    public  void startThisActivity(Class<? extends AppCompatActivity> clazz){
        Intent intent = new Intent(getActivity(),clazz);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        getActivity().startActivity(intent);
    }

    public  void startThisActivityForResult(Class<? extends AppCompatActivity> clazz,int requestCode){
        Intent intent = new Intent(getActivity(),clazz);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        getActivity().startActivityForResult(intent,requestCode);
    }
}
