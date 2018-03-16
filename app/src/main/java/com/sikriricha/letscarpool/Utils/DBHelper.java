package com.sarthakmeh.shareyourride.Utils;

/**
 * Created by sarthakmeh on 4/12/15.
 */
import java.util.HashMap;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SYD.db";
    private HashMap hp;
    private SharedPreferences preferences;
    Context ctx;

    public DBHelper(Context context)
    {
        super(context, "/mnt/sdcard/"+DATABASE_NAME, null, 1);
        ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table user_location " +
                        "(id integer primary key AUTOINCREMENT,user text,time text,latitude text,longitude text,remark text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS user_location");
        onCreate(db);
    }

    public boolean insertData  ( String time,String latitude, String longitude, String remark)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("time",time);
        contentValues.put("latitude", latitude);
        contentValues.put("longitude", longitude);
        contentValues.put("remark", remark);
        preferences = ctx.getSharedPreferences("SYD",Context.MODE_PRIVATE);
        contentValues.put("user", preferences.getString("user", null));
        db.insert("user_location", null, contentValues);
        return true;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from user_location where remark= \""+"User Installed App"+"\";", null);
        return res;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from user_location", null);
        return res;
    }
}