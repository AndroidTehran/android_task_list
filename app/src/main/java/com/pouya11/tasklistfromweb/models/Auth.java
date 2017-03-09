package com.pouya11.tasklistfromweb.models;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pouya11.tasklistfromweb.request.StaticValues;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Mohammad on 16/02/2017.
 * this class is for authentication by php
 */
public class Auth {
    @SerializedName("grant_type")
    @Expose
    private String grantType;

    @SerializedName("client_id")
    @Expose
    private int clientId;

    @SerializedName("client_secret")
    @Expose
    private String clientSecret;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("password")
    @Expose
    private String password;


    public Auth(String username, String password){
        this.setUsername(username);
        this.setPassword(password);

        this.setGrantType("password");
        this.setClientId(StaticValues.CLIENT_ID);
        this.setClientSecret(StaticValues.CLIENT_SECRET);
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
