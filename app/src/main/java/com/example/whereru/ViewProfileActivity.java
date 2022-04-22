package com.example.whereru;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;

public class ViewProfileActivity extends AppCompatActivity {

    private TextView nameTextView;
    private TextView emailTextView;
    private TextView typeTextView;
    private TextView phoneTextView;
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        setTitle("Profile");

        nameTextView = findViewById(R.id.profileNameTextId);
        emailTextView = findViewById(R.id.profileEmailTextId);
        typeTextView = findViewById(R.id.profileTypeTextId);
        phoneTextView = findViewById(R.id.profilePhoneTextId);
        fAuth = FirebaseAuth.getInstance();

        DBUsers dbUsers = new DBUsers();
        dbUsers.mReference.child(fAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()) {
                    nameTextView.setText(task.getResult().child("name").getValue(String.class));
                    emailTextView.setText(task.getResult().child("email").getValue(String.class));
                    typeTextView.setText(task.getResult().child("driver").getValue(Boolean.class) == true ? "Driver" : "User");
                    phoneTextView.setText(task.getResult().child("phone").getValue(String.class));;
                }else {
                    Toast.makeText(ViewProfileActivity.this, "Failed to load user profile", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}