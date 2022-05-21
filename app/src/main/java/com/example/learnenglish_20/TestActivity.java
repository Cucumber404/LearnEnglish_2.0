package com.example.learnenglish_20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private EditText testEditText;
    private TextView testText, testCheck;
    private Button btCheckTest;
    private int mistakes;
    private List<Word> currentLessonWordsArr = CurrentLessonFragment.currentLessonWordsArr;
    private List<Integer> usedIndexes = new ArrayList<>(0);
    private Word currentWordTest;
    private int chapter, lesson;
    private ImageButton imageButtonBackToCurrent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        init();
        setCheckBtListener();
        setBackToCurrBtList();
    }

    private void setBackToCurrBtList() {
        imageButtonBackToCurrent.setOnClickListener(b -> {
            Intent intent = initIntent();
            startActivity(intent);
        });
    }

    private void setCheckBtListener() {
        btCheckTest.setOnClickListener(b -> {
            if (testEditText.getText().toString().equalsIgnoreCase(currentWordTest.getEnglish()) && !(TextUtils.isEmpty(testEditText.getText().toString()))) {
                testEditText.setText("");
                if (usedIndexes.size() < 10) {
                    testCheck.setText("");
                    setNewWord();
                } else {
                    finishTest();
                }
            } else if (!(TextUtils.isEmpty(testEditText.getText().toString()))) {
                updateMistakes();
                threadSleep();
                testCheck.setText("Неверно");
            }
        });
    }

    private void updateMistakes() {
        mistakes++;
        if (mistakes == 3) {
            Intent intent = initIntent();
            Toast.makeText(this, "3 ошибки: попробуйте подучить слова", Toast.LENGTH_LONG).show();
            startActivity(intent);
        }
    }

    private void finishTest() {
        if (chapter * 100 + lesson * 10 == DataBase.progress) {//Условие что пройденные не считаются
            DataBase.progress += 10;
            DataBase.updateProgress();
            if (DataBase.progress % 100 == 0) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(Constants.NEW_LEVEL_KEY, true);
                startActivity(intent);
                return;
            }
        }
        Toast.makeText(this, "Поздравляю, вы прошли тест!", Toast.LENGTH_LONG).show();
        Intent intent = initIntent();
        startActivity(intent);
    }

    @NonNull
    private Intent initIntent() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Constants.CHAPTER_KEY, chapter);
        intent.putExtra(Constants.LESSON_KEY, lesson);
        return intent;
    }

    private void threadSleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void setNewWord() {
        int index;
        if (usedIndexes.size() != 0) {
            do {
                index = (int) (Math.random() * 10);
            } while (usedIndexes.contains(index));
        } else {
            index = (int) (Math.random() * 10);
        }
        currentWordTest = currentLessonWordsArr.get(index);
        testText.setText(currentWordTest.getRussian());
        usedIndexes.add(index);
    }

    private void init() {
        testCheck = findViewById(R.id.test_text_check);
        imageButtonBackToCurrent = findViewById(R.id.imageButtonToCurrent);
        btCheckTest = findViewById(R.id.test_check_bt);
        testEditText = findViewById(R.id.test_edit_text);
        testText = findViewById(R.id.test_text);
        usedIndexes.clear();
        mistakes = 0;
        setNewWord();
        getIntentLearn();
    }

    private void getIntentLearn() {
        Intent i = getIntent();
        if (i != null) {
            chapter = i.getIntExtra(Constants.CHAPTER_KEY, -1);
            lesson = i.getIntExtra(Constants.LESSON_KEY, -1);
        }
    }

}