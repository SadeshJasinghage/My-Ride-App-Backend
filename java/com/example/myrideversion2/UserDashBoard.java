package com.example.myrideversion2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserDashBoard extends AppCompatActivity {

    ImageView imgMenu;
    FirebaseAuth mAuth;

    ProgressBar progressBar;
    FirebaseUser user;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dash_board);

        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.logoutProgressBar);
        imgMenu= findViewById(R.id.drawer_menu);
        user = mAuth.getCurrentUser();

        if (user == null) {
            // If the user is not logged in, redirect to MainActivity
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

        imgMenu.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE); // Set the progress bar visible

            FirebaseAuth.getInstance().signOut();

            // You might want to add an onCompleteListener for better handling
            mAuth.signOut();

            Toast.makeText(UserDashBoard.this, "Logged Out", Toast.LENGTH_SHORT).show();

            // Redirect to MainActivity
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();


        });

        CardView crdBooking = findViewById(R.id.cardBooking); // Replace R.id.myCardView with your actual CardView ID
        if (crdBooking != null) {
            crdBooking.setOnClickListener(v -> {

                Intent intent = new Intent(getApplicationContext(), BookingPage1.class);
                startActivity(intent);
                finish();
                // Your click handling code
            });
        }


        CardView crdRideNow = findViewById(R.id.cardRideNow); // Replace R.id.myCardView with your actual CardView ID
        if (crdRideNow != null) {
                crdRideNow.setOnClickListener(v -> {
                Intent intent = new Intent(getApplicationContext(), RideNowPage1Activity.class);
                startActivity(intent);
                finish();
                // Your click handling code
            });
        }


        CardView crdActivity = findViewById(R.id.cardActivity); // Replace R.id.myCardView with your actual CardView ID
        if (crdActivity != null) {
            crdActivity.setOnClickListener(v -> {
                Intent intent = new Intent(getApplicationContext(), Activity_history_page1.class);
                startActivity(intent);
                finish();
                // Your click handling code
            });
        }




    }
}
