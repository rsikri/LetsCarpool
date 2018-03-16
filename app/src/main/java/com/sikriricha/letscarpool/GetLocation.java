package com.sarthakmeh.shareyourride;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;

import com.sarthakmeh.shareyourride.Services.LocationService;
import com.sarthakmeh.shareyourride.Utils.DBHelper;
import static com.sarthakmeh.shareyourride.Activity.Login.*;

import java.util.Calendar;
import java.util.Date;

public class GetLocation extends BroadcastReceiver {

    float distance;
    DBHelper db;
    public static Date time;
    Boolean user_travelling;
    Location oldLoc,newLoc;

    @Override
    public void onReceive(Context context, Intent intent) {

        LocationService.locationManager.removeUpdates(LocationService.listener);
        Toast.makeText(context, "10min over", Toast.LENGTH_LONG).show();
        user_travelling = intent.getBooleanExtra("user_travelling", false);

        /* User's current location after 10 min */
        newLoc = LocationService.locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        Log.d(TAG,intent.getStringExtra("latitude"));
        Log.d(TAG,intent.getStringExtra("longitude"));
        Log.d(TAG,Double.toString(newLoc.getLatitude()));
        Log.d(TAG,Double.toString(newLoc.getLongitude()));


        /* User's location before 10 min */
        oldLoc = new Location(LocationManager.GPS_PROVIDER);
        oldLoc.setLatitude(Double.parseDouble(intent.getStringExtra("latitude")));
        oldLoc.setLongitude(Double.parseDouble(intent.getStringExtra("longitude")));

        /*
         *Calculate distance b/w old and new location
         */
        distance = oldLoc.distanceTo(newLoc);
        Log.d("distances", Double.toString(distance));

        /*
        If user has travelled 3km in 15 minutes then write location to DB
         */

        if (distance > 3000) {

            Toast.makeText(context, "Distance travelled > 3km", Toast.LENGTH_LONG).show();
            Log.d(TAG, "Distance greater than 3km");

            db = new DBHelper(context);

            if (user_travelling) {

                Log.d(TAG, "user travelling");
                Toast.makeText(context, "User travelling", Toast.LENGTH_LONG).show();
                time = Calendar.getInstance().getTime();
                db.insertData(time.toString(), Double.toString(oldLoc.getLatitude())
                        , Double.toString(oldLoc.getLongitude()), "User travelling");

            } else {

                Log.d(TAG, "User started travelling");
                Toast.makeText(context, "User started travelling", Toast.LENGTH_LONG).show();
                time = Calendar.getInstance().getTime();
                db.insertData(time.toString(), Double.toString(oldLoc.getLatitude())
                        , Double.toString(oldLoc.getLongitude()), "User Started travelling");
                user_travelling = true;

            }
            LocationService.listener = new LocationListener(context,newLoc,user_travelling);
        } else {

            Toast.makeText(context, "Distance travelled not > 3km", Toast.LENGTH_LONG).show();
            Log.d(TAG, "distance not greater than 3km");
            LocationService.listener = new LocationListener(context,oldLoc,user_travelling);

        }
        LocationService.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, LocationService.listener);
    }
}
