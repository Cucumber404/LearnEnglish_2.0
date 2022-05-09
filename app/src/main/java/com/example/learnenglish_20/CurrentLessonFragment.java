package com.example.learnenglish_20;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;


public class CurrentLessonFragment extends Fragment {

    private ListView wordsList;
    private ImageButton imageButtonToLessons;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_lesson, container, false);

        imageButtonToLessons =  view.findViewById(R.id.imageButtonToLessons);
        imageButtonToLessons.setOnClickListener(b->{
            replaceFragment(new LessonsFragment());
        });

        ArrayList<WordPair> wordPairs = new ArrayList<WordPair>();
            super.onCreate(savedInstanceState);
            // начальная инициализация списка
            wordPairs.add(new WordPair("Brazil", "Бразилия"));
            wordPairs.add(new WordPair("Argentina", "Аргентина"));
            RecyclerView recyclerView = view.findViewById(R.id.words_recycler_list);
            // создаем адаптер
            WordPairAdapter wordPairAdapter = new WordPairAdapter(getActivity().getApplicationContext(), wordPairs);
            // устанавливаем для списка адаптер
            recyclerView.setAdapter(wordPairAdapter);

        return view;
    }


    public void replaceFragment(Fragment fragment){ // Замена фрагмента
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

}