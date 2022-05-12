package com.example.learnenglish_20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CardsActivity extends AppCompatActivity {

    int index, chapter, lesson;
    Button btMemo, btNotMemo, btShow;
    ImageButton backToLesson;
    TextView topWord, bottomWord;
    public static List<Word> currentLessonWordsArr = CurrentLessonFragment.currentLessonWordsArr;
    List<Integer> used_values;
    ImageView wordPic;
    boolean fromEnToRu;
    Word currentWord;
    int previousIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        init(); // Инициализация полей

        setMemoBtnListener(); // Устанавливаем слушатели
        setNotMemoBtnListener();
        setBtBackClickListener();
        setBtShowClickListener();
    }

    private void setBtShowClickListener() {
        btShow.setOnClickListener(b->{
            if(fromEnToRu){
                bottomWord.setText(currentWord.getRussian());
            }else{
                bottomWord.setText(currentWord.getEnglish());
            }
            btShow.setVisibility(View.INVISIBLE);
            btMemo.setVisibility(View.VISIBLE);
            btNotMemo.setVisibility(View.VISIBLE);
        });
    }

    private void setBtBackClickListener() {
        backToLesson.setOnClickListener(b->{
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(Constants.CHAPTER_KEY, chapter);
            intent.putExtra(Constants.LESSON_KEY, lesson);
            startActivity(intent);
        });
    }

    private void setMemoBtnListener() {
        btMemo.setOnClickListener(b->{
            used_values.add(index);
            if(used_values.size()<10) {
                setTopWord();
                btMemo.setVisibility(View.INVISIBLE);
                btNotMemo.setVisibility(View.INVISIBLE);
                btShow.setVisibility(View.VISIBLE);
            }else{
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(Constants.CHAPTER_KEY, chapter);
                intent.putExtra(Constants.LESSON_KEY, lesson);
                Toast.makeText(this, R.string.finish_exersize, Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
    }

    private void setNotMemoBtnListener(){
        btNotMemo.setOnClickListener(b->{
            setTopWord();
            btMemo.setVisibility(View.INVISIBLE);
            btNotMemo.setVisibility(View.INVISIBLE);
            btShow.setVisibility(View.VISIBLE);
        });
    }

    private void setTopWord() {
        if(used_values.size()!=0) {
            do {
                index = (int) (Math.random() * 10);
            } while (used_values.contains(index));
        }else{
            index = (int) (Math.random() * 10);
        }
        Word word = currentLessonWordsArr.get(index);
        currentWord = word;
        if (fromEnToRu) {
            topWord.setText(word.getEnglish());
        }else{
            topWord.setText(word.getRussian());
        }
        Picasso.get().load(word.getPicture()).into(wordPic);
        bottomWord.setText("");
    }

    private void init() {
        getIntentCards();
        topWord = findViewById(R.id.top_word_card);
        bottomWord = findViewById(R.id.bottom_word_card);
        used_values = new ArrayList<>();
        btMemo = findViewById(R.id.bt_cards_memo);
        btNotMemo = findViewById(R.id.bt_cards_not_memo);
        backToLesson = findViewById(R.id.imageButtonToLesson);
        btShow = findViewById(R.id.bt_cards_show);
        wordPic = findViewById(R.id.en_ru_cards_image);
        btMemo.setVisibility(View.INVISIBLE);
        btNotMemo.setVisibility(View.INVISIBLE);
        setTopWord();
    }

    private void getIntentCards(){
        Intent i = getIntent();
        if (i!=null){
            chapter = i.getIntExtra(Constants.CHAPTER_KEY, -1);
            lesson = i.getIntExtra(Constants.LESSON_KEY, -1);
            fromEnToRu = (i.getStringExtra(Constants.ORDER_KEY).equals(Constants.ORDER_EN_RU));
            System.out.println((Constants.ORDER_KEY+" "+ i.getStringExtra(Constants.ORDER_KEY)));
        }
    }
}