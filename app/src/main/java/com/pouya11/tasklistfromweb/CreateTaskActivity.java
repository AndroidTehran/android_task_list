package com.pouya11.tasklistfromweb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.pouya11.tasklistfromweb.models.Task;
import com.pouya11.tasklistfromweb.request.SendRequest;
import com.pouya11.tasklistfromweb.services.CreateTaskService;

import org.json.JSONException;
import org.json.JSONObject;

public class CreateTaskActivity extends AppCompatActivity {

    EditText txtTitle;
    EditText txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        txtTitle = (EditText) findViewById(R.id.txt_title);
        txtDescription = (EditText) findViewById(R.id.txt_description);
    }

    public void btnSaveClicked(View view) {
        Task task = new Task(
                txtTitle.getText().toString(),
                txtDescription.getText().toString()
        );

        try {
            CreateTaskService.store(task, this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
