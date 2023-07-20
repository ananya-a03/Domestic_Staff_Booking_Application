package com.example.homie.admin_retrieve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.homie.R;
import com.example.homie.java_classes.baby_sitting;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class baby_sitting_retrieve extends AppCompatActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_sitting_retrieve);
        listView = findViewById(R.id.babySittingLV);

        ArrayList<String> list = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.cleaning_list_item,list);
        listView.setAdapter(adapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Bookings").child("Baby Sitting");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    baby_sitting baby = dataSnapshot.getValue(baby_sitting.class);
                    String txt = "Name:"+baby.getName() + "\nEmail:" + baby.getEmail() + "\nPhone:" + baby.getPhone() + "\nAddress:" + baby.getAddress() + "\nPinCode:" + baby.getPinCode() + "\nDate:" + baby.getDate() + "\nTime:" + baby.getTime();
                    list.add(txt);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}