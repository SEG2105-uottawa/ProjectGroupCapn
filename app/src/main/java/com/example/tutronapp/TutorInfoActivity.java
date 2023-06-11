package com.example.tutronapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TutorInfoActivity extends AppCompatActivity {
    //instance variables
    EditText native_languages, short_description1;
    private Button buttonTutor;
    Tutor tutor;
    private Spinner education_options;

    //need to implement image as well

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_info);

        education_options = findViewById(R.id.education_options);
        native_languages = findViewById(R.id.native_languages);
        short_description1 = findViewById(R.id.short_description1);
        buttonTutor = findViewById(R.id.buttonTutor);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.containsKey("Tutor")) {
            tutor = (Tutor) bundle.getSerializable("Tutor");
        }

        /*ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.education_level, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        education_options.setAdapter(adapter);
        */


        education_options.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object text = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Please select an education level", Toast.LENGTH_SHORT).show();
            }
        });

        buttonTutor.setOnClickListener(v -> {
            if (native_languages.getText().toString().isEmpty() || short_description1.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please fill in all fields",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            Intent resultIntent = new Intent();
            resultIntent = intentPacker(resultIntent);
            setResult(RESULT_OK, resultIntent);
            finish();
        });


    }

    public Intent intentPacker(Intent resultIntent){
        resultIntent.putExtra("Education Level", education_options.getSelectedItem().toString());
        resultIntent.putExtra("Native Languages", native_languages.getText().toString());
        resultIntent.putExtra("Short Description",short_description1.getText().toString());

        return resultIntent;
    }
}

