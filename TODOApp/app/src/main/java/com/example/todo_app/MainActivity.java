package com.example.todo_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.todo_app.data.Repository;
import com.example.todo_app.data.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    public static final int UPDATE_WORD_ACTIVITY_REQUEST_CODE = 2;

    public static final String EXTRA_DATA_UPDATE_WORD = "extra_word_to_be_updated";
    public static final String EXTRA_DATA_ID = "extra_data_id";
    static final String STATE_FRAGMENT = "state_of_fragment";
    private MainViewModel mMainViewModel;
    private boolean isFragmentDisplayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState != null) {
            isFragmentDisplayed = savedInstanceState.getBoolean(STATE_FRAGMENT);
        }


        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final TaskAdapter adapter = new TaskAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        mMainViewModel.getAllWords().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable final List<Task> tasks) {

                adapter.setWords(tasks);
            }
        });

        Button deleteAll = findViewById(R.id.delAll);
        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Repository(getApplication()).deleteAll();
                displayFragment();
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeFragment();
                Intent intent = new Intent(MainActivity.this, com.example.todo_app.AddTaskActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });


        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override

                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }


                    @Override

                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();
                        Task myTask = adapter.getWordAtPosition(position);

                        mMainViewModel.deleteWord(myTask);
                    }
                });

        helper.attachToRecyclerView(recyclerView);


        adapter.setOnItemClickListener(new TaskAdapter.ClickListener() {

            @Override
            public void onItemClick(View v, int position) {
                Task task = adapter.getWordAtPosition(position);
                launchUpdateWordActivity(task);
            }
        });
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Task task = new Task(data.getStringExtra(com.example.todo_app.AddTaskActivity.EXTRA_REPLY));

            mMainViewModel.insert(task);
        } else if (requestCode == UPDATE_WORD_ACTIVITY_REQUEST_CODE
                && resultCode == RESULT_OK) {
            String word_data = data.getStringExtra(com.example.todo_app.AddTaskActivity.EXTRA_REPLY);
            int id = data.getIntExtra(com.example.todo_app.AddTaskActivity.EXTRA_REPLY_ID, -1);

            if (id != -1) {
                mMainViewModel.update(new Task(id, word_data));
            } else {
                Log.e("err", "Unable");
            }
        } else {
            Log.e("err", "Empty");
        }
    }

    public void launchUpdateWordActivity(Task task) {
        Intent intent = new Intent(this, com.example.todo_app.AddTaskActivity.class);
        intent.putExtra(EXTRA_DATA_UPDATE_WORD, task.getWord());
        intent.putExtra(EXTRA_DATA_ID, task.getId());
        startActivityForResult(intent, UPDATE_WORD_ACTIVITY_REQUEST_CODE);
    }


    public void displayFragment() {

        DeleteFragment simpleFragment = DeleteFragment.newInstance();


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        fragmentTransaction.add(R.id.fragment_container, simpleFragment).addToBackStack(null).commit();


        isFragmentDisplayed = true;

    }

    public void closeFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();


        DeleteFragment simpleFragment = (DeleteFragment) fragmentManager.findFragmentById(R.id.fragment_container);
        if (simpleFragment != null) {

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(simpleFragment).commit();
        }


        isFragmentDisplayed = false;

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed);
    }

}