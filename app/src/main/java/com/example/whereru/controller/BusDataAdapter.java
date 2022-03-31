package com.example.whereru.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whereru.R;
import com.example.whereru.model.Bus;
import com.example.whereru.view.BusItemViewHolder;

import java.util.List;

public class BusDataAdapter extends RecyclerView.Adapter<BusItemViewHolder> {

    private List<Bus> mBuses;
    private Context mContext;

    public BusDataAdapter(List<Bus> busList, Context context) {
        mBuses = busList;
        mContext = context;
    }

    @NonNull
    @Override
    public BusItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bus_item, parent, false);
        return new BusItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BusItemViewHolder holder, int position) {
        Bus bus = mBuses.get(position);

        String busNumber = bus.getBusNumber()+"";
        String busDriverName = bus.getBusDriverName();
        String busDriverPhone = bus.getBusDriverPhone();

        holder.getBusNumberTextView().setText(busNumber);
        holder.getDriverNameTextView().setText(busDriverName);
        holder.getDriverPhoneTextView().setText(busDriverPhone);
    }

    @Override
    public int getItemCount() {
        return mBuses.size();
    }
}
