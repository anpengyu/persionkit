package apy.utils.listener;

import apy.utils.net.BaseResponse;

/**
 * Created by Administrator on 2017/11/18.
 */

public interface OnLoadDataListener<T extends BaseResponse> {
    /**
     * code == 0T
     */
    void onSuccess(String state,T code);
    /**
     * code ！=000 异常信息
     */
    void onFaiure(String state,String code);

    /**
     * code == null
     */
    void onException();

}
