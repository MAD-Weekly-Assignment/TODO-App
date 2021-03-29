package com.example.todo_app.data;

import android.app.Application;

import java.util.List;

public class Repository {

    private static Repository INSTANCE;
    AppDatabase db;
    TodoDao dao;

    private Repository(Application application) {
        db = AppDatabase.getDatabase(application);
        dao = db.todoDao();
    }

    public static Repository getRepository(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new Repository(application);
        }
        return INSTANCE;
    }

    public List<Task> getAllTasks() {
        return dao.getAllTasks();
    }

    public void deleteAll(){
        dao.deleteAll();
    }

    public void update(Task task){
        dao.update(task);
    }

    public void addTask(Task task){
        dao.insert(task);
    }
}