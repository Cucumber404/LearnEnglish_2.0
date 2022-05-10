package com.example.learnenglish_20;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

public class LessonsFragment extends Fragment implements AdapterView.OnItemClickListener {

    private int chapter;
    private String[] lessonsArr = new String[] {"Урок 1" , "Урок 2", "Урок 3",
            "Урок 4" , "Урок 5", "Урок 6","Урок 7" , "Урок 8", "Урок 9", "Урок 10"};
    private ListView lessonsList;
    ImageButton imageButtontoHome;

    LessonsFragment(int chapter){
        super();
        this.chapter=chapter;
        Log.d("chapter:", String.valueOf(chapter));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, // Здесь указывается какой дизайн используется для фрагмента
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lessons_fragment, container, false);

        createLessonsList(view); // Создаем ListView cо списком уроков

        setBackButton(view); // Кнопка Назад

        return view;
    }

    private void setBackButton(View view) {
        imageButtontoHome =  view.findViewById(R.id.imageButtonToHome);
        imageButtontoHome.setOnClickListener(b->{
            replaceFragment(new HomeFragment());
        });
    }

    private void createLessonsList(View view) {
        lessonsList =  view.findViewById(R.id.lessons_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(),R.layout.modules_lessons_list, R.id.button, lessonsArr);
        lessonsList.setAdapter(adapter);
        lessonsList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        replaceFragment(new CurrentLessonFragment(chapter, position));
    }

    public void replaceFragment(Fragment fragment){ // Замена фрагмента
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}