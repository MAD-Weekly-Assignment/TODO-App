package com.example.todo_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo_app.data.Task;


import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {


    private List<Task> taskList;

    public TaskAdapter() {
    }

    public void setDate(List<Task> data){
        taskList = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.onBind(task);
    }

    @Override
    public int getItemCount() {
        if(taskList != null)
            return taskList.size();
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView titleTextView;
        private  TextView descTextView;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_task, parent, false));
            titleTextView = itemView.findViewById(R.id.title_tv);
            descTextView = itemView.findViewById(R.id.descripttion_tv);
        }

        public void onBind(Task task) {
            titleTextView.setText(task.getTitle());
            descTextView.setText(task.getDescription());

        }
    }
}