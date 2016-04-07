package com.example.kuznargroup.welowanie;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/*
Example code from:
http://developer.android.com/reference/java/net/HttpURLConnection.html
http://stackoverflow.com/questions/10116961/can-you-explain-the-httpurlconnection-connection-process

 */

public class RegisterActivity extends AppCompatActivity {

    EditText etLogin;
    EditText etPassword;
    TextView errorMsg;
    Button button;
    String login;
    String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        button = (Button) findViewById(R.id.registerButton);
        button.setBackgroundResource(R.drawable.button_game);

        errorMsg = (TextView) findViewById(R.id.registerError);
        etLogin = (EditText) findViewById(R.id.registerLogin);
        etPassword = (EditText) findViewById(R.id.registerPassword);
    }

    public void onStartRegister(View view) {
        button.setBackgroundResource(R.drawable.button_game_click);
        String login = etLogin.getText().toString();
        String password = etPassword.getText().toString();
    }

    private abstract class webServicesRegister extends AsyncTask<Void, Void, Void> {

        protected void doInBackgroun(Void... params) {
            try {
                URL url = new URL("http://example.com");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setDoOutput(true); //POST method enabled
                urlConnection.setRequestMethod("POST");


                OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
                writer.write("login=" + login + "&password=" + password);
                writer.close();






                urlConnection.disconnect ();


            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }
}



