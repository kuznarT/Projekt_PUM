package com.example.kuznargroup.welowanie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.*;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.widget.Button;


public class SelectStageActivity extends Activity {

    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_stage);
        viewPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new customAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        button = (Button) findViewById(R.id.startGameButton);

    }

    public void onQuestionsStart(View view) {
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }
}

