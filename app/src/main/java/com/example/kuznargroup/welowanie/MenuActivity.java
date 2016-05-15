package com.example.kuznargroup.welowanie;
//
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends Activity {

    Button buttonNewGame;
    Button buttonResult;
    Button buttonExit;
    Globals g = Globals.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        buttonNewGame = (Button) findViewById(R.id.buttonNewGame);
        buttonNewGame.setBackgroundResource(R.drawable.button_game);
        buttonResult = (Button) findViewById(R.id.buttonResult);
        buttonResult.setBackgroundResource(R.drawable.button_game);
        buttonExit = (Button) findViewById(R.id.buttonExit);
        buttonExit.setBackgroundResource(R.drawable.button_game);



        TextView callActTV = (TextView) findViewById(R.id.powitanie_tv);

        callActTV.append("Witaj " + Globals.getLogin()+" masz "+Globals.getScore()+" pkt");

    }

    public void onNewGame(View view) {
        buttonNewGame.setBackgroundResource(R.drawable.button_game_click);
        Intent intentNext = new Intent(this, SelectStageActivity.class);
        startActivity(intentNext);
    }

    @Override
    protected void onStart() {
        super.onStart();
        buttonNewGame.setBackgroundResource(R.drawable.button_game);
        buttonResult.setBackgroundResource(R.drawable.button_game);
        buttonExit.setBackgroundResource(R.drawable.button_game);
    }

    public void onResult(View view) {
        buttonResult.setBackgroundResource(R.drawable.button_game_click);
        Intent intentNext = new Intent(this, ResultActivity.class);
        startActivity(intentNext);
    }

    public void onExit(View view) {
        buttonExit.setBackgroundResource(R.drawable.button_game_click);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }
}