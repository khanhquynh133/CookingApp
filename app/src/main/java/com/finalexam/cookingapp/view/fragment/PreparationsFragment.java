package com.finalexam.cookingapp.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.finalexam.cookingapp.R;

public class PreparationsFragment extends Fragment {
    EditText etPreparation;

    public PreparationsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_preparations, container, false);

        etPreparation = view.findViewById(R.id.et_preparation);

        return view;
    }
}