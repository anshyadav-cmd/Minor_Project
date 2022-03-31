package com.example.whereru;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whereru.controller.StopsDataAdapter;
import com.example.whereru.model.Bus;

public class BusStopActivity extends AppCompatActivity {
    private Bus mBus;
    private RecyclerView stopsRecyclerView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_stop);

        mBus =  (Bus)getIntent().getSerializableExtra(ViewBusActivity.BUS_OBJECT_KEY);

        stopsRecyclerView = findViewById(R.id.stopsRV);
        stopsRecyclerView.setAdapter(new StopsDataAdapter(mBus));
        stopsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        setTitle("Bus No " + mBus.getBusNumber());
    }
}