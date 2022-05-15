package com.example.learnenglish_20;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WordPairAdapter extends RecyclerView.Adapter<WordPairAdapter.ViewHolder>{

    //Адаптер для RecyclerView в уроке

    private final LayoutInflater inflater;
    private final List<Word> wordPairs;

    WordPairAdapter(Context context, List<Word> wordPairs) {
        this.wordPairs = wordPairs;
        this.inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public WordPairAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.words_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WordPairAdapter.ViewHolder holder, int position) {
        Word word = wordPairs.get(position);
        holder.russianWord.setText(word.getRussian());
        holder.englishWord.setText(word.getEnglish());
    }

    @Override
    public int getItemCount() {
        return wordPairs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView russianWord, englishWord;
        ViewHolder(View view){
            super(view);
            russianWord = view.findViewById(R.id.russian_word_recyc);
            englishWord = view.findViewById(R.id.english_word_recyc);
        }
    }
}