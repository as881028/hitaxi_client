package com.hitaxi.base;

/**
 * Created by DK on 2017/7/29.
 */

public class MenuBaseActivity extends BaseActivity {
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
