package com.example.myapplication;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class MainActivity extends AppCompatActivity {

    private Button button1;
    private EditText editText1;
    private TextView textView1;
    private TextView textView2;

    private SwitchCompat sum;
    private EditText number1;
    private EditText number2;
    private TextView total;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        button1 = (Button) findViewById(R.id.button1);
        editText1 = (EditText) findViewById(R.id.editTextText);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);


        sum = (SwitchCompat) findViewById(R.id.sum);
        number1 = (EditText) findViewById(R.id.number1);
        number2 = (EditText) findViewById(R.id.number2);
        total = (TextView) findViewById(R.id.total);




        /*button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText1.getText().toString();
                textView1.setText("Hello " + name + "!");
            }
        });*/

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText1.getText().toString();
                String nameFormatted = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();

                textView1.setText("Hello " + nameFormatted + "!");
                textView2.setText(nameFormatted);
            }
        });

        number1.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sum.setChecked(false);
            }
        });

        number2.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sum.setChecked(false);
            }
        });

        sum.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (number1.getText().toString().isEmpty() || number2.getText().toString().isEmpty()) {
                total.setText("Please supply a value for both numbers");
                return;

            int num1 = Integer.parseInt(number1.getText().toString());
            int num2 = Integer.parseInt(number2.getText().toString());
            int sum = num1+num2;
            if (isChecked) {
                total.setText(String.format("The answer is: %s", sum));
            } else {
                total.setText("");
            }

        });

    }
}
