package com.hitaxi.activities;

import android.os.Bundle;

import com.dk.main.R;
import com.hitaxi.base.MenuBaseActivity;

/*
Activity Name:      最新消息畫面
creator:            Stanley.li
layout:             activity_latest_news
路徑:               選單->最新消息
 */
public class LatestNewsAty extends MenuBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMenuLayout(R.layout.activity_latest_news);

    }


}
