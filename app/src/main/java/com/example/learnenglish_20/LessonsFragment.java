package com.example.learnenglish_20;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LessonsFragment extends Fragment implements AdapterView.OnItemClickListener {

    private String[] lessonsArr = new String[] {"Модуль 1" , "Модуль 2", "Модуль 3"};
    private ListView lessonsList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, // Здесь указывается какой дизайн используется для фрагмента
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Inflate the layout for this fragment


        lessonsList =  view.findViewById(R.id.modules_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(),R.layout.modules_lessons_list, R.id.button, lessonsArr);
        lessonsList.setAdapter(adapter);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}