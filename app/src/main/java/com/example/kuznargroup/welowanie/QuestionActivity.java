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
import java.util.Random;

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
    Retrofit retrofit;
    QuestionService questionService;
    Call<pytania> QACall;
    pytania questionsAnswers;
    int licz=0,punkty=0;
    String points;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        buttonA = (Button) findViewById(R.id.buttonA);
        buttonB = (Button) findViewById(R.id.buttonB);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonD = (Button) findViewById(R.id.buttonD);
        textView = (TextView) findViewById(R.id.textView_pytanie);

        buttonA.setBackgroundResource(R.drawable.button_game);
        buttonB.setBackgroundResource(R.drawable.button_game);
        buttonC.setBackgroundResource(R.drawable.button_game);
        buttonD.setBackgroundResource(R.drawable.button_game);



        //Wybór poziomu od 0 do 7, czyli od Semestru I do poziomu Bonusowego
       // Intent intent = getIntent();
       // position = intent.getExtras().getInt("Position");
       // position++; //Impossible to use this as id if it gets value 0 (this is simple but stupid solution)

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
                if(response.isSuccessful()) {
                    questionsAnswers = response.body();

                   // textView.setText(Integer.toString(questionsAnswers.success));
                    giveQuestions();



                    for(Questions c : questionsAnswers.Questions) {
                        Log.i(TAG,String.format("%s: %s",c.question,c.answer1));



                        Log.i(TAG,"---------");
                    }


                }
                else
                {
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
                    if(buttonA.getText() == questionsAnswers.Questions.get(licz-1).answer1)
                    {
                        Toast.makeText(QuestionActivity.this,"Pan to umie ale tego nie rozumie :D",Toast.LENGTH_LONG).show();
                        punkty++;
                    }
                    else{
                        Toast.makeText(QuestionActivity.this,"...Bania.",Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.buttonB:
                    buttonB.setBackgroundResource(R.drawable.button_game_click);
                    if(buttonB.getText() == questionsAnswers.Questions.get(licz-1).answer1)
                    {
                        Toast.makeText(QuestionActivity.this,"Pan to umie ale tego nie rozumie :D",Toast.LENGTH_LONG).show();
                        punkty++;
                    }
                    else{
                        Toast.makeText(QuestionActivity.this,".... Bania.",Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.buttonC:
                    buttonC.setBackgroundResource(R.drawable.button_game_click);
                    if(buttonC.getText() == questionsAnswers.Questions.get(licz-1).answer1)
                    {
                        Toast.makeText(QuestionActivity.this,"Pan to umie ale tego nie rozumie :D",Toast.LENGTH_LONG).show();
                        punkty++;
                    }
                    else{
                        Toast.makeText(QuestionActivity.this,".... Bania.",Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.buttonD:
                    buttonD.setBackgroundResource(R.drawable.button_game_click);

                    if(buttonD.getText() == questionsAnswers.Questions.get(licz-1).answer1)
                        {
                            Toast.makeText(QuestionActivity.this,"Pan to umie ale tego nie rozumie :D",Toast.LENGTH_LONG).show();
                            punkty++;
                        }
                        else{
                            Toast.makeText(QuestionActivity.this,".... Bania.",Toast.LENGTH_LONG).show();
                        }
                    break;


            }
            if(licz == 5){
            Intent intent = new Intent(QuestionActivity.this, YourResultActivity.class);
            startActivity(intent);

            final int wynik = 1;
            //points = Integer.toString(punkty);
            intent.putExtra("user_points", Integer.toString(punkty));

            startActivityForResult(intent, wynik);
            finish();

        }else {
                giveQuestions();
            }

    }

    public void giveQuestions(){
        questionsAnswers.Questions.get(licz).ShuffleAnswers();
        textView.setText(questionsAnswers.Questions.get(licz).question);
        buttonA.setText(questionsAnswers.Questions.get(licz).getAnswer(0));
        buttonB.setText(questionsAnswers.Questions.get(licz).getAnswer(1));
        buttonC.setText(questionsAnswers.Questions.get(licz).getAnswer(2));
        buttonD.setText(questionsAnswers.Questions.get(licz).getAnswer(3));
    }

}
