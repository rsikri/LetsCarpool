package com.sarthakmeh.shareyourride.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sarthakmeh.shareyourride.R;
import com.sarthakmeh.shareyourride.Utils.Constants;
import com.sarthakmeh.shareyourride.Utils.JSONParser;

import org.apache.http.HttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Register extends Activity {

    EditText edName,edEmail,edPass,edRePass;
    Button submit;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        prefs = getSharedPreferences("SYD",MODE_PRIVATE);
        editor = prefs.edit();

        //Initialize the layouts
        edName = (EditText) findViewById(R.id.name);
        edEmail = (EditText) findViewById(R.id.email);
        edPass = (EditText) findViewById(R.id.pass);
        edRePass = (EditText) findViewById(R.id.repass);
        submit = (Button) findViewById(R.id.submit);

        /***
         * When user clicks submit check if user has filled all the fields
         * If yes then check if both passwords are same */
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edName.getText().toString();
                String email = edEmail.getText().toString();
                String pass = edPass.getText().toString();
                String repass = edRePass.getText().toString();

                if (!name.isEmpty() && !email.isEmpty() && !pass.isEmpty()) {
                    if (pass.matches(repass)) {
                        new RegisterUser(Register.this).execute(name,email,pass);
                    }else{
                        Toast.makeText(Register.this,"Password do not match",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(Register.this,"Please fill all the fields",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public class RegisterUser extends AsyncTask<String, Void, Boolean> {

        Context context;
        JSONObject response;
        JSONParser jsonParser = new JSONParser();
        String url= Constants.url + "register";
        List<BasicNameValuePair> params = new ArrayList<>();
        ProgressDialog progressDialog;
        String user;

        public RegisterUser(Context ctx) {
            this.context = ctx;
        }

        @Override
        protected void onPreExecute(){
            progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("Please wait");
            progressDialog.setMessage("Registering..");
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... str) {

            user = str[0];
            params.add(new BasicNameValuePair("username",str[0]));
            params.add(new BasicNameValuePair("email",str[1]));
            params.add(new BasicNameValuePair("password",str[2]));

            response = jsonParser.makeHttpRequest(url,"POST",params);

            return true;
        }

        protected void onPostExecute(Boolean b) {
            progressDialog.dismiss();

            try {
                if (response.getInt("status_code") == 201){
                    editor.putBoolean("isLoggedIn", true);
                    editor.putString("username",user);
                    editor.commit();
                    Intent start = new Intent(Register.this,MainActivity.class);
                    startActivity(start);

                }else if (response.getInt("status_code") == 409){
                    Toast.makeText(Register.this,"Please try with some other email",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Register.this,"Please try again some problem occurred",Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
