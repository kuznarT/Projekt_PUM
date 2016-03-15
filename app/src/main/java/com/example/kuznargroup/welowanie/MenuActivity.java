package com.example.kuznargroup.welowanie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.ardian.menuexample.R;

/**
 * Created by Ardian on 2016-03-14.
 */
public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("user_login");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("user_login");
        }

        TextView callActTV = (TextView) findViewById(R.id.powitanie_tv);

        callActTV.append(" " +newString);

    }

    public void onNewGame(View view) {
        Intent intentDalej = new Intent(this, SelectStageActivity.class);
        startActivity(intentDalej);
    }


}

