package com.hitaxi.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.dk.main.R;
import com.hitaxi.adapter.IncomeRecordAdapter;
import com.hitaxi.object.PersonalDetail;
import com.hitaxi.object.TradDetail;
import com.hitaxi.tools.phpConnection;
import com.hitaxi.base.BaseActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;


/*
Activity Name:      收入統計畫面
creator:            Stanley.li
layout:             activity_income_record
list view layout:   income_record_Listview
adapter:            IncomeRecordAdapter
路徑:               選單->個人資料->收入紀錄
 */
public class IncomeRecordAty extends BaseActivity {


    protected static final String TAG = "IncomeRecordActivity";

    public com.hitaxi.object.PersonalDetail PersonalDetail;
    ListView income_record_Listview;
    public String[] dates = new String[]{"2017年", "2017年", "2017年", "2017年", "2017年",
            "2017年", "2017年", "2017年", "2017年", "2017年", "2017年", "2017年"};

    public Integer[] digitals = {85, 87, 65, 66, 87, 88, 89, 90, 81, 82, 83, 84};
    public String[] monthes = {"一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"};
    public String[] moneies = {"1000NT", "1001NT", "1002NT", "1003NT", "1004NT",
            "1005NT", "1006NT", "靜宜", "東海", "逢甲"};
    public TradDetail TradDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMenuLayout(R.layout.activity_income_record);


        getGlobal();
        PersonalDetail = var.PersonalDetail;
        TradDetail = var.TradDetail;


        //listView Adpater start
        income_record_Listview = (ListView) findViewById(R.id.income_record_Listview);
        //建立自訂的Adapter
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String years = sdf.format(new java.util.Date());


        IncomeRecordAdapter adapter = new IncomeRecordAdapter(getApplicationContext(), years + "年", digitals, monthes, TradDetail.getRecordMoney());

        //設定ListView 的資源來源
        income_record_Listview.setAdapter(adapter);
        //listView Adpater end


    }


}
