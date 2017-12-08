package utils.apy.myapplication;

import apy.utils.activity.KitMainActivity;
import apy.utils.net.BaseResponse;

public class MainActivity extends KitMainActivity {

    @Override
    protected String title() {
        return null;
    }

    @Override
    protected boolean controlTitle() {
        return false;
    }

    @Override
    public void onSuccess(String state, BaseResponse baseResponse) {

    }

    @Override
    public void onFailure(String code) {

    }
}
