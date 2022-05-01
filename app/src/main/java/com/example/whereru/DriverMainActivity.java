package com.example.whereru;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class DriverMainActivity extends AppCompatActivity {

    private static final int REQUEST_LOCATION = 1;
    private Button logoutBtn;
    private Button profileBtn;
    private Button startBtn;
    private Button stopBtn;
    private DBUsers mDBUsers;
    private FirebaseAuth fAuth;

    LocationManager locationManager;
    String latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_main);

        logoutBtn = findViewById(R.id.btnLogout);
        profileBtn = findViewById(R.id.driverProfileBtn);
        startBtn = findViewById(R.id.btnStart);
        stopBtn = findViewById(R.id.btnStop);
        mDBUsers = new DBUsers();
        fAuth = FirebaseAuth.getInstance();

        final ScheduledExecutorService[] scheduler = new ScheduledExecutorService[1];

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DriverMainActivity.this, "Location Sharing Live", Toast.LENGTH_SHORT).show();
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    OnGPS();
                } else {
                    scheduler[0] = Executors.newScheduledThreadPool(1);
                    Runnable toRun = new Runnable() {
                        public void run() {
                            getLocation();
                        }
                    };
                    ScheduledFuture<?> handle = scheduler[0].scheduleAtFixedRate(toRun, 1, 1, TimeUnit.SECONDS);

                }
            }
        });
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DriverMainActivity.this, "Location sharing Stopped", Toast.LENGTH_SHORT).show();
                scheduler[0].shutdown();
            }
        });
    }

    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void getLocation() {
            if (ActivityCompat.checkSelfPermission(
                    DriverMainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    DriverMainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
            } else {
                Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (locationGPS != null) {
                    double lat = locationGPS.getLatitude();
                    double longi = locationGPS.getLongitude();
                    latitude = String.valueOf(lat);
                    longitude = String.valueOf(longi);
                    Log.i("Location", latitude + " " + longitude +"");
                    mDBUsers.mReference.child(fAuth.getUid()).child("latitude").setValue(latitude);
                    mDBUsers.mReference.child(fAuth.getUid()).child("longitude").setValue(longitude);
                } else {
                    Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
                }
            }
    }

    @Override
    protected void onStart() {
        super.onStart();

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder logoutAlert = new AlertDialog.Builder(DriverMainActivity.this)
                        .setTitle("Are you sure you want to Logout?")
                        .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseAuth.getInstance().signOut();
                                startActivity(new Intent(getApplicationContext(), Login.class));
                                finish();
                            }
                        })
                        .setNegativeButton("No", null);
                logoutAlert.show();
            }
        });

        profileBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, ViewProfileActivity.class));
        });
    }
}