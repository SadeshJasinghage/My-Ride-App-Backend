package com.example.myrideversion2;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BookingData extends AppCompatActivity {
    ImageView imgArrow;

    DatabaseReference databaseReference;
    booking_page3_adupter bookingPage3Adupter;
    List<booking_page3_item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_data);

        imgArrow = findViewById(R.id.backArrow_booking_data);


        imgArrow.setOnClickListener(v -> {

            // Redirect to  User dash board
            Intent intent = new Intent(getApplicationContext(), Activity_history_page1.class);
            startActivity(intent);
            finish();


        });

        RecyclerView recyclerView = findViewById(R.id.RecyclerView);
        databaseReference = FirebaseDatabase.getInstance().getReference("Bookings");


        //create list of item here
        items = new ArrayList<>();
        bookingPage3Adupter = new booking_page3_adupter(this, items);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(bookingPage3Adupter);




        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    booking_page3_item  item = dataSnapshot.getValue(booking_page3_item.class);
                    items.add(item);

                }

                bookingPage3Adupter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
