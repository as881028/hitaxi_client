package com.hitaxi.activities;

import android.os.Bundle;

import com.hitaxi.base.BaseActivity;
import com.dk.main.R;

/*
Activity Name:      修改個人資料畫面
creator:            Stanley.li
layout:             activity_modify_personal_info
路徑:               選單->個人資料->修改密碼
 */
public class ModPersonInfoAty extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMenuLayout(R.layout.activity_modify_personal_info);
    }
}
