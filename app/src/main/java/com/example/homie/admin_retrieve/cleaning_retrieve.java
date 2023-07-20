package com.example.homie.admin_retrieve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.homie.R;
import com.example.homie.java_classes.cleaning;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class cleaning_retrieve extends AppCompatActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaning_retrieve);

        listView = findViewById(R.id.cleaningLV);

        ArrayList<String> list = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.cleaning_list_item,list);
        listView.setAdapter(adapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Bookings").child("Cleaning");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
               for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                   cleaning clean = dataSnapshot.getValue(cleaning.class);
                   String txt = "Name:"+clean.getName() + "\nEmail:" + clean.getEmail() + "\nPhone:" + clean.getPhone() + "\nAddress:" + clean.getAddress() + "\nPinCode:" + clean.getPinCode() + "\nDate:" + clean.getDate() + "\nTime:" + clean.getTime();
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