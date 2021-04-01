package com.example.todo_app;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.todo_app.data.Repository;
import com.example.todo_app.data.Task;
import java.util.List;



public class MainViewModel extends AndroidViewModel {

    private Repository mRepository;

    private LiveData<List<Task>> mAllWords;

    public MainViewModel(Application application) {
        super(application);
        mRepository = new Repository(application);
        mAllWords = mRepository.getAllWords();
    }

    LiveData<List<Task>> getAllWords() {
        return mAllWords;
    }

    public void insert(Task task) {
        mRepository.insert(task);
    }

    public void deleteAll() {
        mRepository.deleteAll();
    }

    public void deleteWord(Task task) {
        mRepository.deleteWord(task);
    }

    public void update(Task task) {
        mRepository.update(task);
    }
}
