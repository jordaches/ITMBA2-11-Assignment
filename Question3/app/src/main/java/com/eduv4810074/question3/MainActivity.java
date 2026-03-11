package com.eduv4810074.question3;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //Question 3.2
    EditText editBookName,editPrice,editAge;
    RadioGroup radioGroupStatus;
    Button buttonReceipt, buttonExit;

    Toast t;

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

        editBookName = findViewById(R.id.editBookName);
        editPrice = findViewById(R.id.editPrice);
        editAge = findViewById(R.id.editAge);
        radioGroupStatus = findViewById(R.id.radioGroupStatus);
        buttonReceipt = findViewById(R.id.buttonReceipt);
        buttonExit= findViewById(R.id.buttonExit);

        buttonReceipt.setOnClickListener(v -> {

            //Question 3.3

            String bookName = editBookName.getText().toString().trim();
            double price;
            try
            {
                price = Double.parseDouble(editPrice.getText().toString());
            }
            catch (Exception e)
            {
                editPrice.setError("Please enter a valid price");
                return;
            }

            int age;
            try
            {
                age = Integer.parseInt(editAge.getText().toString());
            } catch (Exception e) {
                editAge.setError("Please enter a valid age");
                return;
            }


            int selectedId = radioGroupStatus.getCheckedRadioButtonId();

            if(bookName.isEmpty()){
                editBookName.setError("Please enter a book name");
                return;
            }
            if(selectedId == -1){
                return;
            }


            if(radioGroupStatus.getCheckedRadioButtonId() == -1){
                t.makeText(MainActivity.this, "Please select a status", Toast.LENGTH_SHORT).show();
                return;
            }

            int selectedStatus = radioGroupStatus.getCheckedRadioButtonId();
            RadioButton rbStatus= findViewById(selectedStatus);
            String studentStatus = rbStatus.getText().toString();

            Intent intent = new Intent(MainActivity.this, ReceiptActivity.class);
            intent.putExtra("bookName", bookName);
            intent.putExtra("price", price);
            intent.putExtra("age", age);
            intent.putExtra("status", studentStatus);
            startActivity(intent);

            //Empty out when going back to main activity
            editBookName.setText("");
            editPrice.setText("");
            editAge.setText("");
        });
        buttonExit.setOnClickListener(v-> finish());
    }

}



