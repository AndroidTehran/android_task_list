package com.pouya11.tasklistfromweb.services;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.pouya11.tasklistfromweb.TaskListActivity;
import com.pouya11.tasklistfromweb.models.Auth;
import com.pouya11.tasklistfromweb.request.SendRequest;
import com.pouya11.tasklistfromweb.request.StaticValues;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Mohammad on 23/02/2017.
 */
public class LoginService {
    public static void login(Auth auth, final AppCompatActivity activity) throws JSONException {
        final SharedPreferences.Editor editor = activity
                .getSharedPreferences("com.pouya11.tasklistfromweb", activity.MODE_PRIVATE)
                .edit();

        SendRequest.post(
                new JSONObject(new Gson().toJson(auth)),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            editor.putString("token_type", response.getString("token_type"));
                            editor.putString("access_token", response.getString("access_token"));
                            editor.putString("refresh_token", response.getString("refresh_token"));

                            editor.commit();
                            Toast.makeText(activity,
                                    "Login successful",
                                    Toast.LENGTH_SHORT).show();

                            activity.startActivity(new Intent(activity, TaskListActivity.class));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(activity,
                                "Your username or password is incorect",
                                Toast.LENGTH_SHORT).show();
                    }
                },
                activity,
                StaticValues.OAUTH_TOKEN
        );
    }
}
