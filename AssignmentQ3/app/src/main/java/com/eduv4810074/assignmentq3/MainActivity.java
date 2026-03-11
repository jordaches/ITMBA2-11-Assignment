package com.eduv4810074.assignmentq3;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputHeight, inputWeight;
    private TextView outputBmiValue,outputCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputHeight = findViewById(R.id.input_height);
        inputWeight = findViewById(R.id.input_weight);
        Button btnCalculate = findViewById(R.id.button_calculate);
        outputBmiValue = findViewById(R.id.output_bmivalue);
        outputCategory = findViewById(R.id.output_category);
        btnCalculate.setOnClickListener(v -> calculateBMI());
    }

    private void calculateBMI() {
        String heightStr = inputHeight.getText().toString().trim();
        String weightStr = inputWeight.getText().toString().trim();

        if (heightStr.isEmpty() || weightStr.isEmpty()) {
            Toast.makeText(this, "Please enter both values", Toast.LENGTH_SHORT).show();
            return;
        }

        double heightDouble = Double.parseDouble(heightStr);
        double weightDouble= Double.parseDouble(weightStr);

        if(heightDouble ==0 || weightDouble ==0 || heightDouble < 0 || weightDouble < 0 || heightDouble > 3 || weightDouble > 300)
        {
            Toast.makeText(this, "Please enter valid values", Toast.LENGTH_SHORT).show();
            return;
        }

        double bmi = weightDouble / (heightDouble * heightDouble);

        String category;
        if (bmi < 18.5) {
            category = "Underweight";
            outputCategory.setTextColor(Color.parseColor("#F54927"));
        }
        else if (bmi < 25) {
            category = "Normal weight";
            outputCategory.setTextColor(Color.parseColor("#27F549"));
        }
        else if (bmi < 30){
            category = "Overweight";
            outputCategory.setTextColor(Color.parseColor("#F54927"));
        }
        else {
            category = "Obese";
            outputCategory.setTextColor(Color.parseColor("#F54927"));
        }

        outputBmiValue.setText(String.format("BMI: " + String.format("%.2f", bmi)));
        outputCategory.setText("Category: " + category);
    }
}