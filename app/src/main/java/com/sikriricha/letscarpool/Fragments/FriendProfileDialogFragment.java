package com.sarthakmeh.shareyourride.Fragments;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.sarthakmeh.shareyourride.Activity.ChatActivity;
import com.sarthakmeh.shareyourride.R;
import com.sarthakmeh.shareyourride.Utils.Constants;
import com.sarthakmeh.shareyourride.Utils.JSONParser;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.sarthakmeh.shareyourride.R.id.et_car_model;

/**
 * Created by sarthakmeh on 11/6/16.
 */
public class FriendProfileDialogFragment extends DialogFragment {

    private Context context;
    private String user;
    EditText username, gender,phoneNo, homeAdd, officeAdd, carPlateNumber, carModel;
    SeekBar carMileage;
    Button chat;

    public FriendProfileDialogFragment(Context ctx, String username) {
        this.context = ctx;
        this.user = username;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        View rootView = inflater.inflate(R.layout.fragment_friend_profile, container, false);

        username = (EditText) rootView.findViewById(R.id.et_profile_name);
        gender = (EditText) rootView.findViewById(R.id.et_gender);
        phoneNo = (EditText) rootView.findViewById(R.id.et_profile_phone);
        carMileage = (SeekBar) rootView.findViewById(R.id.car_mileage);
        carPlateNumber = (EditText) rootView.findViewById(R.id.et_car_number);
        carModel = (EditText) rootView.findViewById(et_car_model);
        homeAdd = (EditText) rootView.findViewById(R.id.et_home_add);
        officeAdd = (EditText) rootView.findViewById(R.id.et_office_add);
        chat = (Button) rootView.findViewById(R.id.btn_save_profile);

        new GetFriendProfile(context).execute(user);

        chat.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                Intent chat = new Intent(getActivity(), ChatActivity.class);
                chat.putExtra("receiver", user);
                startActivity(chat);
            }
        });

        return rootView;

    }

    public class GetFriendProfile extends AsyncTask<String, Void, Boolean> {

        Context context;
        JSONObject response;
        JSONParser jsonParser = new JSONParser();
        String url = Constants.url + "profile";
        List<BasicNameValuePair> params = new ArrayList<>();
        ProgressDialog progressDialog;

        public GetFriendProfile(Context ctx) {
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
                    username.setText(response.getString("username"));
                    gender.setText(response.getString("gender"));
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
                    Toast.makeText(context, "Please try again some problem occurred", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
    }

}
