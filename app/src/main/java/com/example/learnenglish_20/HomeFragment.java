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
import android.widget.ListView;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ArrayList<String> chaptersArr;
    private ListView modulesList;

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
        int size = MainActivity.wordsArr.size();
        while (size > 0){
            chaptersArr.add("Глава "+i);
            i++;
            size-=100;
        }
    }

    private void initModulesList(View view) {
        modulesList =  view.findViewById(R.id.modules_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(),R.layout.modules_lessons_list, R.id.button, chaptersArr);
        modulesList.setAdapter(adapter);
        modulesList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        replaceFragment(new LessonsFragment(position)); // При нажатии на главу открывается фрагмент с уроками
    }

    public void replaceFragment(Fragment fragment){ // Замена фрагмента
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}