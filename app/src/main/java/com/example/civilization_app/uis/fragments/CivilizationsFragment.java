package com.example.civilization_app.uis.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.civilization_app.R;
import com.example.civilization_app.adapters.CivilizationsAdapter;
import com.example.civilization_app.models.Civilization;
import com.example.civilization_app.viewmodels.CivilizationsViewModel;

import java.util.List;

public class CivilizationsFragment extends Fragment {

    private static final String TAG = "CivilizationsFragment";

    CivilizationsViewModel mViewModel;
    EditText civ_name_edit_text;
    EditText civ_expansion_edit_text;
    EditText civ_army_type_edit_text;
    Button addCivButton;
    Button deleteAllCivsButton;
    RecyclerView civilizationsList;
    CivilizationsAdapter civilizationsAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_civilizations, container, false);
        Log.d(TAG, "onCreateView called -- Civilizations fragment");

        mViewModel = new ViewModelProvider(this).get(CivilizationsViewModel.class);


        civ_name_edit_text = v.findViewById(R.id.editCivName);
        civ_expansion_edit_text = v.findViewById(R.id.editCivExpansion);
        civ_army_type_edit_text = v.findViewById(R.id.editCivArmyType);


        addCivButton = v.findViewById(R.id.addButton);
        addCivButton.setOnClickListener(this::onClickAdd);

        deleteAllCivsButton = v.findViewById(R.id.deleteAllButton);
        deleteAllCivsButton.setOnClickListener(this::onClickDeleteAll);

        civilizationsList = v.findViewById(R.id.recyclerViewCivilizations);
        civilizationsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        civilizationsList.hasFixedSize();


        civilizationsAdapter = new CivilizationsAdapter();
        civilizationsList.setAdapter(civilizationsAdapter);
        mViewModel.getAllCivilizations().observe(getViewLifecycleOwner(), civilizations -> {
            civilizationsAdapter.setCivilizations(civilizations);
        });


        return v;
    }

    private void onClickAdd(View view) {
        if (!(civ_name_edit_text.getText().toString().equals("") ||
                civ_expansion_edit_text.getText().toString().equals("") ||
                civ_army_type_edit_text.getText().toString().equals(""))) {
            mViewModel.insertCivilization(new Civilization(civ_name_edit_text.getText().toString(),
                    civ_expansion_edit_text.getText().toString(),
                    civ_army_type_edit_text.getText().toString()));
            Toast.makeText(getContext(), "Added to collection !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Missing information !", Toast.LENGTH_SHORT).show();
        }
    }

    private void onClickDeleteAll(View view){
        mViewModel.deleteAllCivilizations();
        Toast.makeText(getContext(), "Collection deleted !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}