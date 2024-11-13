package com.example.myrideversion2;

public class booking_page3_item {

    String selectedDate;
    String selectedTime;
    String pickupStation;
    String droppingStation;
    String bike;

    // No-argument constructor (required by Firebase)
    public booking_page3_item() {
        // Initialize fields with default values if needed
        this.selectedDate = "";
        this.selectedTime = "";
        this.pickupStation = "";
        this.droppingStation = "";
        this.bike = "";
    }
    


    public booking_page3_item(String date, String time, String pickupStation, String droppingStation, String bike) {
        this.selectedDate = date;
        this.selectedTime = time;
        this.pickupStation = pickupStation;
        this.droppingStation = droppingStation;
        this.bike = bike;
    }

    public String getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }

    public String getSelectedTime() {
        return selectedTime;
    }

    public void setSelectedTime(String selectedTime) {
        this.selectedTime = selectedTime;
    }

    public String getPickupStation() {
        return pickupStation;
    }

    public void setPickupStation(String pickupStation) {
        this.pickupStation = pickupStation;
    }

    public String getDroppingStation() {
        return droppingStation;
    }

    public void setDroppingStation(String droppingStation) {
        this.droppingStation = droppingStation;
    }

    public String getBike() {
        return bike;
    }

    public void setBike(String bike) {
        this.bike = bike;
    }
}
