package com.example.whereru.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whereru.R;
import com.example.whereru.model.Bus;
import com.example.whereru.view.BusStopViewHolder;

public class StopsDataAdapter extends RecyclerView.Adapter<BusStopViewHolder> {

    private Bus mBus;

    public StopsDataAdapter(Bus bus) {
        mBus = bus;
    }

    @NonNull
    @Override
    public BusStopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.stops_item, parent, false);
        return new BusStopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BusStopViewHolder holder, int position) {
        holder.getStopAddress().setText(mBus.getStopList().get(position).getAddress());
        holder.getStopTime().setText(mBus.getStopList().get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return mBus.getStopList().size();
    }
}
