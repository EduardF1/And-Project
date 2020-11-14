package com.example.civilization_app.uis.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.civilization_app.R;
import com.example.civilization_app.viewmodels.CivilizationsViewModel;
import com.google.android.material.textview.MaterialTextView;

public class CivilizationsFragment extends Fragment {

    private static final String TAG = "CivilizationsFragment";

    CivilizationsViewModel mViewModel;
    MaterialTextView mIdTextView;
    MaterialTextView mNameTextView;
    MaterialTextView mExpansionTextView;
    MaterialTextView mTypeTextView;
    Button mButton;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_civilizations, container, false);
        Log.d(TAG,"onCreateView called");

        mViewModel = new ViewModelProvider(this).get(CivilizationsViewModel.class);

        mIdTextView = v.findViewById(R.id.civ_id);
        mNameTextView = v.findViewById(R.id.civ_name);
        mExpansionTextView = v.findViewById(R.id.civ_expansion);
        mTypeTextView = v.findViewById(R.id.civ_type);




        mButton = v.findViewById(R.id.civ_button);
        mButton.setOnClickListener(this::onClick);


        return v;
    }

    private void onClick(View view) {
        mViewModel.setCiv();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        mViewModel.getCiv().observe(getViewLifecycleOwner(), civ -> {
            //mIdTextView.setText(civ.id);
            if (civ != null) {

                mNameTextView.setText(civ.civilizationName);
                mExpansionTextView.setText(civ.expansion);
                mTypeTextView.setText(civ.armyType);
            }
        });

    }
}