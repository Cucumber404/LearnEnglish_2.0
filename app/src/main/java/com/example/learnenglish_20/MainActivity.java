package com.example.learnenglish_20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnenglish_20.databinding.ActivityMainBinding;
import com.example.learnenglish_20.modal.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding; // В binding будут все элементы дизайна у которых есть id
    private boolean newLevel;
    public static boolean fromStory;
    public ProgressBar progressBarMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater()); //Создаем объект класса ResultProfileBinding – в binding все id
        setContentView(binding.getRoot()); // Корневая id – Activity main

        init();
        getFirstTimeFromDB();

        replaceFragment(new ProfileFragment());

        getIntentMain();

        if (newLevel) {
            Toast.makeText(this, "Поздравляю с повышением!", Toast.LENGTH_LONG).show();
        }

        setBottomNavListener();

    }

    public void getFirstTimeFromDB() {
        ValueEventListener vListener = new ValueEventListener() {
            User tempUser;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    tempUser = ds.getValue(User.class);
                    assert tempUser != null;
                    if (tempUser.geteMail().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())) {
                        break;
                    }
                }
                //Когда код доходит до сюда, вся информация уже загружена, то
                //есть можно отследить момент загрузки (важно!!!)
                if(tempUser.isFirstTime()) {
                    startStory();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        DataBase.mUserDataBase.addValueEventListener(vListener);
    }

    public void startStory() {
        Intent intent = new Intent(this, StoryActivity.class);
        startActivity(intent);
    }

    private void setBottomNavListener() {
        binding.bottomNavigationView.setOnItemSelectedListener(item -> { // Слушатель нажатия на  bottomNavigationView

            switch (item.getItemId()) {
                case Constants.FRAGMENT_HOME_ID:
                    replaceFragment(new HomeFragment());
                    break;
                case Constants.FRAGMENT_PROFILE_ID:
                    replaceFragment(new ProfileFragment());
                    break;
                case Constants.FRAGMENT_SETTINGS_ID:
                    replaceFragment(new SettingsFragment());
                    break;
            }
            return true;
        });
    }

    private void init() {

    }

    public void replaceFragment(Fragment fragment) { // Замена фрагмента
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    private void getIntentMain() {
        Intent i = getIntent();
        if (i != null) {
            newLevel = i.getBooleanExtra(Constants.NEW_LEVEL_KEY, false);
            if (newLevel) {
                return;
            }
            int chapter = i.getIntExtra(Constants.CHAPTER_KEY, -1);
            int lesson = i.getIntExtra(Constants.LESSON_KEY, -1);
            if (chapter != -1) {
                replaceFragment(new CurrentLessonFragment(chapter, lesson));
            }
            fromStory = i.getBooleanExtra(Constants.FROM_STORY, false);
        }
    }

    public void signOut(View view) {
        Toast.makeText(this, "Вы вышли из аккаунта " + FirebaseAuth.getInstance().getCurrentUser().getEmail(), Toast.LENGTH_LONG).show();
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, LoginActivity.class));
    }
}

//TODO:
//  обучение