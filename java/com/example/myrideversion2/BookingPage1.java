package com.example.myrideversion2;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;


import com.google.android.material.button.MaterialButton;

public class BookingPage1 extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_page1);
        ImageView imgArrow = findViewById(R.id.backArrow);


        imgArrow.setOnClickListener(v -> {

            // Redirect to  User dash board
            Intent intent = new Intent(getApplicationContext(), UserDashBoard.class);
            startActivity(intent);
            finish();


        });


        MaterialButton materialButton = findViewById(R.id.Reserve_Ride_button); // Replace R.id.myCardView with your actual CardView ID
        if (materialButton != null) {
            materialButton.setOnClickListener(v -> {
                Intent intent = new Intent(getApplicationContext(), BookingPage2.class);
                startActivity(intent);
                finish();

            });
        }
    }
}