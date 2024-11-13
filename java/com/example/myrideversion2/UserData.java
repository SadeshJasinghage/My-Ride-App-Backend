package com.example.myrideversion2;

public class UserData {
    public String Email;
    public String Password;
    public String FullName;
    public String Contact;

    // Default constructor required for calls to DataSnapshot.getValue(User.class)
    public UserData() {
    }

    public UserData(String email, String password, String fullName, String contact) {
        Email = email;
        Password = password;
        FullName = fullName;
        Contact = contact;
    }
}

