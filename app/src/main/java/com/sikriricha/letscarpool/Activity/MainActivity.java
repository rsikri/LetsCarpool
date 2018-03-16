package com.sarthakmeh.shareyourride.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.sarthakmeh.shareyourride.Adapters.TabPagerAdapter;
import com.sarthakmeh.shareyourride.R;
import com.facebook.login.LoginManager;
import com.sarthakmeh.shareyourride.Utils.Constants;
import com.sarthakmeh.shareyourride.Utils.JSONParser;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SharedPreferences.Editor editor;
    SharedPreferences prefs;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("SYD", MODE_PRIVATE);
        editor = prefs.edit();

        // Get friend list who also uses SYD
        new FbFriends(this).execute();

//        Intent service = new Intent(this,LocationService.class);
//        startService(service);
    }

    public class FbFriends extends AsyncTask<String, Void, Boolean> {

        Context context;
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;
        JSONObject response;
        String fb_url = "https://graph.facebook.com/v2.1/" + prefs.getString("userID", "") + "/friends?access_token=" +
                prefs.getString("fb_access_token", "");
        String url = Constants.url + "getCarPoolers/";
        List<BasicNameValuePair> params = new ArrayList();
        ProgressDialog progressDialog;

        public FbFriends(Context ctx) {
            this.context = ctx;
        }

        @Override
        protected void onPreExecute(){
            progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("Relax !");
            progressDialog.setMessage("Fetching CarPoolers..");
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... str) {

            jsonObject = jsonParser.makeFBHttpRequest(fb_url, "GET");
            try {
                params.add(new BasicNameValuePair("friendsList", jsonObject.getJSONArray("data").toString()));
                response = jsonParser.makeHttpRequest(url, "POST", params);
            } catch (JSONException e) {
                response = jsonParser.makeHttpRequest(url, "POST", params);
                e.printStackTrace();
            }
            return true;
        }

        protected void onPostExecute(Boolean b) {
            progressDialog.dismiss();
            try {
                jsonArray = response.getJSONObject("content").getJSONArray("data");
                setupTabsWithPager();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void setupTabsWithPager() {
        /**
         * Set up ListView and MapView Fragment in Tabs
         */
        mTabLayout = (TabLayout) findViewById(R.id.tl_activity_home);
        mViewPager = (ViewPager) findViewById(R.id.vp_activity_home);
        mViewPager.setAdapter(new TabPagerAdapter(getSupportFragmentManager(), jsonArray));
        mTabLayout.setupWithViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.logout) {
            editor.clear().commit();
            LoginManager.getInstance().logOut();
//            stopService(new Intent(this, LocationService.class));
//            LocationService.locationManager.removeUpdates(LocationService.listener);
            Intent logout = new Intent(this,Login.class);
            startActivity(logout);
            return true;
        } else if (id == R.id.profile) {
            Intent profile = new Intent(this, Profile.class);
            startActivity(profile);
        }

        return super.onOptionsItemSelected(item);
    }
}
