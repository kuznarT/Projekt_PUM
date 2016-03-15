package com.example.kuznargroup.welowanie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ardian.menuexample.R;

public class QuestionActivity extends AppCompatActivity {

    int position = 0;
    Button buttonA;
    Button buttonB;
    Button buttonC;
    Button buttonD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        buttonA = (Button) findViewById(R.id.buttonA);
        buttonB = (Button) findViewById(R.id.buttonB);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonD = (Button) findViewById(R.id.buttonD);

        buttonA.setBackgroundResource(R.drawable.button);
        buttonB.setBackgroundResource(R.drawable.button);
        buttonC.setBackgroundResource(R.drawable.button);
        buttonD.setBackgroundResource(R.drawable.button);


        //Wybór poziomu od 0 do 7, czyli od Semestru I do poziomu Bonusowego
        Intent intent = getIntent();
        position = intent.getExtras().getInt("Position");

        //Tutaj przydałby się serwer
        if (position == 0) {
            Toast.makeText(QuestionActivity.this, "Pytania z semestru I", Toast.LENGTH_LONG).show();
        } else if (position == 1) {
            Toast.makeText(QuestionActivity.this, "Pytania z semestru II", Toast.LENGTH_LONG).show();
        }


    }


    public void onAnswer(View view) {

        buttonA.setBackgroundResource(R.drawable.button);
        buttonB.setBackgroundResource(R.drawable.button);
        buttonC.setBackgroundResource(R.drawable.button);
        buttonD.setBackgroundResource(R.drawable.button);


        switch (view.getId()) {
            case R.id.buttonA:
                buttonA.setBackgroundResource(R.drawable.button_click);
                break;
            case R.id.buttonB:
                buttonB.setBackgroundResource(R.drawable.button_click);
                break;
            case R.id.buttonC:
                buttonC.setBackgroundResource(R.drawable.button_click);
                break;
            case R.id.buttonD:
                buttonD.setBackgroundResource(R.drawable.button_click);
                break;

        }
    }

}
