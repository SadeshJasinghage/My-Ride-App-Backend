package com.example.myrideversion2;

public class Ride_Data_item {

    String pickupStation;
    String droppingStation;
    String bike;

    // No-argument constructor (required by Firebase)
    public Ride_Data_item() {
        // Initialize fields with default values if needed

        this.pickupStation = "";
        this.droppingStation = "";
        this.bike = "";
    }



        public Ride_Data_item(String pickupStation, String droppingStation, String bike) {
        this.pickupStation = pickupStation;
        this.droppingStation = droppingStation;
        this.bike = bike;
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
