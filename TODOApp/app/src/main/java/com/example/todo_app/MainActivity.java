package com.example.todo_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import com.example.todo_app.data.Repository;
import com.example.todo_app.data.Task;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static String TAG = MainActivity.class.getSimpleName();

    private RecyclerView recyclerView;

    private Repository repository;
    private List<Task> taskList;
    private TaskAdapter adapter;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.task_list);
        fab = findViewById(R.id.floatingActionButton);

        repository = Repository.getRepository(this.getApplication());
        taskList = repository.getAllTasks();
        adapter = new TaskAdapter();
        adapter.setDate(taskList);
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        taskList = repository.getAllTasks();
        adapter.setDate(taskList);

    }
}
