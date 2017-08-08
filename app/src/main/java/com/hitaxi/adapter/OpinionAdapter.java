package com.hitaxi.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.dk.main.R;

/**
 * Created by DK on 2017/8/3.
 */
//public class OpinionAdapter extends BaseAdapter{
//
//    private Context mContext;
//    String[] dates;
//        String[] stars;
//    String[] contents;
//    public OpinionAdapter(Context c, String[] dates, String[] stars, String[] contents) {
//        //myInflater = LayoutInflater.from(c);
//        this.mContext = c;
//        this.dates = dates;
//        this.stars = stars;
//        this.contents = contents;
//    }
//
//    @Override
//    public int getCount() {
//        // TODO Auto-generated method stub
//        return dates.length;    }
//
//    @Override
//    public Object getItem(int position) {
//        // TODO Auto-generated method stub
//        return dates[position];
//    }
//
//    @Override
//    public long getItemId(int position) {
//        // TODO Auto-generated method stub
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        // TODO Auto-generated method stub
//        ViewHolder viewholder = null;
//        if(convertView == null){
//            convertView = View.inflate(mContext, R.layout.opinion_listview_layout, null);
//            viewholder = new ViewHolder();
//            viewholder.titleTv = (TextView)convertView.findViewById(R.id.date);
//            convertView.setTag(viewholder);
//        }
//        else{
//            viewholder = (ViewHolder)convertView.getTag();
//        }
//        viewholder.titleTv.setText(dates[position]);
//
//        return convertView;
//    }
//
//    public static class ViewHolder{
//        TextView titleTv;
//    }
//
//}

public class OpinionAdapter extends BaseAdapter {

    private LayoutInflater myInflater;
    private Context mContext;

    String[] dates;
    String[] stars;
    String[] contents;

    public OpinionAdapter(Context c, String[] dates, String[] stars, String[] contents) {
        //myInflater = LayoutInflater.from(c);
        this.mContext = c;
        this.dates = dates;
        this.stars = stars;
        this.contents = contents;
    }

    @Override
    public int getCount() {
        return dates.length;
    }

    @Override
    public Object getItem(int position) {
        return dates[position];
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int postition, View convertView, ViewGroup parent) {
        ViewHolder viewholder = null;
        if(convertView == null){
        //convertView = myInflater.inflate(R.layout.opinion_listview_layout, null);
            convertView = View.inflate(mContext,R.layout.opinion_listview_layout, null);
        //取得XML內容

            viewholder = new ViewHolder();
            viewholder.tvDate = (TextView)convertView.findViewById(R.id.date);
            viewholder.tvScore = (TextView)convertView.findViewById(R.id.score);
            viewholder.rbRatingBar = (RatingBar)convertView.findViewById(R.id.ratingBar);
            viewholder.tvOpinionContent = (TextView)convertView.findViewById(R.id.opinion_content);

//        TextView tvDate = ((TextView)
//                convertView.findViewById(R.id.date));
//        TextView tvScore = ((TextView)
//                convertView.findViewById(R.id.score));
//        RatingBar rbRatingBar = ((RatingBar)
//                convertView.findViewById(R.id.ratingBar));
//        TextView tvOpinionContent = ((TextView)
//                convertView.findViewById(R.id.opinion_content));
//
//        tvDate.setText(dates[postition]);
////        rbRatingBar.setRating((float) (1.5));
//        tvOpinionContent.setText(contents[postition]);
//        Log.i("test", contents[postition]);


            convertView.setTag(viewholder);
        }
        else{
            viewholder = (ViewHolder)convertView.getTag();
        }
        Log.i("test", dates[postition]);
        viewholder.tvDate.setText(dates[postition]);
        viewholder.rbRatingBar.setRating((float) (1.5));
        viewholder.tvOpinionContent.setText(contents[postition]);
        return convertView;
    }
    public static class ViewHolder{
        TextView tvDate;
        TextView tvScore;
        RatingBar rbRatingBar;
        TextView tvOpinionContent;

    }
}