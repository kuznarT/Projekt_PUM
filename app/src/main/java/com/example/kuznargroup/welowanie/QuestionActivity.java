package com.example.kuznargroup.welowanie;

import android.app.Activity;
import android.content.Intent;
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
    String points;
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
            }

            public void onFinish() {
                count.setText("0");
            }
        };
        ct.start();


        //Retrofit magic part
        retrofit = new Retrofit.Builder()
                .baseUrl("http://46.101.128.24/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        questionService = retrofit.create(QuestionService.class);

        QACall = questionService.getQuestionsAnswers();

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
                    Toast.makeText(QuestionActivity.this, "Pan to umie ale tego nie rozumie :D", Toast.LENGTH_SHORT).show();
                    punkty++;
                } else {
                    Toast.makeText(QuestionActivity.this, "...Bania.", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.buttonB:
                buttonB.setBackgroundResource(R.drawable.button_game_click);
                if (buttonB.getText() == questionsAnswers.Questions.get(licz - 1).answer1) {
                    Toast.makeText(QuestionActivity.this, "Pan to umie ale tego nie rozumie :D", Toast.LENGTH_SHORT).show();
                    punkty++;
                } else {
                    Toast.makeText(QuestionActivity.this, "...Bania.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.buttonC:
                buttonC.setBackgroundResource(R.drawable.button_game_click);
                if (buttonC.getText() == questionsAnswers.Questions.get(licz - 1).answer1) {
                    Toast.makeText(QuestionActivity.this, "Pan to umie ale tego nie rozumie :D", Toast.LENGTH_SHORT).show();
                    punkty++;
                } else {
                    Toast.makeText(QuestionActivity.this, "...Bania.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.buttonD:
                buttonD.setBackgroundResource(R.drawable.button_game_click);

                if (buttonD.getText() == questionsAnswers.Questions.get(licz - 1).answer1) {
                    Toast.makeText(QuestionActivity.this, "Pan to umie ale tego nie rozumie :D", Toast.LENGTH_SHORT).show();
                    punkty++;
                } else {
                    Toast.makeText(QuestionActivity.this, "...Bania.", Toast.LENGTH_SHORT).show();
                }
                break;


        }
        if (licz == 5) {
            String resultTimeString = ct.toString();
            resultTime1ASCII = resultTimeString.charAt(resultTimeString.length() - 2);
            resultTime2ASCII = resultTimeString.charAt(resultTimeString.length()-1);
            resultTime1 = resultTime1ASCII - 48;
            resultTime2 = resultTime2ASCII - 48;
            resultTime = resultTime1 + resultTime2;
            punkty = punkty * resultTime;
            Intent intent = new Intent(QuestionActivity.this, YourResultActivity.class);
            startActivity(intent);

            final int wynik = 1;
            //points = Integer.toString(punkty);
            intent.putExtra("user_points", Integer.toString(punkty));

            startActivityForResult(intent, wynik);
            finish();

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

    public void giveQuestions() {
        questionsAnswers.Questions.get(licz).ShuffleAnswers();
        textView.setText(questionsAnswers.Questions.get(licz).question);
        buttonA.setText(questionsAnswers.Questions.get(licz).getAnswer(0));
        buttonB.setText(questionsAnswers.Questions.get(licz).getAnswer(1));
        buttonC.setText(questionsAnswers.Questions.get(licz).getAnswer(2));
        buttonD.setText(questionsAnswers.Questions.get(licz).getAnswer(3));
    }


}
