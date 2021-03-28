package com.example.todo_app.data;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.google.android.material.circularreveal.CircularRevealHelper;

import java.util.List;

@Dao
public interface TodoDao {

    @Query("select * from todos order by priority")
    List<Task> getAllTasks();


    @Update
    void update(Task task);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Task task);




}