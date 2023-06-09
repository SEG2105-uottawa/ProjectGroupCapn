/**
 * 
 */

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
            Intent resultIntent = new Intent();
            resultIntent = intentPacker(resultIntent);
            setResult(RESULT_OK, resultIntent);
            finish();
        });





    }
    public Intent intentPacker(Intent resultIntent){
        resultIntent.putExtra("CardHolder", cardHolder.getText().toString());
        resultIntent.putExtra("CardNumber", Integer.parseInt(cardNumber.getText().toString()));
        resultIntent.putExtra("ValidTill",Integer.parseInt(validTill.getText().toString()));
        resultIntent.putExtra("SecurityCode",Integer.parseInt(securityCode.getText().toString()));
        resultIntent.putExtra("StreetNumber",Integer.parseInt(streetNumber.getText().toString()));
        resultIntent.putExtra("StreetName",streetName.getText().toString());
        resultIntent.putExtra("PostCode",postCode.getText().toString());
        return resultIntent;
    }
}