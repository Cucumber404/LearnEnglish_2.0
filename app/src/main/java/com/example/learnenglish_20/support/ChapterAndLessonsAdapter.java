package com.example.learnenglish_20.support;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnenglish_20.main_activity_fragments.HomeFragment;
import com.example.learnenglish_20.R;

import java.util.ArrayList;

public class ChapterAndLessonsAdapter extends RecyclerView.Adapter<ChapterAndLessonsAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final ArrayList<String> chaptersOrLessons;

    public ChapterAndLessonsAdapter(Context context, ArrayList<String> chaptersOrLessons) {
        this.chaptersOrLessons = chaptersOrLessons;
        this.inflater = LayoutInflater.from(context);
        System.out.println(getItemCount());
    }

    @NonNull
    @Override
    public ChapterAndLessonsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //возвращает объект ViewHolder, который будет хранить данные по одной строчке
        View view = inflater.inflate(R.layout.modules_lessons_list, parent, false);
        //Ставит весь(!) файл modules_lessons_list как 1 элемент
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterAndLessonsAdapter.ViewHolder holder, int position) {
        //выполняет привязку объекта ViewHolder к строке по определенной позиции
        holder.item.setText(chaptersOrLessons.get(position));

        if (chaptersOrLessons.get(0).equals("Глава 1")) {
            if (position + 1 <= HomeFragment.chapterProgress) { // Зеленый если до или равен прогрессу
                holder.item.setBackgroundColor(Color.parseColor("#47BF32"));
            } else if (position + 1 == HomeFragment.chapterProgress + 1) { //Синий если на 1 больше прогресса
                holder.item.setBackgroundColor(Color.parseColor("#46bee6"));
            } else {
                holder.item.setBackgroundColor(Color.parseColor("#c0c0c0"));
            }
        } else {
            if ((position * 10 + 1 + HomeFragment.clickedChapter * 100) <= HomeFragment.lessonsProgress) {
                holder.item.setBackgroundColor(Color.parseColor("#47BF32"));
            } else if ((position * 10 + 1 + HomeFragment.clickedChapter * 100) == HomeFragment.lessonsProgress + 1) {
                holder.item.setBackgroundColor(Color.parseColor("#46bee6"));
            } else {
                holder.item.setBackgroundColor(Color.parseColor("#c0c0c0"));
            }
        }

        holder.item.setOnClickListener(b -> {

        });
    }

    @Override
    public int getItemCount() {
        //возвращает количество объектов в списке
        return chaptersOrLessons.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item_chapters_lessons_list);
        }
    }
}
