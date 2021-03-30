
package com.example.todo_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static com.example.todo_app.MainActivity.EXTRA_DATA_ID;
import static com.example.todo_app.MainActivity.EXTRA_DATA_UPDATE_WORD;


public class AddTaskActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.roomwordssample.REPLY";
    public static final String EXTRA_REPLY_ID = "com.android.example.roomwordssample.REPLY_ID";

    private EditText mEditWordView;
    boolean isFragmentDisplayed = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        mEditWordView = findViewById(R.id.edit_word);
        int id = -1 ;

        final Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String word = extras.getString(EXTRA_DATA_UPDATE_WORD, "");
            if (!word.isEmpty()) {
                mEditWordView.setText(word);
                mEditWordView.setSelection(word.length());
                mEditWordView.requestFocus();
            }
        }


        final Button button = findViewById(R.id.button_save);


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditWordView.getText())) {

                    setResult(RESULT_CANCELED, replyIntent);
                } else {

                    String word = mEditWordView.getText().toString();

                    replyIntent.putExtra(EXTRA_REPLY, word);
					if (extras != null && extras.containsKey(EXTRA_DATA_ID)) {
                        int id = extras.getInt(EXTRA_DATA_ID, -1);
                        if (id != -1) {
                            replyIntent.putExtra(EXTRA_REPLY_ID, id);
                        }
                    }

                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
