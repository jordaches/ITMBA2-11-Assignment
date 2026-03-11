package com.eduv4810074.assignmentq1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText studentName,englishScore,mathScore,scienceScore;
    Button calculate;
    Character grade;
    Double average;
    TextView outputName, averageOutput, outputGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        outputName = findViewById(R.id.name_output_textView);
        averageOutput = findViewById(R.id.avg_output_textView);
        outputGrade = findViewById(R.id.grade_output_textView);

        studentName = findViewById(R.id.input_studentName);
        englishScore = findViewById(R.id.input_englishScore);
        mathScore = findViewById(R.id.input_mathScore);
        scienceScore = findViewById(R.id.input_scienceScore);
        calculate = findViewById(R.id.button_calculate);

        outputName.setText(null);
        averageOutput.setText(null);
        outputGrade.setText(null);

        calculate.setOnClickListener(v -> {
            //Empty fields incase an incorrect value was first enter and clicked
            outputName.setText(null);
            averageOutput.setText(null);
            outputGrade.setText(null);

            if (studentName.getText().toString().isEmpty() || englishScore.getText().toString().isEmpty() || mathScore.getText().toString().isEmpty() || scienceScore.getText().toString().isEmpty()) {
                outputName.setText("Please enter all fields");
                return;
            }

            String studentNamestr = studentName.getText().toString();

            if (!studentNamestr.matches("^[a-zA-Z\\s]+$")) {
                outputName.setText("Name can only contain letters and spaces");
                return;
            }

            double eScore = Double.parseDouble(englishScore.getText().toString());
            double mScore = Double.parseDouble(mathScore.getText().toString());
            double sScore = Double.parseDouble(scienceScore.getText().toString());

            if(eScore > 100 || eScore < 0 || mScore > 100 || mScore < 0 || sScore > 100 || sScore < 0){
                outputName.setText("Please enter a valid score");
                return;
            }

            average = (eScore + mScore + sScore) / 3;
            grade = getGrade(average);

            outputName.setText(String.join("","Name: ",studentNamestr));
            outputGrade.setText(String.join("","Grade: ",grade.toString()));
            averageOutput.setText(String.join("","Average: ",String.format("%.2f", average)));

        });
    }

    private char getGrade(Double average) {
        if (average >= 90) { //First case, will not attempt others
            return 'A';
        } else if (average >= 80) {//Second case, will not attempt others
            return 'B';
        } else if (average >= 70) {//Third case, will not attempt others
            return 'C';
        } else {//Fourth case, final
            return 'F';
        }
    }
}