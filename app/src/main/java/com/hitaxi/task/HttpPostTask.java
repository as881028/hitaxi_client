package com.hitaxi.task;

import android.os.AsyncTask;
import android.util.Log;

import com.hitaxi.tools.phpConnection;

import java.net.URL;
import java.util.Map;

/**
 * Created by DK on 2017/8/5.
 */

public class HttpPostTask extends AsyncTask<Void, Void, String> {
    public String TAG = "HttpTradPostTask";
    public Map<String, String> mMap;
    public String url;

    public HttpPostTask(String url, Map<String, String> map) {
        mMap = map;
        this.url = url;
    }


    @Override
    protected String doInBackground(Void... voids) {
        String result = "";
        try {
            //取得PHP回傳值
            result = phpConnection.createConnection(url, mMap);


            Log.i(TAG, result);

        } catch (NullPointerException e) {

            Log.i(TAG, "Null Point");
            return null;

        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
        // TODO: register the new account here.
        return result;
    }

    @Override
    protected void onPostExecute(String succes) {
        super.onPostExecute(succes);

    }

}
