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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class SignupActivity extends AppCompatActivity {

    private ImageView logo;
    private TextView signUp,signUpPicker;
    private EditText firstName, lastName, emailAddress, password, verifyPassword;
    private Spinner rolePicker;
    private String firstNameString, lastNameString, emailAddressString, passwordString, verifyPasswordString,role;
    private Button btnSignUp2;
    private User user;
    private DatabaseReference dataToCheck;



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
        btnSignUp2 = findViewById(R.id.btnLoginLoginPage);
        dataToCheck = FirebaseDatabase.getInstance().getReference();

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
                        user = new Student(firstNameString, lastNameString, emailAddressString, passwordString);
                        Bundle bundleForStudent = new Bundle();
                        bundleForStudent.putSerializable("Student", user);
                        callIntent(bundleForStudent);
                        break;

                    case "Tutor":
                        user = new Tutor(firstNameString, lastNameString, emailAddressString, passwordString);
                        Bundle bundleForTutor = new Bundle();
                        bundleForTutor.putSerializable("Tutor", user);
                        callIntent(bundleForTutor);
                        break;
                }


            }
            else{
                Toast.makeText(getApplicationContext(), "Passwords don't match",
                        Toast.LENGTH_SHORT).show();
            }

        });

    }

    public void callIntent(Bundle bundle){
        Intent intent = new Intent(SignupActivity.this, WelcomeActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }


}