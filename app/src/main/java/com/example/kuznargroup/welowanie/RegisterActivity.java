/*
Code based mostly on examples. All knowledge comes from:

http://developer.android.com/reference/android/os/AsyncTask.html
http://developer.android.com/reference/java/net/HttpURLConnection.html
http://stackoverflow.com/questions/10116961/can-you-explain-the-httpurlconnection-connection-process

And more stack overflow and git repositories and a bit more of stack overflow :)

 */

package com.example.kuznargroup.welowanie;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

//TODO: Extract string resources i guess, also the same in LoginActivity

public class RegisterActivity extends AppCompatActivity {

    EditText etLogin;
    EditText etPassword;
    Button button;
    String login;
    String password;
    int accSet;
    JSONObject Json;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        button = (Button) findViewById(R.id.registerButton);
        button.setBackgroundResource(R.drawable.button_game);

        etLogin = (EditText) findViewById(R.id.registerLogin);
        etPassword = (EditText) findViewById(R.id.registerPassword);
    }


    public void onStartRegister(View view) {
        button.setBackgroundResource(R.drawable.button_game_click);
        login = etLogin.getText().toString();
        password = etPassword.getText().toString();

        new webServicesRegister().execute();
    }

    private class webServicesRegister extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                URL url = new URL("http://46.101.128.24/create_user.php");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setDoOutput(true); //POST method enabled
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty ("Content-Type", "application/x-www-form-urlencoded");
                urlConnection.connect();


                OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
                writer.write("name=" + login + "&password=" + password);
                writer.flush ();
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


            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void response) {
            try {
                accSet = Json.getInt("success");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if(accSet == 1){
                Toast.makeText (RegisterActivity.this, "Rejestracja zakończona", Toast.LENGTH_SHORT).show ();
                Intent intent = new Intent(RegisterActivity.this, MenuActivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText (RegisterActivity.this, "Bład", Toast.LENGTH_SHORT).show ();
                //TODO: Better error explanation, e.g. not all fields filled and so on
                //TODO: Add text field for error messages instead of toast
                //TODO: Password should be hashed; sending in raw text at the moment
            }


        }
    }
}



