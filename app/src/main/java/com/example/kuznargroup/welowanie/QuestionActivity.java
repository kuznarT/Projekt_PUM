package com.example.kuznargroup.welowanie;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class QuestionActivity extends Activity {

    Button buttonA;
    Button buttonB;
    Button buttonC;
    Button buttonD;
    TextView textView_pyt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        buttonA = (Button) findViewById(R.id.buttonA);
        buttonB = (Button) findViewById(R.id.buttonB);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonD = (Button) findViewById(R.id.buttonD);

        textView_pyt = (TextView) findViewById(R.id.textView_pytanie);


        buttonA.setBackgroundResource(R.drawable.button_game);
        buttonB.setBackgroundResource(R.drawable.button_game);
        buttonC.setBackgroundResource(R.drawable.button_game);
        buttonD.setBackgroundResource(R.drawable.button_game);


        QuestionService.Factory.getIstance().getPytania().enqueue(new Callback<Pytania>() {
            @Override
            public void onResponse(Call<Pytania> call, Response<Pytania> response) {
                textView_pyt.setText(response.body().getQuestion().getQuestion());
                buttonA.setText(response.body().getQuestion().getAnswer1());
                buttonB.setText(response.body().getQuestion().getAnswer2());
                buttonC.setText(response.body().getQuestion().getAnswer3());
                buttonD.setText(response.body().getQuestion().getAnswer4());
            }

            @Override
            public void onFailure(Call<Pytania> call, Throwable t) {

            }
        });

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
