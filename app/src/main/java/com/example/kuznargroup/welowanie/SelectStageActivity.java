package com.example.kuznargroup.welowanie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ardian.menuexample.R;


public class SelectStageActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    ListView listView;
    String[] stages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_stage);

        stages = getResources().getStringArray(R.array.level_difficulty);

        listView = (ListView) findViewById(R.id.stagesListView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stages);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent (getApplicationContext(), QuestionActivity.class);
                intent.putExtra("Position", position);
                startActivity(intent);
            }
        });


    }

}

