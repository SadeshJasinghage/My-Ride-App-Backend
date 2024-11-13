package com.example.myrideversion2;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import static com.example.myrideversion2.DataProvider.*;
import android.content.ActivityNotFoundException;

public class RideNowPage1Activity extends AppCompatActivity {


    ArrayAdapter<String> adapterItem1;
    ArrayAdapter<String> adapterItem2;
    ArrayAdapter<String> adapterItem3;


    AutoCompleteTextView autoCompletePickupPlace_rideNow;
    AutoCompleteTextView autoCompleteDroppingPlace_rideNow;
    AutoCompleteTextView autoCompleteBike_rideNow;


    MaterialButton materialButton1_rideNow;
    MaterialButton materialButton2_rideNow;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;



    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ride_now_page1);

        // Use arrays from DataProvider
        adapterItem1 = new ArrayAdapter<>(this, R.layout.list_item, item1);
        adapterItem2 = new ArrayAdapter<>(this, R.layout.list_item, item2);
        adapterItem3 = new ArrayAdapter<>(this, R.layout.list_item, item3);


        materialButton1_rideNow = findViewById(R.id.start_btn_rideNow);
        materialButton2_rideNow = findViewById(R.id.end_btn_rideNow);
        autoCompleteBike_rideNow = findViewById(R.id.ride_now_auto_complete_bike);
        autoCompletePickupPlace_rideNow = findViewById(R.id.ride_now_auto_complete_pickup_place);
        autoCompleteDroppingPlace_rideNow = findViewById(R.id.ride_now_auto_complete_drop_place);
        ImageView imgArrow = findViewById(R.id.backArrow_ride_now_page1);


        // Initialize Firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Rides");
        FirebaseApp.initializeApp(this);

        imgArrow.setOnClickListener(v -> {

            // Redirect to  User dash board
            Intent intent = new Intent(getApplicationContext(), UserDashBoard.class);
            startActivity(intent);
            finish();


        });



        materialButton2_rideNow.setOnClickListener(view -> {

            Intent intent = new Intent(getApplicationContext(), UserDashBoard.class);
            startActivity(intent);
            finish();
        });




        materialButton1_rideNow.setOnClickListener(view -> {

            try {


                // Create a Data object with selected values

                String pickupStation = autoCompletePickupPlace_rideNow.getText().toString();
                String droppingStation = autoCompleteDroppingPlace_rideNow.getText().toString();
                String bike = autoCompleteBike_rideNow.getText().toString();


                // Check if any field is empty
                if (pickupStation.isEmpty() || droppingStation.isEmpty() || bike.isEmpty()) {
                    Toast.makeText(RideNowPage1Activity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();


                }else {

                    Uri uri = Uri.parse("google.navigation:q=" + droppingStation + "&mode=d");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.google.android.apps.maps");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                    // Initialize Firebase
                    firebaseDatabase = FirebaseDatabase.getInstance();
                    databaseReference = firebaseDatabase.getReference("Rides");

                    // Push the Data object to the database
                    String key = databaseReference.push().getKey();
                    rideNow_page1_item rideData = new rideNow_page1_item( pickupStation, droppingStation, bike);
                    assert key != null;
                    databaseReference.child(key).setValue(rideData).addOnSuccessListener(unused -> Toast.makeText(RideNowPage1Activity.this, "Please wait...." , Toast.LENGTH_SHORT).show());


                }



            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(RideNowPage1Activity.this, "An error occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();

            }


        });





        //Auto Complete text View adopting item from list and set selected item to text


        adapterItem1 = new ArrayAdapter<>(this, R.layout.list_item, item1);
        adapterItem2 = new ArrayAdapter<>(this, R.layout.list_item, item2);
        adapterItem3 = new ArrayAdapter<>(this, R.layout.list_item, item3);



        autoCompletePickupPlace_rideNow.setAdapter(adapterItem1);
        autoCompletePickupPlace_rideNow.setOnItemClickListener((adapterView, view, i, l) -> {
            String item = adapterView.getItemAtPosition(i).toString();
            Toast.makeText(RideNowPage1Activity.this,"Pickup Place:" + item, Toast.LENGTH_SHORT).show();
        });


        autoCompleteDroppingPlace_rideNow.setAdapter(adapterItem2);
        autoCompleteDroppingPlace_rideNow.setOnItemClickListener((adapterView, view, i, l) -> {
            String item = adapterView.getItemAtPosition(i).toString();
            Toast.makeText(RideNowPage1Activity.this,"Dropping Place:" + item, Toast.LENGTH_SHORT).show();
        });


        autoCompleteBike_rideNow.setAdapter(adapterItem3);
        autoCompleteBike_rideNow.setOnItemClickListener((adapterView, view, i, l) -> {
            String item = adapterView.getItemAtPosition(i).toString();
            Toast.makeText(RideNowPage1Activity.this,"Selected Bike:" + item, Toast.LENGTH_SHORT).show();
        });



    }


}