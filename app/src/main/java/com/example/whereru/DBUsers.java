package com.example.whereru;

import com.google.android.gms.tasks.Task;
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

}
