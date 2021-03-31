package com.example.todo_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class DeleteFragment extends Fragment {

    private static final int YES = 0;
    private static final int NO = 1;

    public DeleteFragment() {
        // Required empty public constructor
    }

    public static DeleteFragment newInstance() {
        return new DeleteFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_delete,
                container, false);



        return rootView;
    }
}