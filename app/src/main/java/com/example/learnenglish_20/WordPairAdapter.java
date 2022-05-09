package com.example.learnenglish_20;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WordPairAdapter extends RecyclerView.Adapter<WordPairAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<WordPair> WordPairs;

    WordPairAdapter(Context context, List<WordPair> WordPairs) {
        this.WordPairs = WordPairs;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public WordPairAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.words_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WordPairAdapter.ViewHolder holder, int position) {
        WordPair WordPair = WordPairs.get(position);
        holder.russianWord.setText(WordPair.getRussianWord());
        holder.englishWord.setText(WordPair.getEnglishWord());
    }

    @Override
    public int getItemCount() {
        return WordPairs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView russianWord, englishWord;
        ViewHolder(View view){
            super(view);
            russianWord = view.findViewById(R.id.russian_word);
            englishWord = view.findViewById(R.id.english_word);
//            russianWord.setTypeface(Typeface.createFromAsset(itemView.getContext().getAssets(),
//                    "preloaded_fonts.xml/chewy"));
//            englishWord.setTypeface(Typeface.createFromAsset(itemView.getContext().getAssets(),
//                    "preloaded_fonts.xml/chewy"));
        }
    }
}