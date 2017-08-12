package com.hitaxi.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.dk.main.R;
import com.hitaxi.base.MenuBaseActivity;
import com.hitaxi.object.PersonalDetail;

import org.w3c.dom.Text;


/*
Activity Name:      服務中心畫面
creator:            Stanley.li
layout:             activity_service_center
路徑:               選單->服務中心
 */
public class ServiceCenterAty extends MenuBaseActivity {
    //TextView
    private TextView tvName, tvAddress, tvFax, tvPhone;
    //Object
    PersonalDetail PersonalDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMenuLayout(R.layout.activity_service_center);
        getGlobal();
        PersonalDetail = var.PersonalDetail;

        initView();
        setText();
    }

    private void setText() {
        tvName.setText(PersonalDetail.getTeamName() + " 辦事處");
        tvAddress.setText(PersonalDetail.getTeamAddr());
        tvFax.setText(PersonalDetail.getTeamFax());
        tvPhone.setText(PersonalDetail.getTeamPhone());
    }

    private void initView() {
        tvName = (TextView) findViewById(R.id.tvName);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        tvFax = (TextView) findViewById(R.id.tvFax);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
    }


}
