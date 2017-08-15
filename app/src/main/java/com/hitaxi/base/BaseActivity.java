package com.hitaxi.base;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.hitaxi.activities.PersonInfoAty;
import com.dk.main.R;
import com.hitaxi.activities.LatestNewsAty;
import com.hitaxi.activities.OrderRecordAty;
import com.hitaxi.activities.ReservationAty;
import com.hitaxi.activities.ServiceCenterAty;
import com.hitaxi.db.DBHelper;
import com.hitaxi.object.PersonalDetail;

import static android.provider.BaseColumns._ID;
import static com.hitaxi.db.DBConstants.ACCOUNT;
import static com.hitaxi.db.DBConstants.PASSWORD;
import static com.hitaxi.db.DBConstants.TABLE_NAME;
import static com.hitaxi.db.DBConstants.TOKEN;
import static com.hitaxi.db.DBConstants.USERID;

/**
 * Created by DK on 2017/7/28.
 */

public class BaseActivity extends AppCompatActivity {

    public GlobalVar var;


    public void getGlobal() {
        var = (GlobalVar) getApplicationContext();
    }

    public DBHelper userDBHelper;


    public void openDatabase() {
        userDBHelper = new DBHelper(this);
    }

    public void closeDatabase() {
        userDBHelper.close();
    }


    public Cursor getCursor() {
        SQLiteDatabase db = userDBHelper.getReadableDatabase();
        String[] columns = {_ID, ACCOUNT, PASSWORD, TOKEN, USERID};
        //從Sqlite取資料出來用法
        //cursor.getString(1) = 第一個欄位(ACCOUNT)
        //cursor.getString(2) = 第一個欄位(PASSWORD)
        //cursor.getString(3) = 第一個欄位(TOKEN)

        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            startManagingCursor(cursor);
        }
        return cursor;
    }


    //取代include_menu 用來替換畫面
    public void replaceInclude(int layout) {


        View include = findViewById(R.id.include_menu);
        ViewGroup parent = (ViewGroup) include.getParent();
        int index = parent.indexOfChild(include);
        parent.removeView(include);
        include = getLayoutInflater().inflate(layout, parent, false);
        parent.addView(include, index);
    }

    //初始化相關MENU元件 及 相關事件
    public void setMenuLayout() {
        //起始畫面activity_menu
        setContentView(R.layout.activity_menu);

        //畫面不關
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        menu_click();
        initMenuBar();
        getGlobal();
    }


    //初始化相關MENU元件 及 相關事件
    public void setMenuLayout(int layout) {
        //起始畫面activity_menu
        setContentView(R.layout.activity_menu);

        //畫面不關
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        replaceInclude(layout);
        menu_click();
        initMenuBar();
        menu_text_set();
        getGlobal();
    }


    //MENU必要的宣告
    private void initMenuBar() {
        //menu tool bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {


            @Override
            public void onDrawerStateChanged(int newState) {
                menu_text_set();
            }

        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    public void closeDrawers(DrawerLayout drawer) {
        drawer.closeDrawers();
    }

    //MENU相關事件
    private void menu_click() {

        //選單 -> 個人資料
        findViewById(R.id.imageButton2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent();
                i.setClass(getApplicationContext(), PersonInfoAty.class);
                startActivity(i);
            }
        });
        //選單 -> 最新消息
        findViewById(R.id.latest_news).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent();
                i.setClass(getApplicationContext(), LatestNewsAty.class);
                startActivity(i);
            }
        });
        //選單 -> 接單紀錄
        findViewById(R.id.order_record).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent();
                i.setClass(getApplicationContext(), OrderRecordAty.class);
                startActivity(i);
            }
        });
        //選單 -> 服務中心
        findViewById(R.id.service_center_Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent();
                i.setClass(getApplicationContext(), ServiceCenterAty.class);
                startActivity(i);
            }
        });
        //選單 -> 服務中心
        findViewById(R.id.reservation_Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent();
                i.setClass(getApplicationContext(), ReservationAty.class);
                startActivity(i);
            }
        });


    }

    public void menu_text_set() {
        GlobalVar var = (GlobalVar) getApplicationContext();

        TextView tvDriverName = (TextView) findViewById(R.id.driver_name);
        TextView tvDriverNum = (TextView) findViewById(R.id.driver_number);
        TextView tvDriverTeam = (TextView) findViewById(R.id.taxiteam_name);
        if (var.PersonalDetail.getName() != null) {
            tvDriverName.setText("姓名: " + var.PersonalDetail.getName());
            tvDriverNum.setText("呼號: " + var.PersonalDetail.getCallNum());
            tvDriverTeam.setText("車隊: " + var.PersonalDetail.getTeamName());
        }
    }
}
