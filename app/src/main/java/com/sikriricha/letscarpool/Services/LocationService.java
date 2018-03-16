package com.sarthakmeh.shareyourride.Services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.IBinder;
import android.util.Log;

import static com.sarthakmeh.shareyourride.Activity.Login.*;
import com.sarthakmeh.shareyourride.LocationListener;
import com.sarthakmeh.shareyourride.Utils.DBHelper;


public class LocationService extends Service {

    public static LocationManager locationManager;
    public static LocationListener listener;
    Boolean isGPSEnabled,isNetworkEnabled;
    public String provider_info;
    DBHelper db;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Location loc;

    public LocationService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Location Service Created");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        db = new DBHelper(this);
        prefs = getSharedPreferences("SYD",MODE_PRIVATE);
        editor = prefs.edit();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //getting GPS status
        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        //getting network status
        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        // Try to get location if you GPS Service is enabled
        if (isGPSEnabled) {

            Log.d(TAG, "Application use GPS Service");

                /*
                 * This provider determines location using
                 * satellites. Depending on conditions, this provider may take a while to return
                 * a location fix.
                 */

            provider_info = LocationManager.GPS_PROVIDER;

        } else if (isNetworkEnabled) { // Try to get location if you Network Service is enabled

            Log.d(TAG, "Application use Network State to get GPS coordinates");

                /*
                 * This provider determines location based on
                 * availability of cell tower and WiFi access points. Results are retrieved
                 * by means of a network lookup.
                 */
            provider_info = LocationManager.NETWORK_PROVIDER;
        }

        if(locationManager!=null) {
            listener = new LocationListener(getApplicationContext(), loc, false);
            locationManager.requestLocationUpdates(provider_info, 0, 0, listener);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Location service destroyed");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
