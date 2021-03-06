package com.example.kuznargroup.welowanie;

/**
 * Created by kuznar on 2016-05-15.
 */
public class Globals {
    private  static Globals instance;
    private  static  String login;
    private  static  String password;
    private  static  int score;
    private  static  int score2;
    private  static  int czas;

    private  Globals(){}

    public static String getLogin() {
        return Globals.login;
    }

    public static void setLogin(String login) {
        Globals.login = login;
    }

    public static String getPassword() {
        return Globals.password;
    }

    public static void setPassword(String password) {
        Globals.password = password;
    }

    public static int getScore() {
        return Globals.score;
    }

    public static void setScore(int score) {
        Globals.score = score;
    }

    public static int getScore2() {
        return Globals.score2;
    }

    public static void setScore2(int score2) {
        Globals.score2 = score2;
    }

    public static int getCzas() {
        return Globals.czas;
    }

    public static void setCzas(int czas) {
        Globals.czas = czas;
    }

    public static synchronized  Globals getInstance(){
        if(instance==null){
            instance= new Globals();
        }
        return instance;
    }
}
