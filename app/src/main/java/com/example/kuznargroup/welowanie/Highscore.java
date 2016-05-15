package com.example.kuznargroup.welowanie;

import java.util.ArrayList;

/**
 * Created by kuznar on 2016-05-15.
 */
public class Highscore {
    String name;
    String score;
    ArrayList<String> scores = new ArrayList<String>();

    public String getScore(int i){
        return scores.get(i);
    }
}