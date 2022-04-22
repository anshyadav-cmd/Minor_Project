package com.example.whereru;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;

public class MainActivity extends AppCompatActivity  {

    private MapsFragment mMapsFragment;
    private FirebaseAuth fAuth;
    private DBUsers mDBUsers;
    private LatLng mLatLng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMapsFragment = (MapsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_map);
        fAuth = FirebaseAuth.getInstance();
        mDBUsers = new DBUsers();
    }
    public void logout (View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.logoutMenuItem){
            AlertDialog.Builder logoutAlert = new AlertDialog.Builder(this)
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
        if(item.getItemId() == R.id.viewRouteMenuItem){
            startActivity(new Intent(this, ViewBusActivity.class));
        }
        if(item.getItemId() == R.id.trackBusMenuItem){
//            startActivity(new Intent(this, TrackBusActivity.class));

            mDBUsers.mReference.child("J2GpwMxY89MqrHVVJI094gsxghk1").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    String latitude = task.getResult().child("latitude").getValue(String.class);
                    String longitude = task.getResult().child("longitude").getValue(String.class);

                    mLatLng = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
                    Log.i("LatLon", "onChildChanged: " + mLatLng.toString());


                }
            });


//                mDBUsers.mReference.addChildEventListener(new ChildEventListener() {
//                    @Override
//                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//                    }
//
//                    @Override
//                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                        String latitude = snapshot.child("latitude").getValue(String.class);
//                        String longitude = snapshot.child("longitude").getValue(String.class);
//
//                        mLatLng = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
//                        Log.i("LatLon", "onChildChanged: " + mLatLng.toString());
//                    }
//
//                    @Override
//                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//                    }
//
//                    @Override
//                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
        }
        if(item.getItemId() == R.id.viewProfileMenuItem){
            startActivity(new Intent(this, ViewProfileActivity.class));
        }
            return true;
    }
}