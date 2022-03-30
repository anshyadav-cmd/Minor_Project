package com.example.whereru.view;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whereru.R;

public class BusItemViewHolder extends RecyclerView.ViewHolder {
    private TextView busNumberTextView;
    private TextView driverNameTextView;
    private TextView driverPhoneTextView;

    public BusItemViewHolder(@NonNull View itemView) {
        super(itemView);
        busNumberTextView = itemView.findViewById(R.id.busNumberTextView);
        driverNameTextView = itemView.findViewById(R.id.driverNameTextView);
        driverPhoneTextView = itemView.findViewById(R.id.driverPhoneTextView);
    }

    public TextView getBusNumberTextView() {
        return busNumberTextView;
    }

    public void setBusNumberTextView(TextView busNumberTextView) {
        this.busNumberTextView = busNumberTextView;
    }

    public TextView getDriverNameTextView() {
        return driverNameTextView;
    }

    public void setDriverNameTextView(TextView driverNameTextView) {
        this.driverNameTextView = driverNameTextView;
    }

    public TextView getDriverPhoneTextView() {
        return driverPhoneTextView;
    }

    public void setDriverPhoneTextView(TextView driverPhoneTextView) {
        this.driverPhoneTextView = driverPhoneTextView;
    }
}
