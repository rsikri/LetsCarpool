package com.sarthakmeh.shareyourride;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;

import com.sarthakmeh.shareyourride.Utils.DBHelper;
import com.sarthakmeh.shareyourride.Utils.JSONParser;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Push yesterday data to server at 5:00 am
 */
public class PushDataToServer extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        new PushData(context).execute();
    }

    public class PushData extends AsyncTask<String, Void, Boolean> {

        DBHelper dbHelper;
        Context context;
        JSONArray jsonArray;
        JSONParser jsonParser = new JSONParser();
        String url = "http://172.20.10.5:8000/api/push/data";
        int status_code;
        List<BasicNameValuePair> param = new ArrayList<>();

        public PushData(Context ctx){
            context = ctx;
        }

        @Override
        protected Boolean doInBackground(String... params) {

            Log.d("Pushing Data", "To SERVER");

            dbHelper = new DBHelper(context);
            Cursor cursor = dbHelper.getAllData();
            jsonArray = new JSONArray();

            SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
            Date date = null;
            Calendar c = Calendar.getInstance();
            /* Get yesterday date */
            c.add(Calendar.DATE, -1);
            Date yesterday = c.getTime();

            while (cursor.moveToNext()){

                JSONObject jsonObject = new JSONObject();
                try {
                    date = sdf.parse(cursor.getString(2));
                    yesterday = sdf.parse(yesterday.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                /* Push only yesterday data to server */
                if(date.equals(yesterday)) {
                    try {
                        jsonObject.put("user", cursor.getString(1));
                        jsonObject.put("time", cursor.getString(2));
                        jsonObject.put("latitude", cursor.getString(3));
                        jsonObject.put("longitude", cursor.getString(4));
                        jsonObject.put("remark", cursor.getString(5));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    jsonArray.put(jsonObject);
                }
            }

            Log.d("array",jsonArray.toString());
            param.add(new BasicNameValuePair("data",jsonArray.toString()));
            //status_code = jsonParser.makeHttpRequest(url,"POST",param);
            status_code = 200;

            return true;
        }

        protected void onPostExecute(Boolean b) {

            if(status_code == 200){
                Log.d("ShareYourRide", "Data pushed.");
            }else{
                Log.d("ShareYourRide", "Couldn't push data. Some problem occurred");
            }
        }
    }
}
