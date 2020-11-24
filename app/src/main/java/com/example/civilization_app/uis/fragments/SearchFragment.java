package com.example.civilization_app.uis.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.civilization_app.R;
import com.example.civilization_app.adapters.CivilizationsFromWebAdapter;
import com.example.civilization_app.viewmodels.SearchViewModel;
import com.google.android.material.snackbar.Snackbar;

public class SearchFragment extends Fragment {

    Button fromWebButton;
    EditText editTextReq;
    TextView civName;
    TextView civExp;
    TextView civArmTyp;
    RecyclerView civilizationsFromWebList;
    CivilizationsFromWebAdapter civilizationsFromWebAdapter;


    //TODO create adapter and list item for all civs from web and display in fragment

    private static final String TAG = "ShareFragment";

    private SearchViewModel searchViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        searchViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);


        Log.d(TAG, "onCreateView called -- Share fragment");

        civName = v.findViewById(R.id.civ_name_pl);
        civExp = v.findViewById(R.id.civ_expansion_pl);
        civArmTyp = v.findViewById(R.id.army_type_pl);

        fromWebButton = v.findViewById(R.id.requestButton);
        editTextReq = v.findViewById(R.id.editTextReq);

        fromWebButton.setOnClickListener(this::onClickUpdate);

        // notify on change
        searchViewModel.getCivilizationFromWeb().observe(getViewLifecycleOwner(), civilization -> {

            civName.setText(civilization.getCivilizationName());
            civExp.setText(civilization.getExpansion());
            civArmTyp.setText(civilization.getArmyType());

            searchViewModel.addToOwned(civilization);
        });

        civilizationsFromWebList = v.findViewById(R.id.civilizationFromWebRecyclerView);
        civilizationsFromWebList.setLayoutManager(new LinearLayoutManager(getActivity()));
        civilizationsFromWebList.hasFixedSize();
        civilizationsFromWebAdapter = new CivilizationsFromWebAdapter();
        civilizationsFromWebList.setAdapter(civilizationsFromWebAdapter);
        searchViewModel.getAllCivilizationsFromWeb().observe(getViewLifecycleOwner(), civilizations -> {
            civilizationsFromWebAdapter.setCivilizations(civilizations);
        });

        return v;
    }



    //react to change
    private void onClickUpdate(View view) {
        searchViewModel.updateCivilization(editTextReq.getText().toString());
        if(!(editTextReq.getText().toString().equals("")))
        {
            Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                    "Added from web", Snackbar.LENGTH_SHORT);
            snackbar.show();
        }
    }

}