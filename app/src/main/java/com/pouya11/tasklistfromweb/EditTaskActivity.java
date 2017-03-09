package com.pouya11.tasklistfromweb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.pouya11.tasklistfromweb.models.Task;
import com.pouya11.tasklistfromweb.request.SendRequest;
import com.pouya11.tasklistfromweb.request.StaticValues;

import org.json.JSONException;
import org.json.JSONObject;

public class EditTaskActivity extends AppCompatActivity {

    EditText txtTitle;
    EditText txtDescription;
    Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        txtTitle = (EditText) findViewById(R.id.txt_title);
        txtDescription = (EditText) findViewById(R.id.txt_description);

        task = new Gson().fromJson(getIntent().getStringExtra("task"), Task.class);
        txtTitle.setText(task.getTitle());
        txtDescription.setText(task.getDescription());
    }

    public void btnCancelClicked(View view) {
        finish();
    }

    public void btnSaveClicked(View view) {
        task.setTitle(txtTitle.getText().toString());
        task.setDescription(txtDescription.getText().toString());

        try {
            SendRequest.putWithToken(
                    new JSONObject(new Gson().toJson(task)),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(
                                    EditTaskActivity.this,
                                    "Updated successfully",
                                    Toast.LENGTH_LONG
                            ).show();
                            finish();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(
                                    EditTaskActivity.this,
                                    "Oops something went wrong!",
                                    Toast.LENGTH_LONG
                            ).show();

                            Log.e("Edit task Error", error.getMessage() + " " + error.toString());
                        }
                    },
                    EditTaskActivity.this,
                    StaticValues.TASK_ROUTE + "/" + task.getId()
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
