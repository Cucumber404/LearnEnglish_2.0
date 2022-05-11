package com.example.learnenglish_20;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;


public class CurrentLessonFragment extends Fragment {

    private int chapter;
    private int lesson;
    private ImageButton imageButtonToLessons;
    public static List<Word> currentLessonWordsArr = new ArrayList<>();
    Button btLearn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_lesson, container, false);

        initBackButton(view);

        setCurrentWordsArr();

        initRecyclerView(savedInstanceState, view);

        initBtLearn(view);

        return view;
    }

    private void initBtLearn(View view) {
        btLearn= view.findViewById(R.id.bt_learn);

        btLearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), LearnActivity.class);
                intent.putExtra("chapter", chapter);
                intent.putExtra("lesson", lesson);
                startActivity(intent);
            }
        });
    }

    private void setCurrentWordsArr() {
        int currentWord = chapter*100+lesson*10;
        currentLessonWordsArr.clear();
        for (int i=0;i<10;i++){
            currentLessonWordsArr.add(DataBase.wordsArr.get(currentWord+i));
        }
    }

    public CurrentLessonFragment(int chapter, int lesson) {
        this.chapter = chapter;
        this.lesson = lesson;
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
        imageButtonToLessons =  view.findViewById(R.id.imageButtonToLesson);
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