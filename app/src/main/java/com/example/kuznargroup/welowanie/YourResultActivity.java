package com.example.kuznargroup.welowanie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class YourResultActivity extends Activity {

    Button buttonOnReturnToMenu;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_result);
        textView = (TextView) findViewById(R.id.yourPoints);
        buttonOnReturnToMenu = (Button) findViewById(R.id.buttonReturnToMenu);
        buttonOnReturnToMenu.setBackgroundResource(R.drawable.button_game);

        String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                newString = null;
            } else {
                newString = extras.getString("user_points");
            }
        } else {
            newString = (String) savedInstanceState.getSerializable("user_points");
        }


        TextView callActTV = (TextView) findViewById(R.id.powitanie_tv);

        textView.setText("Zdobyłeś " + newString + " na 5 punktów");

    }

    public void onReturnToMenu(View view) {
        buttonOnReturnToMenu.setBackgroundResource(R.drawable.button_game_click);
        Intent intentNext = new Intent(this, MenuActivity.class);
        startActivity(intentNext);
        finish();

    }
}
