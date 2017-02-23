package com.pouya11.tasklistfromweb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.pouya11.tasklistfromweb.models.Auth;
import com.pouya11.tasklistfromweb.request.SendRequest;
import com.pouya11.tasklistfromweb.request.StaticValues;
import com.pouya11.tasklistfromweb.services.LoginService;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText txtUsername;
    EditText txtPassword;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsername = (EditText) findViewById(R.id.txt_username);
        txtPassword = (EditText) findViewById(R.id.txt_password);

        sharedPreferences = getSharedPreferences("com.pouya11.tasklistfromweb", MODE_PRIVATE);

        if(! sharedPreferences.getString("access_token", "").equals("")){
            finish();
            startActivity(new Intent(this, TaskListActivity.class));
        }

        editor = sharedPreferences.edit();
    }

    public void btnLoginClicked(View view) {
        Auth auth = new Auth(txtUsername.getText().toString(),
                txtPassword.getText().toString());

        try {
            LoginService.login(auth, this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
