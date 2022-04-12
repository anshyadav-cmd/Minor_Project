package com.example.whereru;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DBUsers {

    FirebaseDatabase rootNode;
    DatabaseReference mReference;

    public DBUsers() {
        rootNode = FirebaseDatabase.getInstance("https://whereru-fd566-default-rtdb.asia-southeast1.firebasedatabase.app/");
        mReference = rootNode.getReference("users");
    }

    public Task<Void> add (User user) {
        return  mReference.child(user.getUserID()).setValue(user);
    }

    public boolean isDriver(String userID) {
        final boolean[] result = new boolean[1];
        mReference.child(userID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            boolean value;
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    Log.i("DATA RE", task.getResult().getValue().toString());
                    result[0] = task.getResult().child("driver").getValue(Boolean.class);
                }else{
                    Log.i("DATA RE", "data re failed");
                }
            }

        });
        return result[0];
    }

}
