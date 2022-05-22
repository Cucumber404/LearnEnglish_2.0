package com.example.learnenglish_20.main_activity_fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.learnenglish_20.R;
import com.google.firebase.auth.FirebaseAuth;

public class SettingsFragment extends Fragment {

    private TextView currentAccountEmail;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        currentAccountEmail = view.findViewById(R.id.current_account);
        currentAccountEmail.setText("Текущий аккаунт: \n"+FirebaseAuth.getInstance().getCurrentUser().getEmail());
        return view;
    }
}