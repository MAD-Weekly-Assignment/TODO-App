package com.example.todo_app;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todo_app.data.Repository;
import com.example.todo_app.data.Task;
import com.example.todo_app.data.TodoDao;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private Repository repository;
    LiveData<List<Task>> taskList;
    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getRepository(application);
        taskList = repository.getAllTasks();
    }

    public LiveData<List<Task>> getAllTasks(){
        return taskList;
    }
}
