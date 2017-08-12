package com.hitaxi.activities;

import android.content.Context;
import android.location.Address;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dk.main.R;
import com.hitaxi.adapter.OrderRecordAdapter;
import com.hitaxi.base.MenuBaseActivity;
import com.hitaxi.object.PersonalDetail;
import com.hitaxi.object.TradDetail;

import java.util.List;

/*
Activity Name:      收入紀錄畫面
creator:            Stanley.li
layout:             activity_opinion
路徑:               選單->接單紀錄
adapter:            OrderRecordAdapter
 */
public class OrderRecordAty extends MenuBaseActivity {
    ListView order_record_ListView;
    String[] month_days = new String[]{"5月12", "5月13日", "5月14日", "5月15日", "5月16日",
            "5月17日", "5月18日", "5月19日", "5月20日", "5月21日"};
    String[] moneys = {"10000", "20000", "30000", "40000", "50000",
            "60000", "70000", "80000", "90000", "100000"};
    String[] getin = {"台北火車站", "台南火車站", "台中火車站", "台東火車站", "永康火車站",
            "新市火車站", "善化火車站", "大橋火車站", "新營火車站", "柳營火車站"};
    String[] getout = {"桃園", "苗栗", "新竹", "嘉義", "龍井",
            "龍津", "龍泉", "靜宜", "東海", "逢甲"};
    public TradDetail TradDetail;
    public PersonalDetail PersonalDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMenuLayout(R.layout.activity_order_record);
        order_record_ListView = (ListView) findViewById(R.id.order_record_ListView);

        getGlobal();
        PersonalDetail = var.PersonalDetail;
        TradDetail = var.TradDetail;

        List<Address> addr = TradDetail.getAddress(this);
        //建立自訂的Adapter
        OrderRecordAdapter adapter = new OrderRecordAdapter(this, TradDetail.getDateArray(), TradDetail.getMoneyArray(), TradDetail.getStartAddress(), TradDetail.getEndAddress());

        //設定ListView 的資源來源
        order_record_ListView.setAdapter(adapter);
    }

}
