package com.example.todo_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;


public class DeleteFragment extends Fragment {

    private static final int YES = 0;
    private static final int NO = 1;

    public DeleteFragment() {

    }

    public static DeleteFragment newInstance() {
        return new DeleteFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_delete,
                container, false);


        return rootView;
    }
}