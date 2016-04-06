package com.example.kuznargroup.welowanie;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ResultActivity extends Activity {

    Button buttonOnReturnToMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        buttonOnReturnToMenu = (Button) findViewById(R.id.buttonReturnToMenu);
        buttonOnReturnToMenu.setBackgroundResource(R.drawable.button_game);


    }

    public void onReturnToMenu(View view) {
        buttonOnReturnToMenu.setBackgroundResource(R.drawable.button_game_click);
        finish();

    }
}
