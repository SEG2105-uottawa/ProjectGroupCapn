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

import java.util.Date;

public class LoginActivity extends AppCompatActivity {
    private Button btnLoginLoginPage;
    private DatabaseReference dataToCheck;

    private DatabaseReference complaints;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // reference to Firebase Realtime Database (users)
        dataToCheck = FirebaseDatabase.getInstance().getReference("users");
        complaints = FirebaseDatabase.getInstance().getReference("complaints");

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
                                if (user.getRole().equals("Tutor")) {
                                    Tutor tutor = userSample.getValue(Tutor.class);
                                    if (!checkSuspended(tutor)){
                                        tutor.setSuspensionEndDate(null);
                                        dataToCheck.child(tutor.getDataBaseID()).setValue(tutor);
                                        sendUser(user, userSample);
                                    }
                                }
                                else {
                                    sendUser(user, userSample);
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

    /**
     * Checks if a tutor is suspended
     * If they are, displays an appropriate toast
     * returns a boolean
     * @param tutor
     * @return boolean whether the tutor is suspended
     */
    private boolean checkSuspended(Tutor tutor){
        if (tutor.getSuspensionEndDate() == null){
            return false;
        }
        if (tutor.getSuspensionEndDate() == (long) -1) {
            Toast.makeText(getApplicationContext(), "Your account is permanently suspended",
                    Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (tutor.getSuspensionEndDate() < System.currentTimeMillis()){
            return false;
        }
        else if (tutor.getSuspensionEndDate() > System.currentTimeMillis()){
            Date suspensionEndDate = new Date(tutor.getSuspensionEndDate());
            Toast.makeText(getApplicationContext(), "Your account is suspended until " + suspensionEndDate,Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }

    /**
     * Accepts a bundle with a Specific User data, calls the WelcomeActivity
     * send the bundle to it.
     * @param bundle a bundle with user data to be sent in an intent to WelcomeActivity
     */
    private void callIntent(Bundle bundle){
        Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }


    /**Sends a user from a sample of users to the WelcomeActivity
     * @param user to be sent
     * @param userSample to get the User's Additional attributes from
     */
    private void sendUser(User user, DataSnapshot userSample){
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
    }


    //redundant but may be useful
    private void checkComplaintStatus(Tutor tutor, User user, DataSnapshot userSample) {



        /*
        String tutorEmailAddress = tutor.getEmailAddress();

        complaints.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean isSuspended = false;

                for (DataSnapshot complaintSnapshot : dataSnapshot.getChildren()) {
                    Complaint complaint = complaintSnapshot.getValue(Complaint.class);

                    // Check if the complaint is suspended
                    if (complaint.getStatus().startsWith("suspended") &&
                            tutorEmailAddress.equals(complaint.getComplaintAgainst().getEmailAddress())) {
                        String[] statusComponents = complaint.getStatus().split(" ");
                        if (statusComponents[1].charAt(0) == '0') {
                            Toast.makeText(getApplicationContext(), "Your account is permanently suspended",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // Haven't implemented anything if the date has passed the suspension date
                            Toast.makeText(getApplicationContext(), "Your account is suspended until " +
                                    statusComponents[1], Toast.LENGTH_SHORT).show();
                        }
                        isSuspended = true;
                        break;
                    }
                }

                if (!isSuspended) {
                    sendUser(user, userSample);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //
            }


        });
        */
    }


}