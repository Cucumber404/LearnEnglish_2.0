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
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class LessonsFragment extends Fragment {

    private int chapter;
    private ArrayList<String> lessonsArr;
    private RecyclerView lessonsRecView;
    ImageButton imageButtonToHome;

    LessonsFragment(int chapter) {
        super();
        this.chapter = chapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, // Здесь указывается какой дизайн используется для фрагмента
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lessons, container, false);

        createLessonsList(view); // Создаем ListView cо списком уроков

        setBackButton(view); // Кнопка Назад

        return view;
    }

    private void setBackButton(View view) {
        imageButtonToHome = view.findViewById(R.id.imageButtonToHome);
        imageButtonToHome.setOnClickListener(b -> {
            replaceFragment(new HomeFragment());
        });
    }

    private void createLessonsList(View view) {
        lessonsArr = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            lessonsArr.add("Урок " + i);
        }
        System.out.println(lessonsArr.size());
        lessonsRecView = view.findViewById(R.id.lessons_rec_view);
        ChapterAndLessonsAdapter adapter = new ChapterAndLessonsAdapter(getActivity().getApplicationContext(), lessonsArr);
        lessonsRecView.setAdapter(adapter);
        lessonsRecView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity().getApplicationContext(), lessonsRecView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if ((position * 10 + 1 + HomeFragment.clickedChapter * 100 <= HomeFragment.lessonsProgress + 1))
                            replaceFragment(new CurrentLessonFragment(chapter, position));
                        else
                            Toast.makeText(getActivity().getApplicationContext(), "Этот урок еще не открыт!", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                    }
                })
        );
        ;
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        replaceFragment(new CurrentLessonFragment(chapter, position));
//    }

    public void replaceFragment(Fragment fragment) { // Замена фрагмента
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}