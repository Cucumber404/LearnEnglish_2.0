package com.example.learnenglish_20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class LearnActivity extends AppCompatActivity {

    int index, chapter, lesson;
    Button btMemo, btNotMemo;
    ImageButton backToLesson;
    TextView englishWord, russianWord;
    public static List<Word> currentLessonWordsArr = CurrentLessonFragment.currentLessonWordsArr;
    List<Integer> used_values;
    ImageView wordPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        init(); // Инициализация полей

        setMemoBtnListener(); // Устанавливаем слушатели
        setNotMemoBtnListener();
        setCtBackClickListener();

    }

    private void setCtBackClickListener() {
        backToLesson.setOnClickListener(b->{
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("chapter", chapter);
            intent.putExtra("lesson", lesson);
            startActivity(intent);
        });
    }

    private void setMemoBtnListener() {
        btMemo.setOnClickListener(b->{
            used_values.add(index);
            if(used_values.size()<10) {
                setNewWords();
            }else{
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("chapter", chapter);
                intent.putExtra("lesson", lesson);
                Toast.makeText(this,"Поздравляю, вы закончили упражнение!", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
    }

    private void setNotMemoBtnListener(){
        btNotMemo.setOnClickListener(b->{
            setNewWords();
        });
    }

    private void setNewWords() {
        if(used_values.size()!=0) {
            do {
                index = (int) (Math.random() * 10);
            } while (used_values.contains(index));
        }else{
            index = (int) (Math.random() * 10);
        }
        Word word = currentLessonWordsArr.get(index);
        englishWord.setText(word.getEnglish());
        russianWord.setText(word.getRussian());
        Picasso.get().load(word.getPicture()).into(wordPic);
    }

    private void init() {
        getIntentLearn();
        englishWord = findViewById(R.id.english_word_learn);
        russianWord = findViewById(R.id.russian_word_learn);
        used_values = new ArrayList<>();
        btMemo = findViewById(R.id.bt_memo);
        btNotMemo = findViewById(R.id.bt_not_memo);
        backToLesson = findViewById(R.id.imageButtonToLesson);
        wordPic = findViewById(R.id.word_image);
        setNewWords();
    }

    private void getIntentLearn(){
        Intent i = getIntent();
        if (i!=null){
            chapter = i.getIntExtra("chapter", -1);
            lesson = i.getIntExtra("lesson", -1);
        }
    }
}