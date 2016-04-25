package com.example.kuznargroup.welowanie;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class QuestionActivity extends Activity {

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

        buttonA.setBackgroundResource(R.drawable.button_game);
        buttonB.setBackgroundResource(R.drawable.button_game);
        buttonC.setBackgroundResource(R.drawable.button_game);
        buttonD.setBackgroundResource(R.drawable.button_game);


    }


    public void onAnswer(View view) {

        buttonA.setBackgroundResource(R.drawable.button_game);
        buttonB.setBackgroundResource(R.drawable.button_game);
        buttonC.setBackgroundResource(R.drawable.button_game);
        buttonD.setBackgroundResource(R.drawable.button_game);


        switch (view.getId()) {
            case R.id.buttonA:
                buttonA.setBackgroundResource(R.drawable.button_game_click);
                break;
            case R.id.buttonB:
                buttonB.setBackgroundResource(R.drawable.button_game_click);
                break;
            case R.id.buttonC:
                buttonC.setBackgroundResource(R.drawable.button_game_click);
                break;
            case R.id.buttonD:
                buttonD.setBackgroundResource(R.drawable.button_game_click);
                break;

        }
    }

}
