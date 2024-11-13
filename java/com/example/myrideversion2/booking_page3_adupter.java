package com.example.myrideversion2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class booking_page3_adupter extends RecyclerView.Adapter <booking_page3_view_holder> {

    Context context;
    List<booking_page3_item> items;

    public booking_page3_adupter(Context context, List<booking_page3_item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public booking_page3_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new booking_page3_view_holder(LayoutInflater.from(context).inflate(R.layout.booking_page3_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull booking_page3_view_holder holder, int position) {
        holder.selectedDate.setText(items.get(position).getSelectedDate());
        holder.selectedTime.setText(items.get(position).getSelectedTime());
        holder.pickupStation.setText(items.get(position).getPickupStation());
        holder.droppingStation.setText(items.get(position).getDroppingStation());
        holder.bike.setText(items.get(position).getBike());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
