package com.eduv4810074.question3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ReceiptActivity extends AppCompatActivity {

    TextView bookName, age, price, studentStatus;
    ConstraintLayout background;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_receipt);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //3.5

        //Data has already been validated in MainActivity, so no need to validate again here
        Intent intent = getIntent();

        bookName = findViewById(R.id.bookName);
        age = findViewById(R.id.age);
        price = findViewById(R.id.price);
        studentStatus = findViewById(R.id.studentStatus);

        bookName.setText("Book Name: " + intent.getStringExtra("bookName").trim());
        int ageInt = intent.getIntExtra("age", 0);
        age.setText("Age: " + ageInt);
        price.setText("Price: " + intent.getDoubleExtra("price", 0));
        String student = intent.getStringExtra("status").trim();
        studentStatus.setText("Student Status: " + student);

        background = findViewById(R.id.main);
        if(student.equals("Student") && ageInt < 18)
        {
            background.setBackgroundColor(Color.parseColor("#A4C639"));
        }
        else if (student.equals("Student") && (ageInt >= 18 && ageInt <= 20))
        {
            background.setBackgroundColor(Color.parseColor("#FFA500"));
        }
        else
        {
            background.setBackgroundColor(Color.parseColor("#FF6961"));
        }
    }
}