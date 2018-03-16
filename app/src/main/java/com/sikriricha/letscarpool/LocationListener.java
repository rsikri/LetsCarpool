package com.sarthakmeh.shareyourride;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.sarthakmeh.shareyourride.Services.LocationService;
import com.sarthakmeh.shareyourride.Utils.DBHelper;
import static com.sarthakmeh.shareyourride.Activity.Login.*;
import java.util.Calendar;
import java.util.Date;


public class LocationListener implements android.location.LocationListener {

    Context ctx;
    String latitude,longitude,TAG="ShareYourRide";
    Location oldLocation;
    float distance;
    boolean user_travelling,check_user_travelling;
    long time_elapsed = 15 * 60000;
    DBHelper dbHelper;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    public LocationListener(Context context,Location loc,boolean user_t){
        oldLocation = loc;
        ctx = context;
        check_user_travelling = false;
        user_travelling = user_t;
        dbHelper = new DBHelper(ctx);
    }

    @Override
    public void onLocationChanged(final Location location) {
        Log.i("********************", "Location changed*****************");

        /*
             When app runs for the first time, Store the current location
        */

        prefs = ctx.getSharedPreferences("SYD",MODE_PRIVATE);
        isFirstRun = prefs.getBoolean("isFirstRun", true);

        if (isFirstRun) {

            prefs = ctx.getSharedPreferences("SYD", MODE_PRIVATE);
            editor = prefs.edit();
            oldLocation = LocationService.locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            String time = Calendar.getInstance().getTime().toString();
            dbHelper.insertData(time, Double.toString(oldLocation.getLatitude())
                    , Double.toString(oldLocation.getLongitude()), "User Installed App");
            editor.putBoolean("isFirstRun", false);
            editor.commit();
            isFirstRun = false;

        }else{
            /*
              Else get the location where user installed App
             */

            if(oldLocation == null) {

                Cursor cursor = dbHelper.getData();
                oldLocation = new Location(LocationManager.GPS_PROVIDER);
                if (cursor != null && cursor.moveToFirst()) {

                    oldLocation.setLatitude(Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow("latitude"))));
                    oldLocation.setLongitude(Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow("longitude"))));

                }else{

                    Log.d(TAG,"old location not null");

                }
            }
        }

        /*
         If distance b/w oldLocation and newLocation is greater than 1000m(1km)
          Assume user has start travelling ,After 10 minutes check user location
          */
        latitude = Double.toString(location.getLatitude());
        longitude = Double.toString(location.getLongitude());
        distance = oldLocation.distanceTo(location);

        Log.d("distance", Double.toString(distance));
        //Toast.makeText(ctx,"Distance "+Double.toString(distance),Toast.LENGTH_LONG).show();

        if(check_user_travelling){

            Log.d(TAG,"Still Checking if user is travelling or not");

        }else {
            /* distance is in meters */
            if (distance > 500) {
                check_user_travelling = true;
                Toast.makeText(ctx, "Distance travelled greater than 500m", Toast.LENGTH_LONG).show();
                /*
                   After 10 minutes check user's location
                */
                Intent intent = new Intent(ctx, GetLocation.class);
                intent.putExtra("latitude", latitude);
                intent.putExtra("longitude", longitude);
                intent.putExtra("user_travelling", user_travelling);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(ctx, (int) distance, intent, 0);
                AlarmManager alarm = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);

                //ADD 10 minutes to current time
                Calendar c = Calendar.getInstance();
                c.add(Calendar.MINUTE, 15);
                alarm.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);

            } else {

                if (user_travelling) {

                    Date current_time = Calendar.getInstance().getTime();
                    long diff = current_time.getTime() - GetLocation.time.getTime();
                    if (diff > time_elapsed) {

                        //Toast.makeText(ctx, "User stopped travelling", Toast.LENGTH_LONG).show();
                        user_travelling = false;
                        dbHelper.insertData(current_time.toString(), latitude
                                , longitude, "User Stopped travelling");

                    }
                }
            }
        }
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(ctx, "Gps Disabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(ctx, "Gps Enabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Toast.makeText(ctx, "Provider changed", Toast.LENGTH_SHORT).show();
    }
}
