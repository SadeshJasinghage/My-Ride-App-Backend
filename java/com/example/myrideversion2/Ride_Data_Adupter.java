package com.example.myrideversion2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Ride_Data_Adupter extends RecyclerView.Adapter <Ride_Data_ViewHolder> {
    Context context;
    List<Ride_Data_item> items;

    public Ride_Data_Adupter(Context context, List<Ride_Data_item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public Ride_Data_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Ride_Data_ViewHolder(LayoutInflater.from(context).inflate(R.layout.ride_data_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Ride_Data_ViewHolder holder, int position) {

        holder.pickupStation.setText(items.get(position).getPickupStation());
        holder.droppingStation.setText(items.get(position).getDroppingStation());
        holder.bike.setText(items.get(position).getBike());

    }


    @Override
    public int getItemCount() {
        return items.size();
    }
}
