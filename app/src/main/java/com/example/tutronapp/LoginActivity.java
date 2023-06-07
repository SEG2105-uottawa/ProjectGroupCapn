package com.example.tutronapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private Button btnLoginLoginPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView enterEmailAddress = findViewById(R.id.enterEmailAddress);
        TextView enterPassword = findViewById(R.id.enterPassword);
        btnLoginLoginPage = findViewById(R.id.btnLoginLoginPage);

        btnLoginLoginPage.setOnClickListener(v -> {

            String emailAddress = enterEmailAddress.getText().toString();
            String password = enterPassword.getText().toString();
            //cross check these to DataBase

            //make a new instance and then make a bundle
            Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
            //add bundle to instance
            startActivity(intent);
        });


    }
}