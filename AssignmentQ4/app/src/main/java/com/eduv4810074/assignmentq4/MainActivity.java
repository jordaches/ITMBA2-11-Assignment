package com.eduv4810074.assignmentq4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputText1, inputText2, inputText3;
    private Button buttonManipulate,buttonReset;
    private LinearLayout resultsLayout;
    private TextView textViewOriginal,textViewManipulated,textViewAcronym;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText1 = findViewById(R.id.input_word1);
        inputText2 = findViewById(R.id.input_word2);
        inputText3 = findViewById(R.id.input_word3);
        buttonManipulate = findViewById(R.id.button_Manipulate);
        buttonReset = findViewById(R.id.button_Reset);
        resultsLayout = findViewById(R.id.resultsLayout);
        textViewOriginal = findViewById(R.id.textViewOriginal);
        textViewManipulated = findViewById(R.id.textViewManipulated);
        textViewAcronym = findViewById(R.id.textViewAcronym);

        resultsLayout.setVisibility(View.GONE);
        buttonReset.setVisibility(View.GONE);

        buttonManipulate.setOnClickListener(v-> {
                manipulateWords();
        });

        // Reset button click listener
        buttonReset.setOnClickListener(v -> {
                resetFields();
        });
    }

    private void manipulateWords() {

        String word1 = inputText1.getText().toString().trim();
        String word2 = inputText2.getText().toString().trim();
        String word3 = inputText3.getText().toString().trim();

        if (word1.isEmpty() || word2.isEmpty() || word3.isEmpty()) {
            return;
        }

        String original = word1 + ", " + word2 + ", " + word3;

        String manipulated1 = word1.toUpperCase();
        String manipulated2 = word2.toLowerCase();
        String manipulated3 = new StringBuilder(word3).reverse().toString();
        String manipulated = manipulated1 + ", " + manipulated2 + ", " + manipulated3;

        String acronym = "";
        if (!word1.isEmpty()) {
            acronym += Character.toUpperCase(word1.charAt(0));
        }
        if (!word2.isEmpty()) {
            acronym += Character.toLowerCase(word2.charAt(0));
        }
        if (!word3.isEmpty()) {
            acronym += Character.toLowerCase(word3.charAt(0));
        }

        textViewOriginal.setText("Original: " + original);
        textViewManipulated.setText("Manipulated: " + manipulated);
        textViewAcronym.setText("Acronym: " + acronym);

        resultsLayout.setVisibility(View.VISIBLE);
        buttonReset.setVisibility(View.VISIBLE);
        buttonManipulate.setVisibility(View.GONE);
    }

    private void resetFields() {
        inputText1.setText(null);
        inputText2.setText(null);
        inputText3.setText(null);

        resultsLayout.setVisibility(View.GONE);
        buttonReset.setVisibility(View.GONE);
        buttonManipulate.setVisibility(View.VISIBLE);
    }
}