package com.example.testlistview;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button showButton;
    Button clearButton;
    ListView listView;


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

        showButton = findViewById(R.id.listbutton);
        clearButton = findViewById(R.id.clearButton);
        listView = findViewById(R.id.listView);



        showButton.setOnClickListener(v -> showNumbers());

        clearButton.setOnClickListener(v -> clearNumbers());



    }

    private void clearNumbers() {
        listView.setAdapter(null);
    }

    private void showNumbers() {
        ArrayList<Integer> numbers = new ArrayList<>();
        for(int i = 1; i <= 35; i++){
            numbers.add(i);
        }

        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, numbers));

    }


}