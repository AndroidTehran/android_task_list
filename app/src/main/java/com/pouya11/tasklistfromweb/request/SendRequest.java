package com.pouya11.tasklistfromweb.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mohammad on 02/02/2017.
 * this class will send post and get requests
 */
public class SendRequest {
    public static void get(
         JSONObject request,
         Response.Listener<JSONObject> listener,
         Response.ErrorListener errorListener,
         Context context,
         String url
    ) {
     JsonObjectRequest jsObjRequest = new JsonObjectRequest(
             JsonObjectRequest.Method.GET,
             url,
             request,
             listener,
             errorListener);
     RequestQueue queue = Volley.newRequestQueue(context);

     queue.add(jsObjRequest);
    }

    public static void post(
            JSONObject request,
            Response.Listener<JSONObject> listener,
            Response.ErrorListener errorListener,
            Context context,
            String url
    ) {
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(
                JsonObjectRequest.Method.POST,
                url,
                request,
                listener,
                errorListener);
        RequestQueue queue = Volley.newRequestQueue(context);

        queue.add(jsObjRequest);
    }

    public static void getWithToken(
            Response.Listener<JSONObject> listener,
            Response.ErrorListener errorListener,
            Context context,
            String url
    ) {
        SharedPreferences sharedPreferences =
                context.getSharedPreferences("com.pouya11.tasklistfromweb",Context.MODE_PRIVATE);
        final String token_type = sharedPreferences.getString("token_type", "");
        final String access_token = sharedPreferences.getString("access_token", "");

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(
                JsonObjectRequest.Method.GET,
                url,
                null,
                listener,
                errorListener){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Authorization", token_type + " " + access_token);

                return headers;
            }
        };


        RequestQueue queue = Volley.newRequestQueue(context);

        queue.add(jsObjRequest);
    }

    public static void postWithToken(
            JSONObject request,
            Response.Listener<JSONObject> listener,
            Response.ErrorListener errorListener,
            Context context,
            String url
    ) {

        SharedPreferences sharedPreferences =
                context.getSharedPreferences("com.pouya11.tasklistfromweb",Context.MODE_PRIVATE);
        final String token_type = sharedPreferences.getString("token_type", "");
        final String access_token = sharedPreferences.getString("access_token", "");

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(
                JsonObjectRequest.Method.POST,
                url,
                request,
                listener,
                errorListener){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Authorization", token_type + " " + access_token);

                return headers;
            }
        };


        RequestQueue queue = Volley.newRequestQueue(context);

        queue.add(jsObjRequest);
    }


}
