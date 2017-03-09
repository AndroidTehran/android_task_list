package com.pouya11.tasklistfromweb.services;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pouya11.tasklistfromweb.R;
import com.pouya11.tasklistfromweb.TaskListActivity;
import com.pouya11.tasklistfromweb.adapters.TaskListAdapter;
import com.pouya11.tasklistfromweb.models.ModelObjcet;
import com.pouya11.tasklistfromweb.models.Pagination;
import com.pouya11.tasklistfromweb.models.Task;
import com.pouya11.tasklistfromweb.request.SendRequest;
import com.pouya11.tasklistfromweb.request.StaticValues;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Mohammad on 23/02/2017.
 */
public class TaskListService {
    public static void index(final TaskListActivity taskListActivity) {
        SendRequest.getWithToken(
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String jsonString = response.toString();
                        Gson gson = new Gson();

                        Pagination<Task> tasks = gson.fromJson(jsonString, new TypeToken<Pagination<Task>>() {
                        }.getType());
                        List<Task> taskList = tasks.getData();

                        taskListActivity.getLstTaskList().setAdapter(
                                new TaskListAdapter(taskListActivity,
                                        R.layout.task_list_item,
                                        taskList));

                        Log.i("TaskList", "Everything is ok");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TaskList", error.getMessage());
                    }
                },
                taskListActivity,
                StaticValues.TASK_ROUTE
        );
    }


}
