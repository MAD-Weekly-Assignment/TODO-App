package com.example.todo_app;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todo_app.data.AppDatabase;
import com.example.todo_app.data.Task;
import com.example.todo_app.data.TodoDao;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    public static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = AppDatabase.getDatabase(this);
        TodoDao dao = db.todoDao();
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                List<Task> tasks = dao.getAllTasks();
                Log.d(TAG, ""+tasks.size());
            }
        });


    }
}