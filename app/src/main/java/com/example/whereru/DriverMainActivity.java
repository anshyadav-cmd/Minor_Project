package com.example.whereru;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class DriverMainActivity extends AppCompatActivity {
    private Button logoutBtn;
    private Button profileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_main);

        logoutBtn = findViewById(R.id.btnLogout);
        profileBtn = findViewById(R.id.driverProfileBtn);

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