package com.hitaxi.object;


import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.util.Log;

import com.hitaxi.tools.parseMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Created by DK on 2017/8/3.
 */

public class TradDetail {
    ArrayList<String> tidArrayList = new ArrayList<String>();
    ArrayList<Integer> moneyArrayList = new ArrayList<Integer>();
    ArrayList<String> dateArrayList = new ArrayList<String>();
    ArrayList<String> yearArrayList = new ArrayList<String>();
    ArrayList<String> monthArrayList = new ArrayList<String>();
    ArrayList<String> dayArrayList = new ArrayList<String>();
    ArrayList<Double> al_startLat = new ArrayList<Double>();
    ArrayList<Double> al_startLng = new ArrayList<Double>();
    ArrayList<Double> al_endLat = new ArrayList<Double>();
    ArrayList<Double> al_endLng = new ArrayList<Double>();

    ArrayList<String> endTimeArrayList = new ArrayList<String>();
    ArrayList<String> opinionArrayList = new ArrayList<String>();
    ArrayList<Integer> ratingArrayList = new ArrayList<Integer>();

    String[] strAddr;
    String[] endAddr;
    Integer[] monthMoney = new Integer[12];


//    HashMap<Integer, Integer> monthMoney = new HashMap<>();

    public TradDetail() {

    }

    public void addTid(String tid) {
        tidArrayList.add(tid);
    }

    public ArrayList<String> getTidArrayList() {
        return this.tidArrayList;
    }

    public String[] getTidArray() {
        return parseMethod.parseStringArray(tidArrayList);
    }

    public void addMoney(int money) {
        moneyArrayList.add(money);
    }

    public ArrayList<Integer> getMoneyArrayList() {
        return this.moneyArrayList;
    }

    public Integer[] getMoneyArray() {
        return parseMethod.parseIntArray(moneyArrayList);
    }

    public void addDate(String date) {
        dateArrayList.add(date);
    }

    public ArrayList<String> getDateArrayList() {
        return this.dateArrayList;
    }

    public String[] getDateArray() {
        return parseMethod.parseStringArray(dateArrayList);
    }

    public void addMonth(String month) {
        monthArrayList.add(month);
    }

    public List<Address> getAddress(Context c) {
        Geocoder gc = new Geocoder(c, Locale.TRADITIONAL_CHINESE);
        List<Address> lstAddress = null;
        String[] resultArrayList = null;
        strAddr = new String[al_startLng.size()];
        endAddr = new String[al_startLng.size()];
        try {

            for (int i = 0; i < al_startLat.size(); i++) {

                lstAddress = gc.getFromLocation(al_startLat.get(i), al_startLng.get(i), 1);
                strAddr[i] = lstAddress.get(0).getAddressLine(0);


                lstAddress = gc.getFromLocation(al_endLat.get(i), al_endLng.get(i), 1);
                endAddr[i] = lstAddress.get(0).getAddressLine(0);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }


        return lstAddress;
    }

    public String[] getStartAddress() {
        return strAddr;
    }

    public String[] getEndAddress() {
        return endAddr;
    }

    public void addGeo(Double s_lat, Double s_lng, Double e_lat, Double e_lng) {
        al_startLat.add(s_lat);
        al_startLng.add(s_lng);
        al_endLat.add(e_lat);
        al_endLng.add(e_lng);
    }

    //計算每月收入紀錄
    public void calOrderRecord() {

        for (int i = 0; i < monthMoney.length; i++) {
            monthMoney[i] = 0;
        }

        for (int i = 0; i < moneyArrayList.size(); i++) {
            int month = Integer.parseInt(monthArrayList.get(i));
            monthMoney[month - 1] += moneyArrayList.get(i);
        }
    }

    public Integer[] getRecordMoney() {
        return monthMoney;
    }

    public void addYear(String year) {
        yearArrayList.add(year);
    }

    public String[] getYear() {
        return parseMethod.parseStringArray(yearArrayList);
    }

    public void addDay(String day) {
        dayArrayList.add(day);
    }

    public String[] getDay() {
        return parseMethod.parseStringArray(dayArrayList);
    }

    public void addEndTime(String endTime) {
        endTimeArrayList.add(endTime);
    }

    public String[] getEndTime() {
        return parseMethod.parseStringArray(endTimeArrayList);
    }

    public void addOpinion(String opinion) {
        opinionArrayList.add(opinion);

    }

    public String[] getOpinion() {
        return parseMethod.parseStringArray(opinionArrayList);

    }

    public void addRating(int score) {
        ratingArrayList.add(score);

    }

    public Integer[] getRating() {
        return parseMethod.parseIntArray(ratingArrayList);

    }

}
