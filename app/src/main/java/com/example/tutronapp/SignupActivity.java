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
import android.widget.Spinner;
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
        btnSignUp2 = findViewById(R.id.btnSignUp2);
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

            // Empty error handling
            if (firstNameString.isEmpty() || lastNameString.isEmpty() || emailAddressString.isEmpty()
                    || passwordString.isEmpty() || verifyPasswordString.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please fill in all fields",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            // Check email valid
            if (!isValidEmail(emailAddressString)) {
                Toast.makeText(getApplicationContext(), "Invalid email", Toast.LENGTH_SHORT).show();
                return;
            }


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
                                    user = new Student(firstNameString, lastNameString, emailAddressString, passwordString);
                                    Intent intent = new Intent(SignupActivity.this, StudentInfoActivity.class);
                                    startActivityForResult(intent, REQUEST_CODE_STUDENT);
                                    break;

                                case "Tutor":
                                    user = new Tutor(firstNameString, lastNameString, emailAddressString, passwordString);
                                    Bundle bundleForTutor = new Bundle();
                                    bundleForTutor.putSerializable("Tutor", user);
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
        addToDatabase(user);
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

    private void intentUnpackerStudent(Intent intent){
        CreditCard creditCard = new CreditCard(intent.getStringExtra("CardHolder"),
                                    intent.getIntExtra("CardNumber",0),
                                    intent.getIntExtra("ValidTill",0),
                                    intent.getIntExtra("SecurityCode",0));
        Address address = new Address(intent.getIntExtra("StreetNumber",0),
                                    intent.getStringExtra("StreetName"),
                                    intent.getStringExtra("PostCode"));
        Student student = (Student) user;
        student.setAddress(address);
        student.setCreditCard(creditCard);
        user = (User) student;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_STUDENT && resultCode == Activity.RESULT_OK) {
            intentUnpackerStudent(data);
            Bundle bundleForStudent = new Bundle();
            bundleForStudent.putSerializable("Student", user);
            callIntent(bundleForStudent);

        }
    }

    // Only check email by the rules
    // limitation: not actually checking the email working
    private boolean isValidEmail(String email) {
        // valid character for email
        String character = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        // Check email contain valid character and length for email
        if(!email.matches(character) || email.length() > 320) {
            return false;
        }

        // Check if there is at least one "@" and ".", or there is more than one "@"
        int atIndex = email.indexOf("@");
        int dotIndex = email.indexOf(".");
        if (atIndex == -1 || dotIndex == -1 || email.indexOf("@", atIndex + 1) != -1) {
            return false;
        }

        // Check if there is at least one character before "@" and after "."
        if (atIndex == 0 || dotIndex <= atIndex + 1 || dotIndex == email.length() - 1) {
            return false;
        }

        // Check if there are two dots in the domain part
        int secondDotIndex = email.indexOf(".", dotIndex+1);
        if (secondDotIndex != -1 && secondDotIndex != email.lastIndexOf(".")) {
            return false;
        }

        return true;
    }



}