package com.example.myrideversion2;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;


public class Activity_history_page1 extends AppCompatActivity {

    LinearLayout linearLayout1;
    LinearLayout linearLayout2;
    ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_page1);

        linearLayout1 = findViewById(R.id.layout_bookingHistory);
        linearLayout2 = findViewById(R.id.layout_rideHistory);
        imageView = findViewById(R.id.backArrow_activity_hisPage1);

        linearLayout1.setOnClickListener(view -> {

            Intent intent = new Intent(getApplicationContext(), BookingData.class);
            startActivity(intent);
            finish();
        });

        linearLayout2.setOnClickListener(view -> {

            Intent intent = new Intent(getApplicationContext(), RideDataActivity.class);
            startActivity(intent);
            finish();
        });

        imageView.setOnClickListener(view -> {

            Intent intent = new Intent(getApplicationContext(), UserDashBoard.class);
            startActivity(intent);
            finish();
        });





    }

}
