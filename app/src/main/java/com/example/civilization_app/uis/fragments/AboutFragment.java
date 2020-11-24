package com.example.civilization_app.uis.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.civilization_app.R;
import com.example.civilization_app.viewmodels.AboutViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AboutFragment extends Fragment {

FloatingActionButton floatingActionButton;

    private AboutViewModel aboutViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_about, container, false);

        aboutViewModel =
                new ViewModelProvider(this).get(AboutViewModel.class);

        floatingActionButton = v.findViewById(R.id.info_fab);
       floatingActionButton.setOnClickListener(this:: onClickInfo);
        return v;
    }

    private void onClickInfo(View view) {
        Uri uri = Uri.parse("https://en.wikipedia.org/wiki/Age_of_Empires_II");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }


}