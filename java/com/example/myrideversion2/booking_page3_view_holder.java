package com.example.myrideversion2;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class booking_page3_view_holder extends RecyclerView.ViewHolder {

    TextView selectedDate,selectedTime,pickupStation,droppingStation,bike;


    public booking_page3_view_holder(@NonNull View itemView) {
        super(itemView);

        selectedDate = itemView.findViewById(R.id.item_view_date);
        selectedTime = itemView.findViewById(R.id.item_view_time);
        pickupStation = itemView.findViewById(R.id.item_view_pickupStation);
        droppingStation = itemView.findViewById(R.id.item_view_droppingStation);
        bike = itemView.findViewById(R.id.item_view_bike);

    }







}
