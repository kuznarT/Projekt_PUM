package com.example.kuznargroup.welowanie;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.ardian.menuexample.R;

public class QuestionActivity extends AppCompatActivity {

    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        //Wybór poziomu od 0 do 7, czyli od Semestru I do poziomu Bonusowego
        Intent intent = getIntent();
        position = intent.getExtras().getInt("Position");

        //Tutaj przydałby się serwer
        if (position == 0){
            Toast.makeText(QuestionActivity.this, "Cos", Toast.LENGTH_SHORT).show();
        }
        else if (position == 1){
            Toast.makeText(QuestionActivity.this, "Cos", Toast.LENGTH_SHORT).show();
        }




    }

}

