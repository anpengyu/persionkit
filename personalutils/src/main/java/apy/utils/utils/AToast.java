package apy.utils.utils;

import android.widget.Toast;

/**
 * Created by Administrator on 2017/10/17.
 */

public class AToast {
    //设置Toast对象
    private static Toast mToast = null;

    public static void showTextToast(String msg) {
        //判断队列中是否包含已经显示的Toast
        if (mToast == null) {
            mToast = Toast.makeText(UIUtils.getContext(), msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }
}
