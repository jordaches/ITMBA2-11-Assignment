package com.eduv4810074.assignmentq2;

import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private String PREFS_NAME = "DrinkingWaterPrefs";
    private EditText waterAmount;
    private Button buttonAdd ,reset_button;
    private TextView totalWater, outputMotiviation;
    private int totalWaterConsumed = 0, totalWaterConsumedInPref ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Using shared prefs to determine if we are on the same day.
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        totalWaterConsumedInPref = prefs.getInt("totalWaterConsumed", 0);
        long today = prefs.getLong("date",0);

        if(today != getDatMill())
        {
            totalWaterConsumed = 0;
        }
        else {
            totalWaterConsumed = totalWaterConsumedInPref;
        }

        waterAmount = findViewById(R.id.input_waterAmount);
        buttonAdd = findViewById(R.id.add_button);
        totalWater = findViewById(R.id.output_totalWater_textView);
        outputMotiviation = findViewById(R.id.output_motivation_textView);
        outputMotiviation.setText(null);
        reset_button = findViewById(R.id.reset_button);
        totalWater.setText("Total water consumed: " + totalWaterConsumed + " ml");

        buttonAdd.setOnClickListener(v -> {
                addWaterToTotal();
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("totalWaterConsumed", totalWaterConsumed);
                editor.putLong("date", getDatMill());
                editor.apply();
        });

        reset_button.setOnClickListener(v->{
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("totalWaterConsumed", 0);
            editor.putLong("date", 0);
            editor.apply();
            totalWaterConsumed = 0;
            totalWater.setText("Total water consumed: " + totalWaterConsumed + " ml");

        });

    }

    private void addWaterToTotal() {
        String waterAmountText = waterAmount.getText().toString();

        if (!waterAmountText.isEmpty()) {
            int waterAmountVal = Integer.parseInt(waterAmountText);
            if(waterAmountVal <= 0) {
                Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show();
                return;
            }
            totalWaterConsumed += waterAmountVal;
            totalWater.setText("Total water consumed: " + totalWaterConsumed + " ml");
            setOutput(totalWaterConsumed);
            waterAmount.setText("");
        } else {
            Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show();
        }
    }

    private void setOutput(int totalAmount) {
        String message;
        if (totalAmount < 1500) {
            message = "Drink more water";
        } else if (totalAmount > 1500 && totalAmount < 2500) {
            message = "Good Job! You're staying hydrated";
        } else {
            message = "You're Drinking enough water";
        }
        outputMotiviation.setText(message);
    }

    private long getDatMill() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }
}