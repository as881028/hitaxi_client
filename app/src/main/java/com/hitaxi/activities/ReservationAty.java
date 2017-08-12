package com.hitaxi.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dk.main.R;
import com.hitaxi.base.MenuBaseActivity;

/*
Activity Name:      預約畫面
creator:            Stanley.li
layout:             activity_opinion
路徑:               選單->預約
 */
public class ReservationAty extends MenuBaseActivity {
    ListView reservation_Listview;
    String[] dates= new String[] {"2017年5月12","2017年5月13日","2017年5月14日","2017年5月15日","2017年5月16日",
            "2017年5月17日","2017年5月18日","2017年5月19日","2017年5月20日","2017年5月21日"};
    String[] daily= {"下午13:18","下午13:20","下午13:32","下午13:50","下午13:20",
            "下午13:40","下午13:03","下午13:20","下午6:45","下午8:45"};
    String[] getOns= {"台北火車站","台南火車站","台中火車站","台東火車站","永康火車站",
            "新市火車站","善化火車站","大橋火車站","新營火車站","柳營火車站"};
    String[] getoffs= {"桃園","苗栗","新竹","嘉義","龍井",
            "龍津","龍泉","靜宜","東海","逢甲"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMenuLayout(R.layout.activity_reservation);
        reservation_Listview = (ListView)findViewById(R.id.reservation_Listview);
        //建立自訂的Adapter
        MyAdapter adapter=new MyAdapter(this);
        //設定ListView 的資源來源
        reservation_Listview.setAdapter(adapter);
    }
    public class MyAdapter extends BaseAdapter {
        private LayoutInflater myInflater;
        public MyAdapter(Context c){
            myInflater = LayoutInflater.from(c);
        }
        @Override
        public int getCount(){
            return dates.length;
        }
        @Override
        public Object getItem(int position){
            return dates[position];
        }
        public long getItemId(int position){
            return position;
        }
        @Override
        public View getView (int postition,View convertView, ViewGroup parent){
            convertView = myInflater.inflate(R.layout.reservation_listview_layout,null);
            //取得XML內容
            TextView reservation_textView = ((TextView)
                    convertView.findViewById(R.id.reservation_textView));
            TextView date = ((TextView)
                    convertView.findViewById(R.id.date));
            TextView textView8 = ((TextView)
                    convertView.findViewById(R.id.textView8));
            TextView geton = ((TextView)
                    convertView.findViewById(R.id.geton));
            TextView getoff = ((TextView)
                    convertView.findViewById(R.id.getoff));

            date.setText(dates[postition]);
            textView8.setText(daily[postition]);
            geton.setText(getOns[postition]);
            getoff.setText(getoffs[postition]);
            return convertView;
        }
    }
}