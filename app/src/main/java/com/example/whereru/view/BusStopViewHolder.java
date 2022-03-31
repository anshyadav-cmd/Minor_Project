package com.example.whereru.view;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whereru.R;

public class BusStopViewHolder extends RecyclerView.ViewHolder {

    public TextView stopAddress;
    public TextView stopTime;

    public BusStopViewHolder(@NonNull View itemView) {
        super(itemView);
        stopAddress = itemView.findViewById(R.id.address_textVeiw);
        stopTime = itemView.findViewById(R.id.stop_time_TextView);
    }

    public TextView getStopAddress() {
        return stopAddress;
    }

    public TextView getStopTime() {
        return stopTime;
    }
}
