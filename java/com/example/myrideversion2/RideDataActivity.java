package com.example.myrideversion2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RideDataActivity extends AppCompatActivity {

    ImageView imgArrow;

    DatabaseReference databaseReference;
    Ride_Data_Adupter rideDataAdupter;
    List<Ride_Data_item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ride_data);

        imgArrow = findViewById(R.id.backArrow_ride_data);


        imgArrow.setOnClickListener(v -> {

            // Redirect to  User dash board
            Intent intent = new Intent(getApplicationContext(), Activity_history_page1.class);
            startActivity(intent);
            finish();


        });

        RecyclerView recyclerView = findViewById(R.id.RideData_RecyclerView);
        databaseReference = FirebaseDatabase.getInstance().getReference("Bookings");


        //create list of item here
        items = new ArrayList<>();
        rideDataAdupter = new Ride_Data_Adupter(this, items);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(rideDataAdupter);




        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Ride_Data_item  item = dataSnapshot.getValue(Ride_Data_item.class);
                    items.add(item);

                }

                rideDataAdupter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}