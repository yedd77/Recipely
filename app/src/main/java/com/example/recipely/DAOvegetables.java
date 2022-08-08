package com.example.recipely;


import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOvegetables {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference databaseReference;

    public DAOvegetables() {
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://recipely-d8af1-default-rtdb.asia-southeast1.firebasedatabase.app");
        databaseReference = db.getReference(vegetable.class.getSimpleName());
    }

    public Task<Void> add (vegetable veg) {
        return databaseReference.child(user.getUid()).setValue(veg);
    }
}
