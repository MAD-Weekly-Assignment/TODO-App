
package com.example.todo_app.data;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import java.util.List;


public class Repository {

    private TodoDao mTodoDao;
    private LiveData<List<Task>> mAllWords;

    public Repository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mTodoDao = db.wordDao();
        mAllWords = mTodoDao.getAllWords();
    }

    public LiveData<List<Task>> getAllWords() {
        return mAllWords;
    }

    public void insert(Task task) {
        new insertAsyncTask(mTodoDao).execute(task);
    }

    public void update(Task task)  {
        new updateWordAsyncTask(mTodoDao).execute(task);
    }

    public void deleteAll()  {
        new deleteAllWordsAsyncTask(mTodoDao).execute();
    }

    public void deleteWord(Task task) {
        new deleteWordAsyncTask(mTodoDao).execute(task);
    }


    private static class insertAsyncTask extends AsyncTask<Task, Void, Void> {

        private TodoDao mAsyncTaskDao;

        insertAsyncTask(TodoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Task... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }


    private static class deleteAllWordsAsyncTask extends AsyncTask<Void, Void, Void> {
        private TodoDao mAsyncTaskDao;

        deleteAllWordsAsyncTask(TodoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    private static class deleteWordAsyncTask extends AsyncTask<Task, Void, Void> {
        private TodoDao mAsyncTaskDao;

        deleteWordAsyncTask(TodoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Task... params) {
            mAsyncTaskDao.deleteWord(params[0]);
            return null;
        }
    }


    private static class updateWordAsyncTask extends AsyncTask<Task, Void, Void> {
        private TodoDao mAsyncTaskDao;

        updateWordAsyncTask(TodoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Task... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }
}
