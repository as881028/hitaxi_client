package com.hitaxi.activities;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import com.dk.main.R;
import com.hitaxi.base.GlobalVar;
import com.hitaxi.base.MenuBaseActivity;
/*
Activity Name:      個人資料畫面
creator:            Stanley.li
layout:             activity_opinion
路徑:               選單->個人資料
 */
public class PersonInfoAty extends MenuBaseActivity {
    Intent i = new Intent();
    private String mConnResult = null;

    protected static final String TAG = "PersonalInfo";

    String uid = "";
    String token = "";

    private TextView tvDriverTeam;
    private TextView tvCallNum;
    com.hitaxi.object.PersonalDetail PersonalDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMenuLayout(R.layout.activity_personal_information);
        initView();

        PersonalDetail = var.PersonalDetail;


        tvCallNum.setText(PersonalDetail.getCallNum());
        tvDriverTeam.setText(PersonalDetail.getTeamName());
    }


    private void initView() {
        //按鈕->收入紀錄
        findViewById(R.id.income_record_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                i.setClass(PersonInfoAty.this, IncomeRecordAty.class);
                startActivity(i);
            }
        });

        //按鈕->意見
        findViewById(R.id.opinion_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(PersonInfoAty.this, OpinionAty.class);
                startActivity(i);
            }
        });
        //按鈕->修改密碼
        findViewById(R.id.modify_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(PersonInfoAty.this, ModPersonInfoAty.class);
                startActivity(i);
            }
        });

        //TextView findView
        tvDriverTeam = (TextView) findViewById(R.id.taxiteam_textView);
        tvCallNum = (TextView) findViewById(R.id.callnumer_textView);

    }


}
