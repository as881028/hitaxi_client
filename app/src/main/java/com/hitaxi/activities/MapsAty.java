package com.hitaxi.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hitaxi.base.BaseActivity;

import com.dk.main.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import com.hitaxi.object.PersonalDetail;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/*
Activity Name:      地圖畫面
creator:            Stanley.li
layout:             activity_menu
路徑:               登入後主畫面
 */
public class MapsAty extends BaseActivity implements OnMapReadyCallback, ConnectionCallbacks, OnConnectionFailedListener, LocationSource.OnLocationChangedListener {


    class Coordinate {
        protected Double dLatitude;
        protected Double dLongitude;

        public void setCoordinate(Double x, Double y) {
            dLatitude = x;
            dLongitude = y;
        }

        public Double getLatitude() {
            return dLatitude;
        }

        public Double getLongitude() {
            return dLongitude;
        }


    }

    protected static final String TAG = "MapActivity";
    private DriverMapTask mMapTask = null;
    protected GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;

    private GoogleMap mMap;
    Coordinate Coordinate = new Coordinate();
    private final String PERMISSION_ACCESS_FINE_LOCATION = "android.permission.ACCESS_FINE_LOCATION";

    Timer timer = new Timer(true);
    int LOCATION_SAVE_TIME_PRE_SECOND = 10;
    public com.hitaxi.object.PersonalDetail PersonalDetail;

    Button btnStatus;
    LinearLayout llStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMenuLayout();
//        PersonalDetail = var.PersonalDetail;
        //google api connect
        buildGoogleApiClient();
//        getGlobal() = getGlobal();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        //地圖同步
        mapFragment.getMapAsync(this);

        initView();
    }

    View.OnClickListener statusClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (var.workStatus == false) {
                var.workStatus = true;
                btnStatus.setText("上線");
                llStatus.setBackgroundColor(Color.parseColor("#5500A600"));

            } else {
                var.workStatus = false;
                btnStatus.setText("離線");
                llStatus.setBackgroundColor(Color.parseColor("#55750000"));
            }
        }
    };

    private void initView() {
        btnStatus = (Button) findViewById(R.id.btnStatus);
        btnStatus.setOnClickListener(statusClick);
        llStatus = (LinearLayout) findViewById(R.id.llStatus);
    }


    /**
     * 確認是否要請求權限(API > 23)
     * API < 23 一律不用詢問權限
     */
    private boolean needCheckPermission() {
        //MarshMallow(API-23)之後要在 Runtime 詢問權限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] perms = {PERMISSION_ACCESS_FINE_LOCATION};
            int permsRequestCode = 200;
            requestPermissions(perms, permsRequestCode);
            return true;
        }
        return false;
    }

    /**
     * 是否已經請求過該權限
     * API < 23 一律回傳 true
     */
    private boolean hasPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return (ActivityCompat.checkSelfPermission(this, PERMISSION_ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 200) {
            if (grantResults.length > 0) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(">>>", "取得授權，可以執行動作了");
                    buildGoogleApiClient();
                    mGoogleApiClient.connect();
                }
            }
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }


    @Override
    protected void onStart() {
        super.onStart();
        closeDrawers((DrawerLayout) findViewById(R.id.drawer_layout));
        menu_text_set();

        //檢查權限 > v23就等待權限
        //<23直接連接地圖
        if (!hasPermission()) {
            if (needCheckPermission()) {
                //如果須要檢查權限，由於這個步驟要等待使用者確認，
                //所以不能立即執行儲存的動作，
                //必須在 onRequestPermissionsResult 回應中才執行
                return;
            }


        } else {
            mGoogleApiClient.connect();
        }
//
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
            timer.cancel();
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);  // 右下角的放大縮小功能
        mMap.getUiSettings().setCompassEnabled(true);       // 左上角的指南針，要兩指旋轉才會出現
        mMap.getUiSettings().setMapToolbarEnabled(true);    // 右下角的導覽及開啟 Google Map功能
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

        // 這行指令在 IDE 會出現紅線，不過仍可正常執行，可不予理會
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            if (var.debug) {
                Log.i(TAG, mLastLocation.getLatitude() + "");
                Log.i(TAG, mLastLocation.getLongitude() + "");
            }

            Coordinate.setCoordinate(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            //所在位置
            LatLng nowLocation = new LatLng(Coordinate.dLatitude, Coordinate.dLongitude);
            mMap.addMarker(new MarkerOptions().position(nowLocation).title("You are here!"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(nowLocation));
            //相機定位
            CameraUpdate center = CameraUpdateFactory.newLatLngZoom(nowLocation, 15);
            mMap.animateCamera(center);
            //定時回傳
//            timer.schedule(new LocationTimerTask(), 1000, LOCATION_SAVE_TIME_PRE_SECOND * 1000);
//            saveLocation(Coordinate.dLatitude, Coordinate.dLongitude);

        } else {
            Toast.makeText(this, "偵測不到定位，請確認定位功能已開啟。", Toast.LENGTH_LONG).show();
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));    //開啟設定頁面
        }

    }


    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }

    private void saveLocation(Double latitude, Double longitude) {

        mMapTask = new DriverMapTask(latitude.toString(), longitude.toString());
        mMapTask.execute((Void) null);
    }


    public class DriverMapTask extends AsyncTask<Void, Void, Boolean> {

        private final String mLatitude;
        private final String mLongitude;

        DriverMapTask(String latitude, String longitude) {
            mLatitude = latitude;
            mLongitude = longitude;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            try {
                ArrayList<String> key = new ArrayList<>();
                ArrayList<String> value = new ArrayList<>();

                key.add("phone");
                key.add("latitude");
                key.add("longitude");

                value.add(var.phone);
                value.add(mLatitude);
                value.add(mLongitude);
//                String result = phpConnection.createConnection(getGlobal().driver_location_add, key, value);
                if (var.debug) {
//                    Log.i(TAG, result);
                }
                Thread.sleep(3000);
            } catch (InterruptedException e) {

                mMapTask.cancel(true);
                return false;
            }

            mMapTask.cancel(true);
            return true;
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(TAG, "Connection Google Map Fail");
    }

    @Override
    public void onLocationChanged(Location location) {
        Double mLatiude = location.getLatitude();
        Double mLongitude = location.getLongitude();
        Coordinate.setCoordinate(mLatiude, mLongitude);
    }

    class LocationTimerTask extends TimerTask {
        @Override
        public void run() {
            Log.i(TAG, Coordinate.dLatitude + "");
            Log.i(TAG, Coordinate.dLongitude + "");
//            saveLocation(Coordinate.dLatitude, Coordinate.dLongitude);

        }
    }


}
