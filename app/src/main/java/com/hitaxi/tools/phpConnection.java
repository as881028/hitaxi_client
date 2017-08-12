package com.hitaxi.tools;

import android.util.Log;

import com.hitaxi.base.GlobalVar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by DK on 2017/7/6.
 */

public class phpConnection {


    public static String createConnection(String webFunction, Map<String, String> map) throws ConnectException{

        String TAG = "phpConnection";
        String responseString = null;

        GlobalVar var = new GlobalVar();
        // url String 組合
        String webSiteIP = var.webSiteIP;
        String webPath = var.webPath;
        String urlString = String.format("http://%s%s%s", webSiteIP, webPath, webFunction);
        //


        HttpURLConnection connection = null;

        try {
            // 初始化 URL
            URL url = new URL(urlString);
            // 取得連線物件
            connection = (HttpURLConnection) url.openConnection();
            // 設定 request timeout
            connection.setReadTimeout(1500);
            connection.setConnectTimeout(1500);
            connection.setDoOutput(true);// 設置此方法,允許向伺服器輸出內容

            // post請求的參數
//            String data = String.format("account=%s&password=%s", account, password);

            //取得外部傳入的參數
            //key:value

            String data = "";
//            account=%s&password=%s
            Set<Map.Entry<String, String>> set = map.entrySet();
            for (Map.Entry<String, String> entry : set)

            {
                data += String.format("%s=%s", entry.getKey(), entry.getValue());
                data += "&";
            }
//            for (int i = 0; i < key.size(); i++) {
//                data += String.format("%s=%s", key.get(i), value.get(i));
//                if (i != key.size() - 1) {
//                    data += "&";
//                }
//            }


            OutputStream out = connection.getOutputStream();// 產生一個OutputStream，用來向伺服器傳數據
            out.write(data.getBytes());
            out.flush();
            out.close();

            // 設定開啟自動轉址
            connection.setInstanceFollowRedirects(true);

            // 若要求回傳 200 OK 表示成功取得網頁內容
            if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                // 讀取網頁內容
                InputStream inputStream = connection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String tempStr;
                StringBuffer stringBuffer = new StringBuffer();

                while ((tempStr = bufferedReader.readLine()) != null) {
                    stringBuffer.append(tempStr);
                }

                bufferedReader.close();
                inputStream.close();


                // 網頁內容字串
                responseString = stringBuffer.toString();

                return responseString;

            } else {
                Log.i(TAG, "Connection fail");

            }
        }  catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 中斷連線
            if (connection != null) {
                connection.disconnect();
            }
        }
        return responseString;
    }


}
