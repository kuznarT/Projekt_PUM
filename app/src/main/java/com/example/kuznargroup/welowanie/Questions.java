package com.example.kuznargroup.welowanie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by kuznar on 2016-05-03.
 */
public class Questions {
    String question;
    String answer1;
    String answer2;
    String answer3;
    String answer4;
    ArrayList<String> answers = new ArrayList<String>();




    public String getAnswer(int i){
        return answers.get(i);
    }

    public void ShuffleAnswers(){
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        answers.add(answer4);
        Collections.shuffle(answers);
    }

}
