package com.example.learnenglish_20.main_activity_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.learnenglish_20.data.DataBase;
import com.example.learnenglish_20.R;

public class ProfileFragment extends Fragment {

    public static ImageView currentStatePic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        currentStatePic = view.findViewById(R.id.current_progress_image);
        setPicture();
        return view;
    }

    public static void setPicture() {
        if (DataBase.progress < 100) {
            currentStatePic.setImageResource(R.drawable.poor_state);
        } else if (DataBase.progress < 200) {
            currentStatePic.setImageResource(R.drawable.medium_state);
        } else if (DataBase.progress < 300) {
            currentStatePic.setImageResource(R.drawable.rich_state);
        }
    }
}