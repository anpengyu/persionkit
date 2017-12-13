package apy.utils.net;

import android.app.Dialog;
import android.content.Context;

import apy.utils.utils.AToast;
import apy.utils.utils.DialogUtils;
import apy.utils.utils.UIUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by apy on 2017/8/14.
 */

public abstract class MyCallBack<K extends BaseResponse> implements Callback {

    private Dialog loadingDialog;
    private boolean netWork;
    private Context mContext;

    public MyCallBack(Context mContext, boolean isShowDialog) {
        this.mContext = mContext;
        netWork = UIUtils.getNetWork();
        if (!netWork) {
            AToast.showTextToast("请检查您的网络设置");
            return;
        }
        if (isShowDialog) {
            loadingDialog = DialogUtils.createLoadingDialog(mContext, "加载中~~~~");
            if (loadingDialog != null) {
                loadingDialog.dismiss();
                loadingDialog.show();
                return;
            }
        }
    }

    @Override
    public void onResponse(Call call, Response response) {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
        BaseResponse body = (BaseResponse) response.body();
        if (body != null && body.code != null) {
            if (body.code != null) {
                if ("109".equals(body.code)) {
                    AToast.showTextToast("您的账户已过期，请重新登录");
                    accountOverdue();
                    return;
                }
                K t = (K) body;
                onResponse(t);
            }else{
                onResponseFailure();
            }
        } else {
            AToast.showTextToast("网络错误");
            onResponseFailure();
        }
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
        if (netWork) {
            AToast.showTextToast("网络错误");
            onFailure(t);
        }

    }

    /**
     * 获取到服务器数据
     *
     * @param response
     */
    public abstract void onResponse(K response);

    /**
     * 连接到服务器但是没有获取到数据
     */
    public void onResponseFailure() {

    }

    /**
     * 账号冻结
     */
    public void freezeResponse() {

    }

    /**
     * 账号过期
     */
    public void accountOverdue() {

    }

    /**
     * 连接服务器失败
     *
     * @param t
     */
    public void onFailure(Throwable t) {

    }
}
