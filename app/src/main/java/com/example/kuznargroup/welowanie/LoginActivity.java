package com.example.kuznargroup.welowanie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    EditText etLogin;
    EditText etPassword;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLogin = (EditText) findViewById(R.id.loginText);
        etPassword = (EditText) findViewById(R.id.passwordText);
        button = (Button) findViewById(R.id.logInButton);
        button.setBackgroundResource(R.drawable.button_menu);

    }

    public void onStartLogin(View view) {

        String login = etLogin.getText().toString();
        String password = etPassword.getText().toString();
        button.setBackgroundResource(R.drawable.button_menu_click);


        if ((login.equals("Pluto")) && (password.equals("asd"))) {
            Intent intent = new Intent(this, MenuActivity.class);
            final int result = 1;

            intent.putExtra("user_login", login);

            startActivityForResult(intent, result);
            finish();
        } else{
            Toast.makeText(this, "Złe hasło lub login", Toast.LENGTH_LONG).show();




        }


    }

}

