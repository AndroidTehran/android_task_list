package com.pouya11.tasklistfromweb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.pouya11.tasklistfromweb.request.SendRequest;

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
        String title = txtTitle.getText().toString();
        String description = txtDescription.getText().toString();

        JSONObject request = new JSONObject();
        try {
            request.put("title", title);
            request.put("description", description);

            SendRequest.postWithToken(request,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(CreateTaskActivity.this,
                                    "Created successfully",
                                    Toast.LENGTH_SHORT).show();

                            finish();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(CreateTaskActivity.this,
                                    "Not created",
                                    Toast.LENGTH_SHORT).show();
                        }
                    },
                    CreateTaskActivity.this
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
