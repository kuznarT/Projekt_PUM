package com.example.kuznargroup.welowanie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EndGameActivity extends Activity {

    Button buttonOnReturnToSelectStageClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        buttonOnReturnToSelectStageClick.setBackgroundResource(R.drawable.button_menu);
    }

    public void onReturnToSelectStageClick(View view) {
        buttonOnReturnToSelectStageClick.setBackgroundResource(R.drawable.button_menu_click);
        Intent intent = new Intent(this, SelectStageActivity.class);
        startActivity(intent);

    }
}
