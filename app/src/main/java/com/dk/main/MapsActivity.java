package com.dk.main;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
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

import java.util.ArrayList;

import static com.dk.main.DBConstants.ACCOUNT;
import static com.dk.main.DBConstants.PASSWORD;
import static com.dk.main.DBConstants.TABLE_NAME;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, ConnectionCallbacks, OnConnectionFailedListener, LocationSource.OnLocationChangedListener {

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

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
    private GlobalVar var;
    private GoogleMap mMap;
    Coordinate Coordinate = new Coordinate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        buildGoogleApiClient();
        var = ((GlobalVar) getApplicationContext());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

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
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
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
    public void onLocationChanged(Location location) {
        Double mLatitude = location.getLatitude();
        Double mLongitude = location.getLongitude();
        LatLng latLng = new LatLng(mLatitude, mLongitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
//        mMap.addMarker(new MarkerOptions().position(latLng).title("You are here!"));
        changeLocation(mLatitude,mLongitude);
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

        // 這行指令在 IDE 會出現紅線，不過仍可正常執行，可不予理會
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            Log.i(TAG, mLastLocation.getLatitude() + "");
            Log.i(TAG, mLastLocation.getLongitude() + "");
            Coordinate.setCoordinate(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            //

            LatLng nowLocation = new LatLng(Coordinate.dLatitude, Coordinate.dLongitude);
            mMap.addMarker(new MarkerOptions().position(nowLocation).title("You are here!"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(nowLocation));
            //
            CameraUpdate center = CameraUpdateFactory.newLatLngZoom(nowLocation, 15);

            mMap.animateCamera(center);
            changeLocation(Coordinate.dLatitude, Coordinate.dLongitude);

            //

        } else {
            Toast.makeText(this, "偵測不到定位，請確認定位功能已開啟。", Toast.LENGTH_LONG).show();
        }

    }



    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }

    private void changeLocation(Double latitude, Double longitude) {
        if (mMapTask != null) {
            return;
        }
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

//                key.add("did");
                key.add("latitude");
                key.add("longitude");
//                value.add("1");
                value.add(mLatitude);
                value.add(mLongitude);
                String result = phpConnection.createConnection(var.driver_location_add, key, value);
                Log.i(TAG, result);
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                return false;

            }

            // TODO: register the new account here.
            return true;
        }

    }

}
