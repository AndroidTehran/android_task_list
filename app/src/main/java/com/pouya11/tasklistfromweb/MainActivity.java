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
import com.pouya11.tasklistfromweb.models.Auth;
import com.pouya11.tasklistfromweb.request.SendRequest;
import com.pouya11.tasklistfromweb.request.StaticValues;

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

    public void sendGetRequest(View view) {
        JSONObject request = new JSONObject();
        Log.d("MainThread", "Click on button");
        SendRequest.get(
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("ResponseThread", "successful response");
                        Toast.makeText(MainActivity.this,"Successful response",Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ResponseThread", "Error response");
                        Toast.makeText(MainActivity.this,"Error response",Toast.LENGTH_SHORT).show();
                    }
                },
                this);
    }

    public void btnLoginClicked(View view) {
        Auth auth = new Auth(txtUsername.getText().toString(),
                txtPassword.getText().toString());
        try {

            SendRequest.post(auth.toJsonObject()
                    , new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                editor.putString("token_type", response.getString("token_type"));
                                editor.putString("access_token", response.getString("access_token"));
                                editor.putString("refresh_token", response.getString("refresh_token"));

                                editor.commit();
                                Toast.makeText(MainActivity.this,
                                        "Login successful",
                                        Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(MainActivity.this, TaskListActivity.class));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this,
                                    "Your username or password is incorect",
                                    Toast.LENGTH_SHORT).show();
                        }
                    },
                    this);
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }
}
