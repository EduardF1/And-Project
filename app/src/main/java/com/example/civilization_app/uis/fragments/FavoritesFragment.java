package com.example.civilization_app.uis.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.civilization_app.LogInActivity;
import com.example.civilization_app.R;
import com.example.civilization_app.viewmodels.FavoritesViewModel;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class FavoritesFragment extends Fragment {

    Button logOutButton;

    private FavoritesViewModel favoritesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favoritesViewModel =
                new ViewModelProvider(this).get(FavoritesViewModel.class);
        View v = inflater.inflate(R.layout.fragment_favorites, container, false);


       logOutButton = v.findViewById(R.id.logOutButton);
        logOutButton.setOnClickListener(this::signOut);

        return v;
    }


    public void signOut(View v) {
        AuthUI.getInstance()
                .signOut(getActivity())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        startLoginActivity();
                    }
                });
    }

    private void startLoginActivity() {
        startActivity(new Intent(getActivity(), LogInActivity.class));
    }
}