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

import java.util.ArrayList;

public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ArrayList<String> chaptersArr;
    private RecyclerView modulesRecView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, // Здесь указывается какой дизайн используется для фрагмента
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initChaptersArr(); // Определяем сколько глав у нас будет

        initModulesList(view); // Создаем список глав

        return view;
    }

    private void initChaptersArr() {
        chaptersArr = new ArrayList<>();
        int i=1;
        int size = DataBase.wordsArr.size();
        while (size > 0){
            chaptersArr.add("Глава "+i);
            i++;
            size-=100;
        }
    }

    private void initModulesList(View view) {
        modulesRecView = view.findViewById(R.id.modules_rec_view);
        ChapterAndLessonsAdapter adapter = new ChapterAndLessonsAdapter(getActivity().getApplicationContext(), chaptersArr);
        modulesRecView.setAdapter(adapter);
        modulesRecView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity().getApplicationContext(), modulesRecView,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        replaceFragment(new LessonsFragment(position));
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        replaceFragment(new LessonsFragment(position)); // При нажатии на главу открывается фрагмент с уроками
    }

    public void replaceFragment(Fragment fragment){ // Замена фрагмента
        FragmentManager fragmentManager = getFragmentManager();
        assert fragmentManager != null;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}