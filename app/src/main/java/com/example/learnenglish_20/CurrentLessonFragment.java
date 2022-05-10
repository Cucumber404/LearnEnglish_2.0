package com.example.learnenglish_20;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class CurrentLessonFragment extends Fragment {

    private int chapter;
    private int lesson;
    private ImageButton imageButtonToLessons;
    public static List<Word> currentLessonWordsArr = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_lesson, container, false);

        initBackButton(view);

        setCurrentWordsArr();

        initRecyclerView(savedInstanceState, view);

        return view;
    }

    private void setCurrentWordsArr() {
        int currentWord = chapter*100+lesson*10;
        currentLessonWordsArr.clear();
        for (int i=0;i<10;i++){
            currentLessonWordsArr.add(MainActivity.wordsArr.get(currentWord+i));
        }
    }

    CurrentLessonFragment(int chapter, int lesson) {
        this.chapter = chapter;
        this.lesson = lesson;
        Log.d("chapt_less:", String.valueOf(chapter)+"; "+String.valueOf(lesson));

    }

    private void initRecyclerView(Bundle savedInstanceState, View view) {
        super.onCreate(savedInstanceState);
        // начальная инициализация списка
        RecyclerView recyclerView = view.findViewById(R.id.words_recycler_list);
        // создаем адаптер
        WordPairAdapter wordPairAdapter = new WordPairAdapter(getActivity().getApplicationContext(), currentLessonWordsArr);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(wordPairAdapter);
    }

    private void initBackButton(View view) {
        imageButtonToLessons =  view.findViewById(R.id.imageButtonToLessons);
        imageButtonToLessons.setOnClickListener(b->{
            replaceFragment(new LessonsFragment(chapter));
        });
    }


    public void replaceFragment(Fragment fragment){ // Замена фрагмента
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

}