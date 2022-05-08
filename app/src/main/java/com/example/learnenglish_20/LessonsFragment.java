package com.example.learnenglish_20;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

public class LessonsFragment extends Fragment implements AdapterView.OnItemClickListener {

    private String[] lessonsArr = new String[] {"Урок 1" , "Урок 2", "Урок 3"};
    private ListView lessonsList;
    ImageButton imageButtontoHome;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, // Здесь указывается какой дизайн используется для фрагмента
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lessons_fragment, container, false);

        // Inflate the layout for this fragment

        lessonsList =  view.findViewById(R.id.lessons_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(),R.layout.modules_lessons_list, R.id.button, lessonsArr);
        lessonsList.setAdapter(adapter);

        imageButtontoHome =  view.findViewById(R.id.imageButtonToHome);
        imageButtontoHome.setOnClickListener(b->{
            replaceFragment(new HomeFragment());
        });

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        replaceFragment(new CurrentLessonFragment());
    }

    public void replaceFragment(Fragment fragment){ // Замена фрагмента
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}