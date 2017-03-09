package com.pouya11.tasklistfromweb.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.pouya11.tasklistfromweb.EditTaskActivity;
import com.pouya11.tasklistfromweb.R;
import com.pouya11.tasklistfromweb.models.Task;

import java.util.List;

/**
 * Created by Mohammad on 23/02/2017.
 * this is cutom adapter for tasks listView
 */
public class TaskListAdapter extends ArrayAdapter<Task> {
    public TaskListAdapter(Context context, int resource, List<Task> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_list_item, parent, false);

            TextView title = (TextView) convertView.findViewById(R.id.txt_task_title);
            title.setText(getItem(position).getTitle());

            ImageButton btnEdit = (ImageButton) convertView.findViewById(R.id.btn_edit_task);
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getContext(), EditTaskActivity.class);
                    i.putExtra("task", new Gson().toJson(getItem(position)));
                    getContext().startActivity(i);
                }
            });
        }

        return convertView;
    }
}
