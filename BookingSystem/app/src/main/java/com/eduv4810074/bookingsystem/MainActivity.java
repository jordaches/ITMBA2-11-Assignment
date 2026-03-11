package com.eduv4810074.bookingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText fullName, preferredStylist;
    CheckBox haircut, manicure, massage, returningCustomer;
    Button confirmBooking;
    String servicesSelected = "";
    double cost = 0;

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

        fullName = findViewById(R.id.fullName);
        preferredStylist = findViewById(R.id.editTextTextSylist);
        haircut = findViewById(R.id.checkBoxHaircut);
        manicure = findViewById(R.id.checkBoxManicure);
        massage = findViewById(R.id.checkBoxMassage);
        returningCustomer = findViewById(R.id.checkBoxReturningCustomer);
        confirmBooking = findViewById(R.id.btnconfirmBooking);

        final int PRICE_HAIRCUT = 150;
        final int PRICE_MANICURE = 120;
        final int PRICE_MASSAGE = 2500;

        if (haircut.isChecked()) {
            cost += PRICE_HAIRCUT;
            servicesSelected += " Haircut";
        }
        if (manicure.isChecked()) {
            cost += PRICE_MANICURE;
            servicesSelected += " Manicure";
        }
        if (massage.isChecked()) {
            cost += PRICE_MASSAGE;
            servicesSelected += " Massage";
        }
        cost = returningCustomer.isChecked() ? cost * 0.9 : cost;

        confirmBooking.setOnClickListener(v -> {
            String name = fullName.getText().toString().trim();
            String preferred = preferredStylist.getText().toString().trim();

            if(!haircut.isChecked() && !manicure.isChecked() && !massage.isChecked()) {
                Toast.makeText(this, "Please select at least one service", Toast.LENGTH_SHORT).show();
                return;
            }
            if(name.isEmpty()) {
                fullName.setError("Please enter your name");
                fullName.requestFocus();
                return;
            }
            if(preferred.isEmpty()){
                preferredStylist.setError("Please enter your preferred stylist");
                preferredStylist.requestFocus();
                return;
            }

            Intent intent = new Intent(MainActivity.this, Summary.class);
            intent.putExtra("name", name);
            intent.putExtra("preferred", preferred);
            intent.putExtra("servicesSelected", servicesSelected);
            intent.putExtra("cost", cost);
            startActivity(intent);

        });

    }
}