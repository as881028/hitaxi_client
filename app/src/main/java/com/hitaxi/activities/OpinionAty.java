package com.hitaxi.activities;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.dk.main.R;
import com.hitaxi.adapter.OpinionAdapter;
import com.hitaxi.base.MenuBaseActivity;
import com.hitaxi.object.PersonalDetail;
import com.hitaxi.object.TradDetail;

/*
Activity Name:      意見畫面
creator:            Stanley.li
layout:             activity_opinion
路徑:               選單->個人資料->意見
adapter:            OpinionAdapter
 */
public class OpinionAty extends MenuBaseActivity {

    ListView opinion_Listview;
    String[] dates = new String[]{"2017年5月12日", "2017年5月13日", "2017年5月14日", "2017年5月15日", "2017年5月16日",
            "2017年5月17日", "2017年5月18日", "2017年5月19日", "2017年5月20日", "2017年5月21日"};
    String[] stars = {"3", "2", "5", "1", "1", "2", "5", "4", "4", "3", "1", "2"};
    String[] contents = {"台北火車站", "台南火車站", "台中火車站", "台東火車站", "永康火車站",
            "新市火車站", "善化火車站", "大橋火車站", "新營火車站", "柳營火車站", "柳營火車站", "柳營火車站"};
    public com.hitaxi.object.PersonalDetail PersonalDetail;
    public com.hitaxi.object.TradDetail TradDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setMenuLayout(R.layout.activity_opinion);
        setContentView(R.layout.activity_opinion);

        opinion_Listview = (ListView) findViewById(R.id.opinion_Listview);

        getGlobal();
        PersonalDetail = var.PersonalDetail;
        TradDetail = var.TradDetail;


        //建立自訂的Adapter
        OpinionAdapter adapter = new OpinionAdapter(getApplicationContext(), TradDetail.getEndTime(), TradDetail.getRating(), TradDetail.getOpinion());

        //設定ListView 的資源來源
        opinion_Listview.setAdapter(adapter);
    }


}



