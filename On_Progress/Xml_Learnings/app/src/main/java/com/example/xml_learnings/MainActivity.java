package com.example.xml_learnings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView image;
    private TextView titleText;
    private TextView bottomOne;
    private RadioButton selectMale;
    private RadioButton selectFemale;
    private EditText typeAge;
    private EditText typeFeet;
    private EditText typeInches;
    private EditText typeWeight;
    private Button buttonCalculate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonClickListener();
        //calculateBmi();

        Toast.makeText(this, "the toast pop up!", Toast.LENGTH_SHORT).show();

    }

    private void calculateBmi() {
        String ageInput = typeAge.getText().toString();
        String feetInput = typeFeet.getText().toString();
        String inchesInput = typeInches.getText().toString();
        String weightInput = typeWeight.getText().toString();

        int age = Integer.parseInt(ageInput);
        int feet = Integer.parseInt(feetInput);
        int inches = Integer.parseInt(inchesInput);
        int weight = Integer.parseInt(weightInput);

        int totalInches = inches + (feet*12);
        double heightInMeters = totalInches*0.0254;

        double bmi = weight / (heightInMeters*heightInMeters);

        String bmiTextValue = String.valueOf(bmi);
        bottomOne.setText(bmiTextValue);
    }


    private void setupButtonClickListener() {

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "botton clicked, don't click it again!", Toast.LENGTH_LONG).show();
                calculateBmi();
            }
        });

    }

    private void findViews() {

        image = findViewById(R.id.imageview_bmi);

        titleText = findViewById(R.id.textview_bmi_calc);
        bottomOne = findViewById(R.id.textview_bottom_one);

        selectMale = findViewById(R.id.radio_button_male);
        selectFemale = findViewById(R.id.radio_button_female);

        typeAge = findViewById(R.id.edittext_age);
        typeFeet = findViewById(R.id.edittext_feet);
        typeInches = findViewById(R.id.edittext_inches);
        typeWeight = findViewById(R.id.edittext_weight);

        buttonCalculate = findViewById(R.id.button_calculate);
    }


}