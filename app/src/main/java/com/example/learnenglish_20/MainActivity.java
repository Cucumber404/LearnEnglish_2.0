package com.example.learnenglish_20;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.learnenglish_20.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
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
    private boolean firstTimeLaunching;
    boolean launch_without_sign;
    public static int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater()); //Создаем объект класса ResultProfileBinding – в binding все id
        setContentView(binding.getRoot()); // Корневая id – Activity main

        init();

        replaceFragment(new ProfileFragment()); // Чтобы при открытии приложения показывался HomeFragment

        setBottomNavListener();

        getIntentMain();

        launchWithoutSignToast();


    }

    private void launchWithoutSignToast() {
        if(launch_without_sign){
            Toast.makeText(this,"Вы вошли как: "+LoginActivity.currentUser.getEmail(),Toast.LENGTH_LONG).show();
        }
    }

    private void setBottomNavListener() {
        binding.bottomNavigationView.setOnItemSelectedListener(item -> { // Слушатель нажатия на  bottomNavigationView

            switch (item.getItemId()){
                case Constants.FRAMENT_HOME_ID:
                    replaceFragment(new HomeFragment());
                    break;
                case Constants.FRAMENT_PROFILE_ID:
                    replaceFragment(new ProfileFragment());
                    break;
                case Constants.FRAMENT_SETTINGS_ID:
                    replaceFragment(new SettingsFragment());
                    break;
            }
            return true;
        });
    }

    private void init() {
    }

    public void replaceFragment(Fragment fragment){ // Замена фрагмента
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

    private void getIntentMain(){
        Intent i = getIntent();
        if (i!=null) {
            int chapter = i.getIntExtra(Constants.CHAPTER_KEY, -1);
            int lesson = i.getIntExtra(Constants.LESSON_KEY, -1);
            if (chapter!=-1) {
                replaceFragment(new CurrentLessonFragment(chapter, lesson));
            }
            launch_without_sign = i.getBooleanExtra(Constants.LAUNCHED_WITHOUT_SIGN, false);
        }
    }

    public void signOut(View view){
        Toast.makeText(this,"Вы вышли из аккаунта "+FirebaseAuth.getInstance().getCurrentUser().getEmail(), Toast.LENGTH_LONG).show();
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, LoginActivity.class));
    }
}