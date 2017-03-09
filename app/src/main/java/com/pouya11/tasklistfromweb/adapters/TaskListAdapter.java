package com.pouya11.tasklistfromweb.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_list_item, parent, false);

            TextView title = (TextView) convertView.findViewById(R.id.txt_task_title);

            title.setText(getItem(position).getTitle());
        }

        return convertView;
    }
}
