package com.example.tutronapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class SignupActivity extends AppCompatActivity {

    private User user;
    private EditText firstName, lastName, emailAddress, password, verifyPassword;
    private Spinner rolePicker;
    private String firstNameString, lastNameString, emailAddressString, passwordString, verifyPasswordString,role;
    private Button btnSignUp2;

    private DatabaseReference dataToCheck;
    private static final int REQUEST_CODE_STUDENT = 1;



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
        dataToCheck = FirebaseDatabase.getInstance().getReference("users");

        //converting input to strings
        firstNameString = firstName.getText().toString();
        lastNameString = lastName.getText().toString();
        emailAddressString = emailAddress.getText().toString();
        passwordString = password.getText().toString();
        verifyPasswordString = verifyPassword.getText().toString();


        //only exists as a test case, no real purpose
        //this is kind of what would work for the calls and passing intents.
        Student newStudent = new Student("test","test","test","test",new CreditCard(),new Address());
        User newUser = (User) newStudent;
        Bundle testBundle = new Bundle();
        testBundle.putSerializable("test", newUser);
        User retriving = (User) testBundle.getSerializable("test");
        Student test = (Student) retriving;
        test.getLastName();

        //the role selection drop down menu input acquisition
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

        //when SignUp button is clicked, the values of the strings at that point are taken
        btnSignUp2.setOnClickListener(v -> {

            firstNameString = firstName.getText().toString();
            lastNameString = lastName.getText().toString();
            emailAddressString = emailAddress.getText().toString();
            passwordString = password.getText().toString();
            verifyPasswordString = verifyPassword.getText().toString();

            //Query is sent to the database
            //Purpose : get a copy of all things in database with the same emailAddress
            //to make sure no duplicate accounts
            Query getEmail = dataToCheck.orderByChild("emailAddress").equalTo(emailAddressString);

            getEmail.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //makes sure the provided email is not on the database
                    if (! dataSnapshot.exists()) {

                        //checks if verifyPassword and password match
                        if (passwordString.equals(verifyPasswordString)) {

                            //switch case scenarios for Student and teacher
                            switch (role) {
                                case "Student":

                                    //bundles the student and turns them into a User
                                    User tempUser = new Student(firstNameString, lastNameString, emailAddressString, passwordString);
                                    Bundle tempBundleForStudent = new Bundle();
                                    tempBundleForStudent.putSerializable("Student", tempUser);
                                    Intent intent = new Intent(SignupActivity.this, StudentInfoActivity.class);
                                    intent.putExtras(tempBundleForStudent);
                                    startActivityForResult(intent, REQUEST_CODE_STUDENT);
                                    break;

                                case "Tutor":
                                    user = new Tutor(firstNameString, lastNameString, emailAddressString, passwordString);
                                    Bundle bundleForTutor = new Bundle();
                                    bundleForTutor.putSerializable("Tutor", user);
                                    addToDatabase(user);
                                    callIntent(bundleForTutor);
                                    break;

                                //add the Admin case and implement stuff for it
                            }



                        } else {
                            Toast.makeText(getApplicationContext(), "Passwords don't match",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Account already exists",
                                Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        });

    }

    private void getStudentInfo(Bundle bundle){

    }
    //takes the info of the Student/tutor and takes it to the welcome page
    private void callIntent(Bundle bundle){
        Intent intent = new Intent(SignupActivity.this, WelcomeActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    //adds user to database
    private void addToDatabase(User user){
        DatabaseReference newNode = dataToCheck.push();
        String nodeKey = newNode.getKey();
        newNode.setValue(user);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_STUDENT && resultCode == Activity.RESULT_OK) {
            Bundle bundle = data.getExtras();
            if (bundle != null && bundle.containsKey("Student")) {
                user = (Student) bundle.getSerializable("Student");
                Bundle bundleForStudent = new Bundle();
                bundleForStudent.putSerializable("Student",user);
                //callIntent starts a new Actvity to the Welcome Screen
                callIntent(bundleForStudent);
            }
        }
    }


}