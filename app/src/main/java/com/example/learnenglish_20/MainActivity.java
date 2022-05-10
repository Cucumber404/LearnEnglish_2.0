package com.example.learnenglish_20;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.example.learnenglish_20.databinding.ActivityMainBinding;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding; // В binding будут все элементы дизайна у которых есть id
    private DatabaseReference mDataBase;
    private String WORD_KEY = "Word";
    public static List<Word> wordsArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater()); //Создаем объект класса ResultProfileBinding – в binding все id
        setContentView(binding.getRoot()); // Корневая id – Activity main

        init();

        replaceFragment(new ProfileFragment()); // Чтобы при открытии приложения показывался HomeFragment

//        pushInDB(); // Если нужно заполнить базу данными

        getDataFromDB();

        setBottomNavListener();

    }

    private void setBottomNavListener() {
        binding.bottomNavigationView.setOnItemSelectedListener(item -> { // Слушатель нажатия на  bottomNavigationView

            switch (item.getItemId()){
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.profile:
                    replaceFragment(new ProfileFragment());
                    break;
                case R.id.settings:
                    replaceFragment(new SettingsFragment());
                    break;
            }
            return true;
        });
    }

    private void getDataFromDB(){
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (wordsArr.size()>0) wordsArr.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Word word = ds.getValue(Word.class); // Получаем Word из БД
                    assert word != null; // Проверяем что word не null
                    wordsArr.add(word);
                    System.out.println(word.toString());
                }
                //adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mDataBase.addValueEventListener(vListener);
    }


    private void init() {
        mDataBase = FirebaseDatabase.getInstance().getReference(WORD_KEY);
        wordsArr = new ArrayList<>();
    }

    private void pushInDB() { // При необходимости
        String id = mDataBase.getKey();
        for (int i=0;i<100;i++){
            String eng = englishWordsArr[i];
            String rus = russianWordsArr[i];
            Word word = new Word(id, eng, rus, String.valueOf(i));
            mDataBase.push().setValue(word);
        }
    }


    public void replaceFragment(Fragment fragment){ // Замена фрагмента
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

    public static String[] englishWordsArr = new String[] {"abandon", "abate", "abduct", "abide", "able", "abnormal", "aboard",
            "abolish", "abort", "abound", "about", "abroad", "abrupt", "absent", "absorb", "abundant", "abuse", "abuse", "abuse",
            "accelerate", "accept", "access", "accessory", "accident", "accident", "acclaim", "accommodate", "accompany",
            "accomplish", "accord", "according to", "account", "account", "account for", "accurate", "accuse", "accustom",
            "ache", "achieve", "acid", "acknowledge", "acquaint", "acquire", "acquit", "across", "act", "act on", "acute",
            "adamant", "add to", "add up", "addict", "addition", "adhere", "adjacent", "adjective", "adjust", "admire", "admit",
            "adolescent", "adopt", "adopt", "adore", "adorn", "adult", "adultery", "advance", "advantage", "adventure", "adverb",
            "adversary", "adverse", "advertise", "advice", "advise", "advocate", "aerial", "aerial", "affair", "affair", "affect",
            "affection", "affiliate", "affirm", "afflict", "affluent", "afford", "afraid", "after all", "aftermath", "age", "age",
            "agenda", "aggravate", "aggregate", "agile", "agitate", "agree", "agreement", "agriculture"};
    public static String[] russianWordsArr = new String[] {"оставить", "уменьшаться", "похищать", "смириться", "способный",
            "ненормальный", "на борту", "упразднять", "прерывать", "изобиловать", "примерно", "за границу", "крутой",
            "отсутствующий", "поглощать", "обильный", "оскорблять", "злоупотреблять", "насилие", "ускорять", "принять",
            "доступ", "аксессуар", "случайность", "происшествие", "приветствовать", "размещать", "сопровождать", "выполнить",
            "согласие", "в соответствии", "счёт", "отчёт", "объяснить", "точный", "обвинять", "приучить", "боль, болеть",
            "достигать", "кислота", "признавать", "знакомить", "обретать", "оправдать", "за, через", "действовать",
            "действовать в соответствии", "острый", "непреклонный", "увеличивать", "сходиться", "наркоман", "добавление",
            "придерживаться", "смежный", "прилагательное", "настраивать", "восхищаться", "признавать", "подросток", "принимать",
            "усыновить", "обожать", "украшать", "взрослый", "прелюбодеяние", "продвигаться", "преимущество", "приключение",
            "наречие", "противник", "неблагоприятный", "рекламировать", "совет", "советовать", "защищать", "воздушный", "антенна",
            "дело", "роман", "воздействовать", "привязанность", "филиал", "утверждать", "поражать,", "обеспеченный", "позволить себе",
            "напуганный", "всё-таки", "последствие", "век", "возраст", "повестка дня", "обострять", "совокупный", "проворный",
            "волновать", "соглашаться", "соглашение", "сельское хозяйство"};

}