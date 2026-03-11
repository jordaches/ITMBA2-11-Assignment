package com.eduv4810074.bookingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Summary extends AppCompatActivity {

    TextView stylist, services, returning, totalCost, customerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_summary);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        stylist = findViewById(R.id.stylist);
        services = findViewById(R.id.services);
        returning = findViewById(R.id.returning);
        totalCost = findViewById(R.id.totalCost);
        customerName = findViewById(R.id.customerName);

        Intent i = getIntent();
        stylist.setText(getIntent().getStringExtra("stylist"));
        services.setText(String.join(getIntent().getStringExtra("services"), ","));
        returning.setText(getIntent().getStringExtra("returning"));
        totalCost.setText((int) getIntent().getDoubleExtra("totalCost", 0));
        customerName.setText(getIntent().getStringExtra("customerName"));
    }


}