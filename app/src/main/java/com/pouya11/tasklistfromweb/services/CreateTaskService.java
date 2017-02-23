package com.pouya11.tasklistfromweb.services;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.pouya11.tasklistfromweb.CreateTaskActivity;
import com.pouya11.tasklistfromweb.models.Task;
import com.pouya11.tasklistfromweb.request.SendRequest;
import com.pouya11.tasklistfromweb.request.StaticValues;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Mohammad on 23/02/2017.
 */
public class CreateTaskService {

    public static void store(Task task, final AppCompatActivity activity) throws JSONException {

        SendRequest.postWithToken(
                new JSONObject(new Gson().toJson(task)),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(activity, "Created successfully", Toast.LENGTH_SHORT).show();
                        activity.finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(activity, "Not created", Toast.LENGTH_SHORT).show();
                    }
                },
                activity,
                StaticValues.TASK_ROUTE
        );
    }
}
