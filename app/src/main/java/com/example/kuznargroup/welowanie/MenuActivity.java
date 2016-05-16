package com.example.kuznargroup.welowanie;
//
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MenuActivity extends Activity {

    Button buttonNewGame;
    Button buttonResult;
    Button buttonExit;
    Globals g = Globals.getInstance();
    JSONObject Json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        buttonNewGame = (Button) findViewById(R.id.buttonNewGame);
        buttonNewGame.setBackgroundResource(R.drawable.button_game);
        buttonResult = (Button) findViewById(R.id.buttonResult);
        buttonResult.setBackgroundResource(R.drawable.button_game);
        buttonExit = (Button) findViewById(R.id.buttonExit);
        buttonExit.setBackgroundResource(R.drawable.button_game);
        if(Globals.getLogin().isEmpty()){
            Intent intentNext = new Intent(this, LoginActivity.class);
            startActivity(intentNext);
        }else {
            new webServicesLogin().execute();
        }

    }

    public void onNewGame(View view) {
        buttonNewGame.setBackgroundResource(R.drawable.button_game_click);
        Intent intentNext = new Intent(this, SelectStageActivity.class);
        startActivity(intentNext);
    }

    @Override
    protected void onStart() {
        super.onStart();
        buttonNewGame.setBackgroundResource(R.drawable.button_game);
        buttonResult.setBackgroundResource(R.drawable.button_game);
        buttonExit.setBackgroundResource(R.drawable.button_game);
    }

    public void onResult(View view) {
        buttonResult.setBackgroundResource(R.drawable.button_game_click);
        Intent intentNext = new Intent(this, ResultActivity.class);
        startActivity(intentNext);
    }

    public void onExit(View view) {
        buttonExit.setBackgroundResource(R.drawable.button_game_click);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }

    private class webServicesLogin extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                URL url = new URL("http://46.101.128.24/login.php");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlConnection.connect();

                OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
                writer.write("name=" + Globals.getLogin() + "&password=" + Globals.getPassword());
                writer.flush();
                writer.close();

                String stringLine;
                InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();

                while ((stringLine = bufferedReader.readLine()) != null) stringBuilder.append(stringLine);

                Json = new JSONObject(stringBuilder.toString());

                inputStreamReader.close();
                bufferedReader.close();

                urlConnection.disconnect ();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            try {

                Globals.setScore2(Json.getInt("score"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            TextView callActTV = (TextView) findViewById(R.id.powitanie_tv);

            callActTV.append("Witaj " + Globals.getLogin()+" masz "+Globals.getScore2()+" pkt");


        }
    }
}