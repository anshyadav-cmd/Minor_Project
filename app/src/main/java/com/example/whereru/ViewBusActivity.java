package com.example.whereru;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arasthel.asyncjob.AsyncJob;
import com.example.whereru.controller.BusDataAdapter;
import com.example.whereru.model.Bus;
import com.example.whereru.model.BusStop;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ViewBusActivity extends AppCompatActivity implements BusDataAdapter.OnBusClicked {

    private List<Bus> mAllBuses = new ArrayList<>();
    private BusDataAdapter mBusAdapter;
    private RecyclerView busRecyclerView;

    public static final String BUS_OBJECT_KEY = "bus_object_key19910";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bus);
        setTitle("View Route");

        new AsyncJob.AsyncJobBuilder<Boolean>()
                .doInBackground(new AsyncJob.AsyncAction<Boolean>() {
                    @Override
                    public Boolean doAsync() {
                        try {
                            JSONObject rootJSONObject = new JSONObject(loadJsonFromAssets());

                            JSONObject bus = rootJSONObject.getJSONObject("bus_no_1");
                            JSONArray busStop = bus.getJSONArray("stops");
                            addBusesToArrayList(bus, busStop, mAllBuses);

                            bus = rootJSONObject.getJSONObject("bus_no_2");
                            busStop = bus.getJSONArray("stops");
                            addBusesToArrayList(bus, busStop, mAllBuses);


                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return true;
                    }
                }).doWhenFinished(new AsyncJob.AsyncResultAction() {
                    @Override
                    public void onResult(Object o) {
                        printBusList();
//                        mBusAdapter = new BusDataAdapter(mAllBuses, ViewBusActivity.this);
                        busRecyclerView = findViewById(R.id.busRV);
                        busRecyclerView.setAdapter(new BusDataAdapter(mAllBuses, ViewBusActivity.this));
                        busRecyclerView.setLayoutManager(new LinearLayoutManager(ViewBusActivity.this));
                    }
                })
                .create()
                .start();
    }

    // testing for data input
    private void printBusList() {
        for(Bus bus : mAllBuses) {
            Log.i("Tag", bus.getBusDriverName()+"");
            Log.i("Tag", bus.getBusDriverPhone()+"");
            Log.i("Tag", bus.getBusNumber() + "");
            Log.i("Tag", bus.getStopList()+"");
        }
    }

    //adding data to arrayList of bus
    private void addBusesToArrayList (JSONObject bus, JSONArray stops, List<Bus> arrayList) {
        try{
            if(bus != null && stops != null) {
                List<BusStop> stopList = new ArrayList<>();
                for(int i = 0; i < stops.length(); i++) {
                    JSONObject tempObj = stops.getJSONObject(i);
                    stopList.add(new BusStop(tempObj.getString( "time"),tempObj.getString("address")));
                }
                arrayList.add(new Bus(Integer.parseInt(bus.getString("bus_no")), bus.getString("driver"),
                        bus.getString("phone_no"), (ArrayList<BusStop>) stopList));
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // loading data from json file
    private  String loadJsonFromAssets() {
        String json = null;
        try{
            InputStream is = this.getAssets().open("bus_data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public void busIsClicked(Bus bus) {
        displayStops(bus);
    }

    private void displayStops(Bus bus) {
        Intent stopsItemIntent = new Intent(this, BusStopActivity.class);
        stopsItemIntent.putExtra(BUS_OBJECT_KEY, bus);
        startActivity(stopsItemIntent);
    }
}