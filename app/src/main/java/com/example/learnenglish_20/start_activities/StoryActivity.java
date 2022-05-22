package com.example.learnenglish_20.start_activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learnenglish_20.support.Constants;
import com.example.learnenglish_20.data.DataBase;
import com.example.learnenglish_20.main_activity_fragments.MainActivity;
import com.example.learnenglish_20.R;

public class StoryActivity extends AppCompatActivity {
    public int count = 0;
    public ImageView storyPic;
    Button storyBt;
    private TextView textStory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        storyPic = findViewById(R.id.story_pic);
        View view = getWindow().getDecorView().getRootView();
        storyBt = findViewById(R.id.story_bt);
        setStoryBtListener();
        textStory=findViewById(R.id.text_story);
        textStory.setVisibility(View.INVISIBLE);
    }

    private void setStoryBtListener() {
        storyBt.setOnClickListener(b->{
            count++;
            switch (count) {
                case 1:
                    storyPic.setImageResource(R.drawable.story_sec);
                    break;
                case 2:
                    storyPic.setImageResource(R.drawable.story_three);
                    break;
                case 3:
                    storyPic.setImageResource(R.drawable.poor_state);
                    textStory.setVisibility(View.VISIBLE);
                    textStory.setText(getResources().getString(R.string.inst_one));
                    break;
                case 4:
                    textStory.setText(getResources().getString(R.string.inst_sec));
                    break;
                case 5:
                    textStory.setText(getResources().getString(R.string.inst_third));
                    break;
                default:
                    DataBase.updateFirstTime();
                    DataBase.myCurrentUser.setFirstTime(false);
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra(Constants.FROM_STORY, true);
                    startActivity(intent);
                    break;
            }
        });
    }
}