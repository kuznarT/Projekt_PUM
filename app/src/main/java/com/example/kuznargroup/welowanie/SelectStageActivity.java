package com.example.kuznargroup.welowanie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ardian.menuexample.R;


/**
 * Created by Ardian on 2016-03-14.
 */
public class SelectStageActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    ListView listView;
    String[] stages= {"Semestr I", "Semestr II", "Semestr III", "Semestr IV", "Semestr V", "Semestr VI", "Bonus Best Of" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_stage);

        listView = (ListView) findViewById(R.id.stagesListView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,stages);
        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                //tutaj dalszy kod
//            }
//        });




    }

}

