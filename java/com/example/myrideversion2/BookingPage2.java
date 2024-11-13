package com.example.myrideversion2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;


import android.text.format.DateFormat;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;



import com.google.android.material.button.MaterialButton;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import static com.example.myrideversion2.DataProvider.*;


public class BookingPage2 extends AppCompatActivity {


    AutoCompleteTextView autoCompletePickupPlace;
    AutoCompleteTextView autoCompleteDroppingPlace;
    AutoCompleteTextView autoCompleteBike;


    private TextView text1;
    private TextView text2;





    ArrayAdapter<String> adapterItem1;
    ArrayAdapter<String> adapterItem2;
    ArrayAdapter<String> adapterItem3;

    MaterialButton materialButton1;
    MaterialButton materialButton2;

    TextView textView3;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;




    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_page2);

        // Use arrays from DataProvider
        adapterItem1 = new ArrayAdapter<>(this, R.layout.list_item, item1);
        adapterItem2 = new ArrayAdapter<>(this, R.layout.list_item, item2);
        adapterItem3 = new ArrayAdapter<>(this, R.layout.list_item, item3);

        text1 = findViewById(R.id.pickup_date);
        text2 = findViewById(R.id.pickup_time);
        textView3 = findViewById(R.id.gotoMyBookings);
        materialButton1 = findViewById(R.id.confirm_button_page2);
        materialButton2 = findViewById(R.id.back_booking_page2);
        autoCompleteBike = findViewById(R.id.auto_complete_bike);
        autoCompletePickupPlace = findViewById(R.id.auto_complete_pickup_place);
        autoCompleteDroppingPlace = findViewById(R.id.auto_complete_drop_place);


        // Initialize Firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Bookings");
        FirebaseApp.initializeApp(this);


        materialButton2.setOnClickListener(view -> {

            Intent intent = new Intent(getApplicationContext(), UserDashBoard.class);
            startActivity(intent);
            finish();
        });

        textView3.setOnClickListener(view -> {

            Intent intent = new Intent(getApplicationContext(), BookingPage3Activity.class);
            startActivity(intent);
            finish();

        });




        materialButton1.setOnClickListener(view -> {

            try {


                // Create a Data object with selected values
                String selectedDate = text1.getText().toString();
                String selectedTime = text2.getText().toString();
                String pickupStation = autoCompletePickupPlace.getText().toString();
                String droppingStation = autoCompleteDroppingPlace.getText().toString();
                String bike = autoCompleteBike.getText().toString();


                // Check if any field is empty
                if (selectedDate.isEmpty() || selectedTime.isEmpty() || pickupStation.isEmpty() || droppingStation.isEmpty() || bike.isEmpty()) {
                    Toast.makeText(BookingPage2.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Initialize Firebase
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("Bookings");

                // Push the Data object to the database
                String key = databaseReference.push().getKey();
                booking_page3_item bookingData = new booking_page3_item(selectedDate, selectedTime,pickupStation, droppingStation, bike);
                assert key != null;
                databaseReference.child(key).setValue(bookingData).addOnSuccessListener(unused -> Toast.makeText(BookingPage2.this, "your Booking is added Successfully..." , Toast.LENGTH_SHORT).show());

                Intent intent = new Intent(getApplicationContext(), BookingPage3Activity.class);
                startActivity(intent);
                finish();

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(BookingPage2.this, "An error occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });





        //Auto Complete text View adopting item from list and set selected item to text


        adapterItem1 = new ArrayAdapter<>(this, R.layout.list_item, item1);
        adapterItem2 = new ArrayAdapter<>(this, R.layout.list_item, item2);
        adapterItem3 = new ArrayAdapter<>(this, R.layout.list_item, item3);



        autoCompletePickupPlace.setAdapter(adapterItem1);
        autoCompletePickupPlace.setOnItemClickListener((adapterView, view, i, l) -> {
            String item = adapterView.getItemAtPosition(i).toString();
            Toast.makeText(BookingPage2.this,"Pickup Place:" + item, Toast.LENGTH_SHORT).show();
        });


        autoCompleteDroppingPlace.setAdapter(adapterItem2);
        autoCompleteDroppingPlace.setOnItemClickListener((adapterView, view, i, l) -> {
            String item = adapterView.getItemAtPosition(i).toString();
            Toast.makeText(BookingPage2.this,"Dropping Place:" + item, Toast.LENGTH_SHORT).show();
        });


        autoCompleteBike.setAdapter(adapterItem3);
        autoCompleteBike.setOnItemClickListener((adapterView, view, i, l) -> {
            String item = adapterView.getItemAtPosition(i).toString();
            Toast.makeText(BookingPage2.this,"Selected Bike:" + item, Toast.LENGTH_SHORT).show();
        });


        // Set date and time into text view

        text1.setOnClickListener(view -> setDate());
        text2.setOnClickListener(view -> setTime());


    }

    // Call set date & set time methods

    private void setDate() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (datePicker, year1, month1, day1) -> {
            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(Calendar.YEAR, year1);
            calendar1.set(Calendar.MONTH, month1);
            calendar1.set(Calendar.DATE, day1);

            CharSequence charSequence = DateFormat.format("EEEE dd MMM yyy", calendar1);
            text1.setText(charSequence);

        }, year, month, day);
        datePickerDialog.show();
    }

    private void setTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int min = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, hour1, min1) -> {
            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(Calendar.HOUR, hour1);
            calendar1.set(Calendar.MINUTE, min1);

            CharSequence charSequence = DateFormat.format("hh:mm", calendar1);
            text2.setText(charSequence);

        }, hour, min, true);
        timePickerDialog.show();
    }
}