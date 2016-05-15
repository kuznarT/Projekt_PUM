/*
Code based mostly on examples. All knowledge comes from:

http://developer.android.com/reference/android/os/AsyncTask.html
http://developer.android.com/reference/java/net/HttpURLConnection.html
http://stackoverflow.com/questions/10116961/can-you-explain-the-httpurlconnection-connection-process

And more stack overflow and git repositories and a bit more of stack overflow :)

 */

package com.example.kuznargroup.welowanie;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
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
import java.net.MalformedURLException;
import java.net.URL;

//TODO: Extract string resources

public class LoginActivity extends Activity {

    EditText etLogin;
    EditText etPassword;
    Button button;
    Button button2;
    String login;
    String password;
    JSONObject Json;
    int loginSuccessful;
    int score;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLogin = (EditText) findViewById(R.id.loginText);
        etPassword = (EditText) findViewById(R.id.passwordText);
        button = (Button) findViewById(R.id.logInButton);
        button2 = (Button) findViewById(R.id.createAccButton);
        button.setBackgroundResource(R.drawable.button_game);
        button2.setBackgroundResource(R.drawable.button_game);

    }

    public void onStartLogin(View view) {

        login = etLogin.getText().toString();
        password = etPassword.getText().toString();
        button.setBackgroundResource(R.drawable.button_game_click);

        new webServicesLogin().execute();

    }

    public void onStartCreateAcc(View view) {
        button2.setBackgroundResource(R.drawable.button_game_click);
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    private class webServicesLogin extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... params) {
            try {
                URL url = new URL("http://46.101.128.24/login.php");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlConnection.connect();

                OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
                writer.write("name=" + login + "&password=" + password);
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
                Globals.setScore(Json.getInt("score"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(loginSuccessful == 1){
                Globals.setLogin(login);
                Globals.setPassword(password);
                Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
            else{
                Toast.makeText (LoginActivity.this, "Bład", Toast.LENGTH_SHORT).show ();
                //TODO: Better error explanation, e.g. not all fields filled and so on (Require also some work in php script)
                //TODO: Add text field for error messages instead of toast
                //TODO: Password should be hashed; sending in raw text at the moment
            }

        }
    }
}

