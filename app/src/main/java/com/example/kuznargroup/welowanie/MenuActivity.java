package com.example.kuznargroup.welowanie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.ardian.menuexample.R;

/**
 * Created by Ardian on 2016-03-14.
 */
public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


    }

    public void onNewGame(View view) {
        Intent intentDalej = new Intent(this, SelectStageActivity.class);
        startActivity(intentDalej);
    }
}

