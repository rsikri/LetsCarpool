package com.sarthakmeh.shareyourride.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.sarthakmeh.shareyourride.R;
import com.sarthakmeh.shareyourride.Utils.Constants;
import com.sarthakmeh.shareyourride.Utils.JSONParser;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sarthakmeh on 12/6/16.
 */
public class ChatActivity extends AppCompatActivity {

    ListView lv_messages;
    Button send;
    EditText et_message;
    SharedPreferences preferences;
    ArrayList<String> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences("SYD",MODE_PRIVATE);
        setContentView(R.layout.activity_chat);

        new fetchMessage(this).execute(preferences.getString("username", ""));

        lv_messages = (ListView) findViewById(R.id.lv_messages);
        send = (Button) findViewById(R.id.bt_send);
        et_message = (EditText) findViewById(R.id.et_message);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new sendMessage(ChatActivity.this).execute(getIntent().getStringExtra("receiver"),
                        preferences.getString("username", ""), et_message.getText().toString());
            }
        });
    }

    public class sendMessage extends AsyncTask<String, Void, Boolean> {

        Context context;
        JSONObject response;
        JSONParser jsonParser = new JSONParser();
        String url = Constants.url + "send/message";
        List<BasicNameValuePair> params = new ArrayList<>();

        public sendMessage(Context ctx) {
            this.context = ctx;
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Boolean doInBackground(String... str) {

            params.add(new BasicNameValuePair("sender",str[0]));
            params.add(new BasicNameValuePair("receiver",str[1]));
            params.add(new BasicNameValuePair("content",str[2]));

            response = jsonParser.makeHttpRequest(url,"POST",params);

            return true;
        }

        protected void onPostExecute(Boolean b) {

            try {
                if (response.getInt("status_code") == 200){
                    messages.add(et_message.getText().toString());
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(ChatActivity.this,
                            android.R.layout.simple_list_item_1, android.R.id.text1, messages);
                    lv_messages.setAdapter(adapter);
                    et_message.setText("");

                }else{
                    Toast.makeText(ChatActivity.this, "Please try again some problem occurred", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public class fetchMessage extends AsyncTask<String, Void, Boolean> {

        Context context;
        JSONObject response;
        JSONArray jsonArray;
        JSONParser jsonParser = new JSONParser();
        String url = Constants.url + "fetch/message";
        List<BasicNameValuePair> params = new ArrayList<>();

        public fetchMessage(Context ctx) {
            this.context = ctx;
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Boolean doInBackground(String... str) {

            params.add(new BasicNameValuePair("username",str[0]));

            response = jsonParser.makeHttpRequest(url,"POST",params);

            return true;
        }

        protected void onPostExecute(Boolean b) {

            try {
                if (response.getInt("status_code") == 200){
                    jsonArray = response.getJSONObject("content").getJSONArray("data");
                    for (int i=0; i<jsonArray.length(); i++) {
                        messages.add(jsonArray.getJSONObject(i).getString("content"));
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(ChatActivity.this,
                            android.R.layout.simple_list_item_1, android.R.id.text1, messages);
                    lv_messages.setAdapter(adapter);

                }else{
                    Toast.makeText(ChatActivity.this, "Please try again some problem occurred", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


}
