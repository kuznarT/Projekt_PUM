package com.example.kuznargroup.welowanie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                newString = null;
            } else {
                newString = extras.getString("user_login");
            }
        } else {
            newString = (String) savedInstanceState.getSerializable("user_login");
        }

        TextView callActTV = (TextView) findViewById(R.id.powitanie_tv);

        callActTV.append(" " + newString);

    }

    public void onNewGame(View view) {
        Intent intentNext = new Intent(this, SelectStageActivity.class);
        startActivity(intentNext);
    }


}

