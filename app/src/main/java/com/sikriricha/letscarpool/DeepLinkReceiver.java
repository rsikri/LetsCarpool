package com.sarthakmeh.shareyourride;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by sarthakmeh on 13/4/16.
 */
public class DeepLinkReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle extras = intent.getExtras();
        String referrerString = extras.getString("referrer");

        Log.w("TEST", "Referrer is: " + referrerString);
        Toast.makeText(context, referrerString, Toast.LENGTH_LONG).show();

    }
}
