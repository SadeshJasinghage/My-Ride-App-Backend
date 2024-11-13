package com.example.myrideversion2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class BookingDetail extends AppCompatActivity {
    TextView date,time,droppingPlace,pickupPlace,bike;
    MaterialButton materialButton;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_detail);

        date = findViewById(R.id.pickupDate);
        time = findViewById(R.id.pickupTime);
        droppingPlace = findViewById(R.id.DroppedPlace);
        pickupPlace = findViewById(R.id.pickupPlace);
        bike = findViewById(R.id.BikeId);
        materialButton = findViewById(R.id.cancel_booking_btn);





        Bundle bundle= getIntent().getExtras();
        if(bundle != null){
            date.setText(bundle.getString("selectedDate"));
            time.setText(bundle.getString("selectedTime"));
            pickupPlace.setText(bundle.getString("pickupStation"));
            droppingPlace.setText(bundle.getString("droppingStation"));
            bike.setText(bundle.getString("bike"));

            // Generate a unique booking ID
            String bookingId = UUID.randomUUID().toString();

            // Create a new Data object with booking details
            booking_page3_item newBooking = new booking_page3_item(

                    bundle.getString("selectedDate"),
                    bundle.getString("selectedTime"),
                    bundle.getString("pickupStation"),
                    bundle.getString("droppingStation"),
                    bundle.getString("bike")
            );
            // Save the new booking to the database
            FirebaseDatabase.getInstance().getReference("Bookings").child(bookingId).setValue(newBooking);

        }
    }
}