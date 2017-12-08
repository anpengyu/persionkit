package apy.utils.listener;

import apy.utils.net.BaseResponse;

/**
 * Created by Administrator on 2017/11/18.
 */

public interface OnLoadDataListener<T extends BaseResponse> {

    void onSuccess(boolean state);

    void onSuccess(String state, T t);

    void onFailure(String code);
}
