package com.sarthakmeh.shareyourride.Activity;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.sarthakmeh.shareyourride.R;
import com.sarthakmeh.shareyourride.PushDataToServer;
import com.sarthakmeh.shareyourride.Utils.Constants;
import com.sarthakmeh.shareyourride.Utils.JSONParser;

import org.apache.http.HttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class Login extends AppCompatActivity {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Boolean isLoggedIn=false;
    public static String TAG = "ShareYourRide";
    public static Boolean isFirstRun;
    CallbackManager callbackManager;
    LoginButton loginButton;
    Button login,register;
    EditText et_email, et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        Initialize the FB login before inflating the layout to avoid
         inflating error
         */
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_login_old);

        et_email = (EditText) findViewById(R.id.email);
        et_password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoginUser(Login.this).execute(et_email.getText().toString(), et_password.getText().toString());
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(Login.this, Register.class);
                startActivity(register);
            }
        });

        /* Push all the yesterday's data to server at 05:00 am */
        //push_data_to_server();

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            System.out.println("*** My thread is now configured to allow connection");
        }

        prefs = getSharedPreferences("SYD",MODE_PRIVATE);
        editor = prefs.edit();
        isFirstRun = prefs.getBoolean("isFirstRun", true);
        isLoggedIn = prefs.getBoolean("isLoggedIn",false);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }

        //Check if user has already logged in or not
        if(isLoggedIn){

            Intent start = new Intent(Login.this, MainActivity.class);
            Toast.makeText(Login.this, "Welcome", Toast.LENGTH_LONG).show();
            startActivity(start);
        }else {

            loginButton = (LoginButton) findViewById(R.id.login_button);
            loginButton.setReadPermissions(Arrays.asList("public_profile, email, user_birthday, user_friends"));
            loginButton.registerCallback(callbackManager, callback);
        }
    }

    //If user click on FB login button then login him through facebook (FBGraph API used)
    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {

        @Override
        public void onSuccess(final LoginResult loginResult) {

            GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(
                                JSONObject object,
                                GraphResponse response) {
                            try {
                                new RegisterUser(Login.this).execute(object.getString("id"),
                                        object.getString("name"), object.getString("email"),
                                        object.getString("gender"), new JSONObject(new JSONObject(object.getString("picture")).getString("data")).getString("url"),
                                        loginResult.getAccessToken().getToken());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });

            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,email,gender,picture");
            request.setParameters(parameters);
            request.executeAsync();
        }
        @Override
        public void onCancel() {
            Log.d("result","cancel");
        }

        @Override
        public void onError(FacebookException e) {
            Log.d("result","exception");
        }
    };

    public class LoginUser extends AsyncTask<String, Void, Boolean> {

        Context context;
        JSONObject response;
        JSONParser jsonParser = new JSONParser();
        String url = Constants.url + "login";
        List<BasicNameValuePair> params = new ArrayList<>();
        ProgressDialog progressDialog;

        public LoginUser(Context ctx) {
            this.context = ctx;
        }

        @Override
        protected void onPreExecute(){
            progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("Please wait");
            progressDialog.setMessage("Logging In..");
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... str) {

            params.add(new BasicNameValuePair("username",str[0]));
            params.add(new BasicNameValuePair("password",str[1]));

            response = jsonParser.makeHttpRequest(url,"POST",params);

            return true;
        }

        protected void onPostExecute(Boolean b) {
            progressDialog.dismiss();

            try {
                if (response.getInt("status_code") == 200){

                    editor.putString("username", et_email.getText().toString());
                    editor.putBoolean("isLoggedIn", true);
                    editor.commit();
                    Intent start = new Intent(Login.this,MainActivity.class);
                    startActivity(start);

                }else{
                    Toast.makeText(Login.this,"Please try again some problem occurred",Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public class RegisterUser extends AsyncTask<String, Void, Boolean> {

        Context context;
        JSONObject response;
        JSONParser jsonParser = new JSONParser();
        String url = Constants.url + "fb_register";
        List<BasicNameValuePair> params = new ArrayList<>();
        ProgressDialog progressDialog;

        public RegisterUser(Context ctx) {
            this.context = ctx;
        }

        @Override
        protected void onPreExecute(){
            progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("Please wait");
            progressDialog.setMessage("Logging In..");
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... str) {


            editor.putString("userID", str[0]);
            editor.putString("username", str[1]);
            editor.putString("gender", str[3]);
            editor.putString("fb_access_token", str[5]);
            params.add(new BasicNameValuePair("username",str[1]));
            params.add(new BasicNameValuePair("email",str[2]));
            params.add(new BasicNameValuePair("gender",str[3]));
            params.add(new BasicNameValuePair("picture_url",str[4]));

            response = jsonParser.makeHttpRequest(url,"POST",params);

            return true;
        }

        protected void onPostExecute(Boolean b) {
            progressDialog.dismiss();

            try {
                if (response.getInt("status_code") == 201 || response.getInt("status_code") == 409){

                    editor.putBoolean("isLoggedIn", true);
                    editor.putBoolean("fbLogin", true);
                    editor.commit();
                    Intent start = new Intent(Login.this,MainActivity.class);
                    startActivity(start);

                }else{
                    Toast.makeText(Login.this,"Please try again some problem occurred",Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Log.d(TAG, "FINE LOCATION permission granted");
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            2);
                } else {
                    // permission denied, so close the app with toast
                    Toast.makeText(this, "Sorry app needs permission to proceed", Toast.LENGTH_LONG).show();
                    finish();
                }
                return;
            }
            case 2: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                }else{
                    // permission denied, so close the app with toast
                    Toast.makeText(this, "Sorry app needs permission to proceed", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
            return;
        }
    }

    public void push_data_to_server(){
        /* Call PushDataToServer receiver at 5:00 am */
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.set(Calendar.HOUR_OF_DAY, 05);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        //check if we want to wake up tomorrow
        if (System.currentTimeMillis() > cal.getTimeInMillis()){
            cal.setTimeInMillis(cal.getTimeInMillis() + 24 * 60 * 60 * 1000);// Okay, then tomorrow ...
        }

        Intent start = new Intent(this,PushDataToServer.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,start,0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
    }
}
