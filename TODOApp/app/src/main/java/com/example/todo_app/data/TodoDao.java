

package com.example.todo_app.data;

import androidx.lifecycle.LiveData;
import androidx.room.*;


import java.util.List;


@Dao
public interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Task task);

    @Query("DELETE FROM word_table")
    void deleteAll();

    @Delete
    void deleteWord(Task task);

    @Query("SELECT * from word_table LIMIT 1")
    Task[] getAnyWord();

    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<Task>> getAllWords();

    @Update
    void update(Task... task);
}
