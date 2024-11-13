package com.example.myrideversion2;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Ride_Data_ViewHolder extends RecyclerView.ViewHolder {

    TextView pickupStation,droppingStation,bike;


    public Ride_Data_ViewHolder(@NonNull View itemView) {
        super(itemView);

        pickupStation = itemView.findViewById(R.id.RideData_pickupPlace);
        droppingStation = itemView.findViewById(R.id.RideData_DroppedPlace);
        bike = itemView.findViewById(R.id.RideData_BikeId);
    }
}
