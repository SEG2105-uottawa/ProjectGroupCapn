package com.example.tutronapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private Button btnLoginLoginPage;
    private DatabaseReference dataToCheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         dataToCheck = FirebaseDatabase.getInstance().getReference("users");

        TextView enterEmailAddress = findViewById(R.id.enterEmailAddress);
        TextView enterPassword = findViewById(R.id.enterPassword);
        btnLoginLoginPage = findViewById(R.id.btnLoginLoginPage);

        btnLoginLoginPage.setOnClickListener(v -> {

            String emailAddress = enterEmailAddress.getText().toString();
            String password = enterPassword.getText().toString();

            Query getEmail = dataToCheck.orderByChild("emailAddress").equalTo(emailAddress);

            getEmail.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot userSample : dataSnapshot.getChildren()) {
                        User user = userSample.getValue(User.class);
                        if (user.getPassword().equals(password)) {
                            switch (user.getRole()) {
                                case "Student":
                                    Student student = user.toStudent();
                                    Bundle bundleForStudent = new Bundle();
                                    bundleForStudent.putSerializable("Student", student);
                                    callIntent(bundleForStudent);
                                    break;
                                case "Tutor":
                                    Tutor tutor = user.toTutor();
                                    Bundle bundleForTutor = new Bundle();
                                    bundleForTutor.putSerializable("Tutor", tutor);
                                    callIntent(bundleForTutor);
                                    break;
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Incorrect Username or Password",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle database read error if needed
                }
            });

        });

    }

    public void callIntent(Bundle bundle){
        Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }


}