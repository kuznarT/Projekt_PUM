package com.example.kuznargroup.welowanie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class QuestionActivity extends Activity {

    int position = 0;
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


        //Wybór poziomu od 0 do 7, czyli od Semestru I do poziomu Bonusowego
        //TODO:Wykomentowałem to bo wywala null object reference.
        //Intent intent = getIntent();
        //position = intent.getExtras().getInt("Position");
        //position++; //Impossible to use this as id if it gets value 0 (this is simple but stupid solution)



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
