package com.sarthakmeh.shareyourride.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.sarthakmeh.shareyourride.R;
import com.sarthakmeh.shareyourride.Utils.Constants;
import com.sarthakmeh.shareyourride.Utils.JSONParser;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.sarthakmeh.shareyourride.R.id.et_car_model;

/**
 * Created by sarthakmeh on 7/6/16.
 */
public class Profile extends AppCompatActivity {

    SharedPreferences preferences;
    EditText username, gender,phoneNo, homeAdd, officeAdd, carPlateNumber, carModel;
    SeekBar carMileage;
    Button save;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        preferences = getSharedPreferences("SYD", MODE_PRIVATE);
        new GetProfile(this).execute(preferences.getString("username", ""));

        username = (EditText) findViewById(R.id.et_profile_name);
        gender = (EditText) findViewById(R.id.et_gender);
        phoneNo = (EditText) findViewById(R.id.et_profile_phone);
        carMileage = (SeekBar) findViewById(R.id.car_mileage);
        carPlateNumber = (EditText) findViewById(R.id.et_car_number);
        carModel = (EditText) findViewById(et_car_model);
        homeAdd = (EditText) findViewById(R.id.et_home_add);
        officeAdd = (EditText) findViewById(R.id.et_office_add);
        save = (Button) findViewById(R.id.btn_save_profile);

        initCarTypeSeekbar();
        username.setText(preferences.getString("username", ""));
        gender.setText(preferences.getString("gender", ""));

        homeAdd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    try {
                        PlacePicker.IntentBuilder intentBuilder =
                                new PlacePicker.IntentBuilder();
                        Intent intent = intentBuilder.build(Profile.this);
                        startActivityForResult(intent, 1);

                    } catch (GooglePlayServicesRepairableException
                            | GooglePlayServicesNotAvailableException e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });

        officeAdd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    try {
                        PlacePicker.IntentBuilder intentBuilder =
                                new PlacePicker.IntentBuilder();
                        Intent intent = intentBuilder.build(Profile.this);
                        startActivityForResult(intent, 2);

                    } catch (GooglePlayServicesRepairableException
                            | GooglePlayServicesNotAvailableException e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new SaveProfile(Profile.this).execute(username.getText().toString(), gender.getText().toString(),
                        phoneNo.getText().toString(), carPlateNumber.getText().toString(),
                        carModel.getText().toString(), homeAdd.getText().toString(), officeAdd.getText().toString());
            }
        });

    }

    public void initCarTypeSeekbar() {
        //carMileage.setProgressDrawable(getResources().getDrawable(R.drawable.uber_seekbar_strip));
        carMileage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekBar.setThumb(getResources().getDrawable(R.drawable.uber_car));
                if (seekBar.getProgress() <= 20) {
                    seekBar.setProgress(5);
                } else if (seekBar.getProgress() > 20 && seekBar.getProgress() <= 75) {
                    seekBar.setProgress(50);
                } else if (seekBar.getProgress() > 75) {
                    seekBar.setProgress(95);
                }
            }
        });
    }

    public String getCarType() {
        /**
         * Return the product type chosen by user
         */
        String mileage = "";

        if (carMileage.getProgress() == 5) {
            mileage = "8-10 Km/L";
        } else if (carMileage.getProgress() == 50) {
            mileage = "15-20 Km/L";
        } else if (carMileage.getProgress() == 95) {
            mileage = "Above 20 Km/L";
        }

        return mileage;
    }

    public String getAddressFromLocation(Location location) {
        Geocoder geocoder;
        List<Address> addresses = null;
        String address = "";
        geocoder = new Geocoder(Profile.this, Locale.getDefault());

        try {
            // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i=0; i <= addresses.get(0).getMaxAddressLineIndex(); i++) {
            address = address.concat(addresses.get(0).getAddressLine(i) + " ");
        }
        return address;
    }

    public class GetProfile extends AsyncTask<String, Void, Boolean> {

        Context context;
        JSONObject response;
        JSONParser jsonParser = new JSONParser();
        String url = Constants.url + "profile";
        List<BasicNameValuePair> params = new ArrayList<>();
        ProgressDialog progressDialog;

        public GetProfile(Context ctx) {
            this.context = ctx;
        }

        @Override
        protected void onPreExecute(){
            progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("Please wait");
            progressDialog.setMessage("Fetching..");
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... str) {

            params.add(new BasicNameValuePair("username", str[0]));

            response = jsonParser.makeHttpRequest(url,"GET",params);

            return true;
        }

        protected void onPostExecute(Boolean b) {
            progressDialog.dismiss();

            try {
                if (response.getInt("status_code") == 200){

                    response = response.getJSONObject("content").getJSONObject("data");
                    phoneNo.setText(response.getString("phoneNo"));
                    carModel.setText(response.getString("carModel"));
                    carPlateNumber.setText(response.getString("carPlateNo"));
                    homeAdd.setText(response.getString("homeAdd"));
                    officeAdd.setText(response.getString("officeAdd"));

                    if (response.getString("carMileage").matches("8-10 Km/L")) {
                        carMileage.setProgress(5);
                    } else if (response.getString("carMileage").matches("15-20 Km/L")) {
                        carMileage.setProgress(50);
                    }else {
                        carMileage.setProgress(95);
                    }

                }else{
                    Toast.makeText(Profile.this, "Please try again some problem occurred", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public class SaveProfile extends AsyncTask<String, Void, Boolean> {

        Context context;
        JSONObject response;
        JSONParser jsonParser = new JSONParser();
        String url = Constants.url + "save/profile";
        List<BasicNameValuePair> params = new ArrayList<>();
        ProgressDialog progressDialog;

        public SaveProfile(Context ctx) {
            this.context = ctx;
        }

        @Override
        protected void onPreExecute(){
            progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("Please wait");
            progressDialog.setMessage("Saving..");
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... str) {

            params.add(new BasicNameValuePair("username",str[0]));
            params.add(new BasicNameValuePair("phone",str[2]));
            params.add(new BasicNameValuePair("plate_no",str[3]));
            params.add(new BasicNameValuePair("model",str[4]));
            params.add(new BasicNameValuePair("mileage",getCarType()));
            params.add(new BasicNameValuePair("home_add",str[5]));
            params.add(new BasicNameValuePair("office_add",str[6]));

            response = jsonParser.makeHttpRequest(url,"POST",params);

            return true;
        }

        protected void onPostExecute(Boolean b) {
            progressDialog.dismiss();

            try {
                if (response.getInt("status_code") == 200){

                    Toast.makeText(Profile.this, "Saved", Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(Profile.this, "Please try again some problem occurred", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {

        if (requestCode == 1
                && resultCode == Activity.RESULT_OK) {

            final Place place = PlacePicker.getPlace(data, Profile.this);
            Location dropLocation = new Location("");
            dropLocation.setLatitude(place.getLatLng().latitude);
            dropLocation.setLongitude(place.getLatLng().longitude);
            homeAdd.setText(getAddressFromLocation(dropLocation));

        } else if(requestCode == 2
                && resultCode == Activity.RESULT_OK){
            final Place place = PlacePicker.getPlace(data, Profile.this);
            Location pickUpLocation = new Location("");
            pickUpLocation.setLatitude(place.getLatLng().latitude);
            pickUpLocation.setLongitude(place.getLatLng().longitude);
            officeAdd.setText(getAddressFromLocation(pickUpLocation));
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
