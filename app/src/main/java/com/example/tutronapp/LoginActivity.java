/**
 * LoginActivity handles login function(base on data in Firebase Realtime Database).
 * Start WelcomeActivity if login successfully.
 * Unknown email address -> show "No such account found"
 * Wrong password -> show "Incorrect Password"
 */

package com.example.tutronapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

        // reference to Firebase Realtime Database (users)
        dataToCheck = FirebaseDatabase.getInstance().getReference("users");

        TextView enterEmailAddress = findViewById(R.id.enterEmailAddress);
        TextView enterPassword = findViewById(R.id.enterPassword);
        btnLoginLoginPage = findViewById(R.id.btnLoginLoginPage);


        btnLoginLoginPage.setOnClickListener(v -> {

            String emailAddress = enterEmailAddress.getText().toString();
            String password = enterPassword.getText().toString();

            // create query to get user data base on email address
            Query getEmail = dataToCheck.orderByChild("emailAddress").equalTo(emailAddress);

            getEmail.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // Empty error handling
                    if (emailAddress.isEmpty() || password.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Please enter email and " +
                                "password", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Check user email exists
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot userSample : dataSnapshot.getChildren()) {
                            User user = userSample.getValue(User.class);
                            // Check user password
                            if (user.getPassword().equals(password)) {
                                switch (user.getRole()) {
                                    case "Student":
                                        Student student = userSample.getValue(Student.class);
                                        Bundle bundleForStudent = new Bundle();
                                        bundleForStudent.putSerializable("Student", student);
                                        callIntent(bundleForStudent);
                                        break;
                                    case "Tutor":
                                        Tutor tutor = userSample.getValue(Tutor.class);
                                        Bundle bundleForTutor = new Bundle();
                                        bundleForTutor.putSerializable("Tutor", tutor);
                                        callIntent(bundleForTutor);
                                        break;
                                    case "Administrator":
                                        Administrator administrator = userSample.getValue(Administrator.class);
                                        Bundle bundleForAdministrator = new Bundle();
                                        bundleForAdministrator.putSerializable("Administrator", administrator);
                                        callIntent(bundleForAdministrator);

                                    }
                            } else {
                                Toast.makeText(getApplicationContext(), "Incorrect Password",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "No such account found",
                                Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle database read error if needed
                    Toast.makeText(getApplicationContext(), "Database Error: " +
                            databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        });

    }

    // Navigate from LoginActivity to WelcomeActivity
    private void callIntent(Bundle bundle){
        Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }


}