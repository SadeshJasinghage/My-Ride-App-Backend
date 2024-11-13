package com.example.myrideversion2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Registration extends AppCompatActivity {

    TextInputEditText editTextFullName, editTextEmail, editTextPassword, editTextReEnterPassword, editTextContact;
    Button RegBtn;
    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ProgressBar progressBar;
    TextView textView;

    @Override
    public void onStart() {
        super.onStart();
        //initialize firebase
        FirebaseAuth.getInstance();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), UserDashBoard.class);
            startActivity(intent);
            finish();
        }
    }



    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth= FirebaseAuth.getInstance();

        editTextFullName = findViewById(R.id.signup_full_name);
        editTextEmail = findViewById(R.id.signup_email);
        editTextPassword = findViewById(R.id.signup_password);
        editTextReEnterPassword = findViewById(R.id.signup_retype_password);
        editTextContact = findViewById(R.id.signup_contact_number);

        progressBar = findViewById(R.id.regProgressBar);

        RegBtn = findViewById(R.id.btnRegister);
        textView = findViewById(R.id.txt_backto_login);

        textView.setOnClickListener(v -> {

            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();

        });



// ... (existing code)

        RegBtn.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            String email = String.valueOf(editTextEmail.getText());
            String password = String.valueOf(editTextPassword.getText());
            String rePassword = String.valueOf(editTextReEnterPassword.getText());
            String fullName = String.valueOf(editTextFullName.getText());
            String contact = String.valueOf(editTextContact.getText());

            // Initialize Firebase
            firebaseDatabase = FirebaseDatabase.getInstance();
            //databaseReference = firebaseDatabase.getReference("Bookings");
            FirebaseApp.initializeApp(this);

            //Store data in RealTime database

            try {


                // Create a Data object with selected values
                String Email = Objects.requireNonNull(editTextEmail.getText()).toString();
                String Password = Objects.requireNonNull(editTextPassword.getText()).toString();
                String FullName = Objects.requireNonNull(editTextFullName.getText()).toString();
                String Contact = Objects.requireNonNull(editTextContact.getText()).toString();


                // Check if any field is empty
                if (Email.isEmpty() || Password.isEmpty() || FullName.isEmpty() || Contact.isEmpty()) {
                    Toast.makeText(Registration.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Initialize Firebase
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("User Information");

                // Push the Data object to the database
                String key = databaseReference.push().getKey();
                UserData userData = new UserData(email, password, fullName, contact );
                assert key != null;
                databaseReference.child(key).setValue(userData);



            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(Registration.this, "An error occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(fullName) || TextUtils.isEmpty(contact)) {
                Toast.makeText(Registration.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                return;
            }

            if (contact.length() < 10) {
                Toast.makeText(Registration.this, "Incorrect contact", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                return;
            }

            if (!rePassword.equals(password)) {
                Toast.makeText(Registration.this, "ReEntered password is incorrect", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                return;
            }

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Toast.makeText(Registration.this, "Successfully Authenticated.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Registration.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    });
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();

        });






    }
}