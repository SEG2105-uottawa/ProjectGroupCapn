package com.example.tutronapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StudentInfoActivity extends AppCompatActivity {

    EditText cardHolder, cardNumber, validTill, securityCode;
    EditText streetNumber, streetName, postCode;
    Button btnProceed;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        cardHolder = findViewById(R.id.cardHolder);
        cardNumber = findViewById(R.id.cardNumber);
        validTill = findViewById(R.id.validTill);
        securityCode = findViewById(R.id.securityCode);

        streetNumber = findViewById(R.id.streetNumber);
        streetName = findViewById(R.id.streetName);
        postCode = findViewById(R.id.postCode);

        btnProceed = findViewById(R.id.btnProceed);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.containsKey("Student")) {
            student = (Student) bundle.getSerializable("Student");
        }

        btnProceed.setOnClickListener(v -> {
            CreditCard creditCard = new CreditCard(cardHolder.getText().toString(),
                                    Integer.parseInt(cardNumber.getText().toString()),
                                    Integer.parseInt(validTill.getText().toString()),
                                    Integer.parseInt(securityCode.getText().toString()));
            Address address = new Address(Integer.parseInt(streetNumber.getText().toString()),
                                            streetName.getText().toString(),
                                            postCode.getText().toString());

            student.setCreditCard(creditCard);
            student.setAddress(address);

            User user = (User) student;

            Intent returnIntent = new Intent();
            Bundle newBundle = new Bundle();
            newBundle.putSerializable("Student",user);
            returnIntent.putExtras(newBundle);
            setResult(RESULT_OK, returnIntent);
            finish();
        });





    }
}