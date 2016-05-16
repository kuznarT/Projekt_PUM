package com.example.kuznargroup.welowanie;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YourResultActivity extends Activity {
    private static final String TAG = "suemar";
    Button buttonOnReturnToMenu;
    TextView textView;
    JSONObject Json;
    int loginSuccessful;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_result);
        textView = (TextView) findViewById(R.id.yourPoints);
        buttonOnReturnToMenu = (Button) findViewById(R.id.buttonReturnToMenu);
        buttonOnReturnToMenu.setBackgroundResource(R.drawable.button_game);
        new webServicesLogin().execute();




        TextView callActTV = (TextView) findViewById(R.id.powitanie_tv);


        textView.setText("Zdobyłeś " + Globals.getScore() + " punktów w czasie "+(60-Globals.getCzas())+" sek");

    }

    private class webServicesLogin extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                URL url = new URL("http://46.101.128.24/putscore.php");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlConnection.connect();

                OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
                writer.write("name=" + Globals.getLogin() + "&score=" + Globals.getScore());
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
                loginSuccessful = Json.getInt("success");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(loginSuccessful == 1){

            }
            else{
                Toast.makeText (YourResultActivity.this, "Nie dodano punktow do bazy", Toast.LENGTH_SHORT).show ();
                //TODO: Better error explanation, e.g. not all fields filled and so on (Require also some work in php script)
                //TODO: Add text field for error messages instead of toast
                //TODO: Password should be hashed; sending in raw text at the moment
            }

        }
    }
    public void onReturnToMenu(View view) {
        buttonOnReturnToMenu.setBackgroundResource(R.drawable.button_game_click);
        Intent intentNext = new Intent(this, MenuActivity.class);
        startActivity(intentNext);
        finish();

    }
}
