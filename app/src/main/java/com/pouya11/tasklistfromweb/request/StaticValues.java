package com.pouya11.tasklistfromweb.request;

/**
 * Created by Mohammad on 02/02/2017.
 */
public class StaticValues {

    public static final String CLIENT_SECRET = "DXHOToxpmy7KrPjgLzp4PADSUBkyDWGYppmPtrfS";
    public static final int CLIENT_ID = 2;

    public static final String PROTOCOL = "http://";
    public static final String ADDRESS  = PROTOCOL + "192.168.43.197";
    public static final int PORT = 8000;

    public static final String REMOTE_ADDRESS = ADDRESS + ":" + PORT;

    public static final String OAUTH_TOKEN = REMOTE_ADDRESS + "/oauth/token";
    public static final String TASK_ROUTE = REMOTE_ADDRESS + "/api/task";
}
