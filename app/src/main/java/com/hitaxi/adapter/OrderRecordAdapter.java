package com.hitaxi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dk.main.R;

/**
 * Created by DK on 2017/8/5.
 */

public class OrderRecordAdapter extends BaseAdapter {
    private LayoutInflater myInflater;
    public String[] month_days;
    public Integer[] moneys;
    public String[] getin;
    public String[] getout;

    public OrderRecordAdapter(Context c, String[] month_days, Integer[] moneys, String[] getin, String[] getout) {
        myInflater = LayoutInflater.from(c);
        this.month_days = month_days;
        this.moneys = moneys;
        this.getin = getin;
        this.getout = getout;

    }

    @Override
    public int getCount() {

        return month_days.length;
    }

    @Override
    public Object getItem(int position) {
        return month_days[position];
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int postition, View convertView, ViewGroup parent) {
        convertView = myInflater.inflate(R.layout.orderrecord_listview_layout, null);
        //取得XML內容
        TextView endtime_textView = ((TextView)
                convertView.findViewById(R.id.endtime_textView));
        TextView month_day = ((TextView)
                convertView.findViewById(R.id.month_day));
        TextView fare_textView = ((TextView)
                convertView.findViewById(R.id.fare_textView));
        TextView money = ((TextView)
                convertView.findViewById(R.id.money));
        TextView getin_textView = ((TextView)
                convertView.findViewById(R.id.getin_textView));
        TextView getout_textView = ((TextView)
                convertView.findViewById(R.id.getout_textView));

        month_day.setText(month_days[postition]);
        money.setText(moneys[postition].toString());
        getin_textView.setText(getin[postition]);
        getout_textView.setText(getout[postition]);
        return convertView;
    }
}