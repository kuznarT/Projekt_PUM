package com.example.kuznargroup.welowanie;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class QuestionActivity extends Activity {
    private static final String TAG = "suemar";
    int position = 0;
    Button buttonA;
    Button buttonB;
    Button buttonC;
    Button buttonD;
    TextView textView;
    TextView count;
    Retrofit retrofit;
    QuestionService questionService;
    Call<pytania> QACall;
    pytania questionsAnswers;
    int licz = 0, punkty = 0;
    String id;
    int resultTime=0, resultTime1=0, resultTime2=0,resultTime1ASCII=0,resultTime2ASCII=0;
    CountDownTimer ct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        buttonA = (Button) findViewById(R.id.buttonA);
        buttonB = (Button) findViewById(R.id.buttonB);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonD = (Button) findViewById(R.id.buttonD);
        textView = (TextView) findViewById(R.id.textView_pytanie);
        count = (TextView) findViewById(R.id.countText);

        buttonA.setBackgroundResource(R.drawable.button_game);
        buttonB.setBackgroundResource(R.drawable.button_game);
        buttonC.setBackgroundResource(R.drawable.button_game);
        buttonD.setBackgroundResource(R.drawable.button_game);

        ct =  new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {


                String v = String.format("%02d", millisUntilFinished/60000);
                int va = (int)( (millisUntilFinished%60000)/1000);
                count.setText(String.format("%02d", va));
                Globals.setCzas(va);
            }

            public void onFinish() {
                count.setText("0");
                koniec();
            }
        };
        ct.start();


         Intent intent = getIntent();
         position = intent.getExtras().getInt("position");
         position++;
         id = Integer.toString(position);

        //Retrofit magic part
        retrofit = new Retrofit.Builder()
                .baseUrl("http://46.101.128.24/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        questionService = retrofit.create(QuestionService.class);

        QACall = questionService.getQuestionsAnswers(id);

        QACall.enqueue(new Callback<pytania>() {
            @Override
            public void onResponse(Call<pytania> call, Response<pytania> response) {
                if (response.isSuccessful()) {
                    questionsAnswers = response.body();

                    // textView.setText(Integer.toString(questionsAnswers.success));
                    giveQuestions();


                    for (Questions c : questionsAnswers.Questions) {
                        Log.i(TAG, String.format("%s: %s", c.question, c.answer1));


                        Log.i(TAG, "---------");
                    }


                } else {
                    Toast.makeText(QuestionActivity.this, "LOL2", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<pytania> call, Throwable t) {
                Log.d("Coś się zepsuło", t.getMessage());
                Toast.makeText(QuestionActivity.this, "LOL", Toast.LENGTH_SHORT).show();
            }
        });


    }


    public void onAnswer(View view) {
        licz++;

        buttonA.setBackgroundResource(R.drawable.button_game);
        buttonB.setBackgroundResource(R.drawable.button_game);
        buttonC.setBackgroundResource(R.drawable.button_game);
        buttonD.setBackgroundResource(R.drawable.button_game);


        switch (view.getId()) {
            case R.id.buttonA:
                buttonA.setBackgroundResource(R.drawable.button_game_click);

                if (buttonA.getText() == questionsAnswers.Questions.get(licz - 1).answer1) {
                    Toascik_dobra();
                    punkty++;
                } else {
                    Toascik_zla();
                }
                break;

            case R.id.buttonB:
                buttonB.setBackgroundResource(R.drawable.button_game_click);
                if (buttonB.getText() == questionsAnswers.Questions.get(licz - 1).answer1) {
                    Toascik_dobra();
                    punkty++;
                } else {
                    Toascik_zla();
                }
                break;
            case R.id.buttonC:
                buttonC.setBackgroundResource(R.drawable.button_game_click);
                if (buttonC.getText() == questionsAnswers.Questions.get(licz - 1).answer1) {
                    Toascik_dobra();
                    punkty++;
                } else {
                    Toascik_zla();
                }
                break;
            case R.id.buttonD:
                buttonD.setBackgroundResource(R.drawable.button_game_click);

                if (buttonD.getText() == questionsAnswers.Questions.get(licz - 1).answer1) {
                    Toascik_dobra();
                    punkty++;
                } else {
                    Toascik_zla();
                }
                break;


        }
        if (licz == 5) {
            koniec();

        } else {
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    buttonA.setBackgroundResource(R.drawable.button_game);
                    buttonB.setBackgroundResource(R.drawable.button_game);
                    buttonC.setBackgroundResource(R.drawable.button_game);
                    buttonD.setBackgroundResource(R.drawable.button_game);
                    giveQuestions();
                }
            };

            Handler h = new Handler();
            h.postDelayed(r, 300);

        }

    }

    private void koniec() {
        ct.cancel();
        String resultTimeString = ct.toString();
        resultTime1ASCII = resultTimeString.charAt(resultTimeString.length() - 2);
        resultTime2ASCII = resultTimeString.charAt(resultTimeString.length()-1);
        resultTime1 = resultTime1ASCII - 48;
        resultTime2 = resultTime2ASCII - 48;
        resultTime = resultTime1 + resultTime2;
        //punkty = punkty * resultTime;
        if(punkty > 2) {
            punkty = punkty * Globals.getCzas(); // mnożenie punktów przez czas który pozostał do końca rundy
            Globals.setScore(punkty);
        }else{
            punkty = punkty * (Globals.getCzas()/2);
            Globals.setScore(punkty);
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(QuestionActivity.this, YourResultActivity.class);
                startActivity(intent);
                finish();
            }
        }, 800);
    }

    public void giveQuestions() {
        questionsAnswers.Questions.get(licz).ShuffleAnswers();
        textView.setText(questionsAnswers.Questions.get(licz).question);
        buttonA.setText(questionsAnswers.Questions.get(licz).getAnswer(0));
        buttonB.setText(questionsAnswers.Questions.get(licz).getAnswer(1));
        buttonC.setText(questionsAnswers.Questions.get(licz).getAnswer(2));
        buttonD.setText(questionsAnswers.Questions.get(licz).getAnswer(3));
    }

    public void Toascik_dobra(){
        final Toast toast = Toast.makeText(QuestionActivity.this, "Pan to umie ale tego nie rozumie", Toast.LENGTH_SHORT);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        v.setTextColor(Color.GREEN);
        toast.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, 800);
    }

    public void Toascik_zla(){
        final Toast toast = Toast.makeText(QuestionActivity.this, "Bania", Toast.LENGTH_SHORT);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        v.setTextColor(Color.RED);
        toast.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, 800);
    }

}
