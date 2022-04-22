package com.example.whereru;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity  {

    private MapsFragment mMapsFragment;
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMapsFragment = (MapsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_map);
        fAuth = FirebaseAuth.getInstance();
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
            startActivity(new Intent(this, TrackBusActivity.class));
        }
        if(item.getItemId() == R.id.viewProfileMenuItem){
            startActivity(new Intent(this, ViewProfileActivity.class));
        }
            return true;
    }
}