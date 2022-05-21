package com.example.learnenglish_20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;

public class StoryActivity extends AppCompatActivity {
    public int count = 0;
    public ImageView storyPic;
    Button storyBt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        storyPic = findViewById(R.id.story_pic);
        View view = getWindow().getDecorView().getRootView();
        storyBt = findViewById(R.id.story_bt);
        setStoryBtListener();
//        DataBase.updateFirstTime();
    }

    private void setStoryBtListener() {
        storyBt.setOnClickListener(b->{
            count++;
            if (count==1){
                storyPic.setImageResource(R.drawable.story_sec);
            }else if (count==2){
                storyPic.setImageResource(R.drawable.story_three);
            }else{
                DataBase.updateFirstTime();
                DataBase.myCurrentUser.setFirstTime(false);
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(Constants.FROM_STORY, true);
                startActivity(intent);
            }
        });
    }
}