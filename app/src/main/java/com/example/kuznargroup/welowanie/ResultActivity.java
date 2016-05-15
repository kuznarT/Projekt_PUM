package com.example.kuznargroup.welowanie;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResultActivity extends Activity {
    Retrofit retrofit;
    GetTop questionService;
    Call<TopScores> QACall;
    TopScores questionsAnswers;
    Button buttonOnReturnToMenu;
    private static final String TAG = "suemar";
    TextView firstPlace;
    TextView secondPlace;
    TextView thirdPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        buttonOnReturnToMenu = (Button) findViewById(R.id.buttonReturnToMenu);
        buttonOnReturnToMenu.setBackgroundResource(R.drawable.button_game);
        firstPlace = (TextView) findViewById(R.id.firstPlace);
        secondPlace = (TextView) findViewById(R.id.secondPlace);
        thirdPlace = (TextView) findViewById(R.id.thirdPlace);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://46.101.128.24/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        questionService = retrofit.create(GetTop.class);

        QACall = questionService.getScore();

        QACall.enqueue(new Callback<TopScores>() {
            @Override
            public void onResponse(Call<TopScores> call, Response<TopScores> response) {
                if (response.isSuccessful()) {
                    questionsAnswers = response.body();

                    firstPlace.setText(questionsAnswers.Highscore.get(0).name + "-"+questionsAnswers.Highscore.get(0).score+" pkt");
                    secondPlace.setText(questionsAnswers.Highscore.get(1).name+ "-"+questionsAnswers.Highscore.get(1).score+" pkt");
                    thirdPlace.setText(questionsAnswers.Highscore.get(2).name+ "-"+questionsAnswers.Highscore.get(2).score+" pkt");



                    for (Highscore c : questionsAnswers.Highscore) {
                        Log.i(TAG, String.format("%s: %s", c.name, c.score));


                        Log.i(TAG, "---------");
                    }


                } else {
                    Toast.makeText(ResultActivity.this, "LOL2", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TopScores> call, Throwable t) {
                Log.d("Coś się zepsuło", t.getMessage());
                Toast.makeText(ResultActivity.this, "LOL", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onReturnToMenu(View view) {
        buttonOnReturnToMenu.setBackgroundResource(R.drawable.button_game_click);
        finish();

    }
}
