package com.example.whereru;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.arasthel.asyncjob.AsyncJob;
import com.example.whereru.model.Bus;
import com.example.whereru.model.BusStop;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ViewBusActivity extends AppCompatActivity {

    private List<Bus> mAllBuses = new ArrayList<>();

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
                            Log.i("Tag", rootJSONObject.toString());
                            JSONObject bus = rootJSONObject.getJSONObject("bus_no_1");
                            JSONArray busStop = bus.getJSONArray("stops");

                            addBusesToArrayList(bus, busStop, mAllBuses);
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return true;
                    }
                });
    }

    // testing for data input
    private void printBusList() {

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
}