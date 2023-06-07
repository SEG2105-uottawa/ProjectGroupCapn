package com.example.tutronapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    ImageView logo;
    TextView signUp,signUpPicker;
    EditText firstName, lastName, emailAddress, password, verifyPassword;
    Spinner rolePicker;
    String firstNameString, lastNameString, emailAddressString, passwordString, verifyPasswordString,role;
    Button btnSignUp2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        emailAddress = findViewById(R.id.emailAddress);
        password = findViewById(R.id.password);
        verifyPassword = findViewById(R.id.verifyPassword);
        rolePicker = findViewById(R.id.rolePicker);
        btnSignUp2 = findViewById(R.id.btnSignUp2);

        firstNameString = firstName.getText().toString();
        lastNameString = lastName.getText().toString();
        emailAddressString = emailAddress.getText().toString();
        passwordString = password.getText().toString();
        verifyPasswordString = verifyPassword.getText().toString();

        rolePicker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                role = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Please select a role",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnSignUp2.setOnClickListener(v -> {

            firstNameString = firstName.getText().toString();
            lastNameString = lastName.getText().toString();
            emailAddressString = emailAddress.getText().toString();
            passwordString = password.getText().toString();
            verifyPasswordString = verifyPassword.getText().toString();

            if (passwordString.equals(verifyPasswordString)) {
                switch (role) {
                    case "Student":
                        Student student = new Student(firstNameString, lastNameString, emailAddressString, passwordString);
                        Intent intent = new Intent(SignupActivity.this, WelcomeActivty.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("student", student);


                        break;

                    case "Tutor":
                        //Tutor tutor = new Tutor(firstNameString, lastNameString, emailAddressString, passwordString);
                        break;
                }

            }
            else{
                Toast.makeText(getApplicationContext(), "Passwords don't match",
                        Toast.LENGTH_SHORT).show();
            }

        });

    }


}