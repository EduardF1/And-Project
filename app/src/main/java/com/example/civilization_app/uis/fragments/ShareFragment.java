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

import com.example.civilization_app.R;
import com.example.civilization_app.viewmodels.ShareViewModel;
import com.google.android.material.snackbar.Snackbar;

public class ShareFragment extends Fragment {

    Button fromWebButton;
    EditText editTextReq;
    TextView civName;
    TextView civExp;
    TextView civArmTyp;

    //TODO create adapter and list item for all civs from web and display in fragment

    private static final String TAG = "ShareFragment";

    private ShareViewModel shareViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_share, container, false);
        shareViewModel =
                new ViewModelProvider(this).get(ShareViewModel.class);

        final TextView textView = v.findViewById(R.id.text_favorites);

        Log.d(TAG, "onCreateView called -- Share fragment");

        civName = v.findViewById(R.id.civ_name_pl);
        civExp = v.findViewById(R.id.civ_expansion_pl);
        civArmTyp = v.findViewById(R.id.army_type_pl);

        fromWebButton = v.findViewById(R.id.requestButton);
        editTextReq = v.findViewById(R.id.editTextReq);

        fromWebButton.setOnClickListener(this::onClickUpdate);

        // notify on change
        shareViewModel.getCivilizationFromWeb().observe(getViewLifecycleOwner(), civilizationFromWeb -> {

            civName.setText(civilizationFromWeb.getName());
            civExp.setText(civilizationFromWeb.getExpansion());
            civArmTyp.setText(civilizationFromWeb.getArmy_type());
        });


        return v;
    }

    //react to change
    private void onClickUpdate(View view) {
        shareViewModel.updateCivilization(editTextReq.getText().toString());


        Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                "Added from web", Snackbar.LENGTH_SHORT);
        snackbar.show();


    }

}