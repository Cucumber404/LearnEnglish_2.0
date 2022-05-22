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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ArrayList<String> chaptersArr;
    private RecyclerView modulesRecView;
    public static int chapterProgress, lessonsProgress, entireProgress;
    public static int clickedChapter;
    private Button btFromStory;
    private TextView textFromStory;
    public static ProgressBar progressBarMain;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, // Здесь указывается какой дизайн используется для фрагмента
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initChaptersArr(); // Определяем сколько глав у нас будет

        progressBarMain=view.findViewById(R.id.progress_bar_home);

        initProgress();

        initModulesList(view); // Создаем список глав
        btFromStory = view.findViewById(R.id.bt_instr_main);
        textFromStory = view.findViewById(R.id.text_instr_main);
        btFromStory.setVisibility(View.INVISIBLE);
        textFromStory.setVisibility(View.INVISIBLE);
        if(MainActivity.fromStory) {
            textFromStory.setVisibility(View.VISIBLE);
            btFromStory.setVisibility(View.VISIBLE);
            btFromStory.setOnClickListener(b -> {
                btFromStory.setVisibility(View.INVISIBLE);
                textFromStory.setVisibility(View.INVISIBLE);
            });
            MainActivity.fromStory=false;
        }
        return view;
    }

    private void initProgress(){
        chapterProgress=0;
        entireProgress=DataBase.progress;
        lessonsProgress=entireProgress;
        while(entireProgress>99){
            chapterProgress+=1;
            entireProgress-=100;
        }
    }

    private void initChaptersArr() {
        chaptersArr = new ArrayList<>();
        int i = 1;
        int size = DataBase.wordsArr.size();
        while (size > 0) {
            chaptersArr.add("Глава " + i);
            i++;
            size -= 100;
        }
    }

    private void initModulesList(View view) {
        modulesRecView = view.findViewById(R.id.modules_rec_view);
        ChapterAndLessonsAdapter adapter = new ChapterAndLessonsAdapter(getActivity().getApplicationContext(), chaptersArr);
        modulesRecView.setAdapter(adapter);
        modulesRecView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity().getApplicationContext(), modulesRecView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if ((position + 1 <= chapterProgress + 1)) {
                            clickedChapter = position;
                            replaceFragment(new LessonsFragment(position));
                        } else {
                            Toast.makeText(getActivity().getApplicationContext(), "Эта глава еще не открыта!", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }

    public void replaceFragment(Fragment fragment) { // Замена фрагмента
        FragmentManager fragmentManager = getFragmentManager();
        assert fragmentManager != null;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}