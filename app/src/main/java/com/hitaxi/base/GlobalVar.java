package com.hitaxi.base;

import android.app.Application;

import com.hitaxi.object.PersonalDetail;
import com.hitaxi.object.TradDetail;

/**
 * Created by DK on 2017/7/3.
 */

public class GlobalVar extends Application {
    public Boolean debug = true;

    public String phone = "";
    public String webSiteIP = "140.128.98.12";
    public String webPath = "/taxi_go/api";
    public String validation = "/validation";
    public String queryUser = "/query_member";
    public String queryDriver = "/query_driver";
    public String queryTrad = "/query_transaction";
    public String driver_location_add = "/driver_location_add.php";
    public com.hitaxi.object.PersonalDetail PersonalDetail;
    public TradDetail TradDetail;
    public Boolean workStatus=false;

}