package com.example.homie.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.homie.java_classes.Electrician;
import com.example.homie.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class electrician_booking_form extends AppCompatActivity {

    EditText Name3, Email3, Phone3, Address3, PinCode3, Date3, Time3;
    Button PayBtn3;
    private DatabaseReference reference;
    FirebaseDatabase rootNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electrician_booking_form);

        Name3 = findViewById(R.id.fullNameET3);
        Email3 = findViewById(R.id.emailET3);
        Phone3 = findViewById(R.id.phoneET3);
        Address3 = findViewById(R.id.addressET3);
        PinCode3 = findViewById(R.id.pinCodeET3);
        Date3 = findViewById(R.id.dateET3);
        Time3 = findViewById(R.id.timeET3);
        PayBtn3 = findViewById(R.id.payBtn3);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Bookings").child("Electrician");

        PayBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = Name3.getText().toString().trim();
                String email = Email3.getText().toString().trim();
                String phone = Phone3.getText().toString().trim();
                String address = Address3.getText().toString().trim();
                String pinCode = PinCode3.getText().toString().trim();
                String date = Date3.getText().toString().trim();
                String time = Time3.getText().toString().trim();

                Electrician EleOBJ = new Electrician(name, email, phone, address, pinCode, date,time);

                if(TextUtils.isEmpty(name)){
                    Name3.setError("Please enter the name");
                    Name3.requestFocus();
                }
                else if (TextUtils.isEmpty(email)){
                    Email3.setError("please enter the email");
                    Email3.requestFocus();
                }
                else if(TextUtils.isEmpty(phone)){
                    Phone3.setError("Please enter the phone");
                    Phone3.requestFocus();
                }
                else if(phone.length() != 10){
                    Phone3.setError("The length should be 10");
                    Phone3.requestFocus();
                }
                else if(TextUtils.isEmpty(address)){
                    Address3.setError("please enter the address");
                    Address3.requestFocus();
                }
                else  if(TextUtils.isEmpty(pinCode)){
                    PinCode3.setError("Please enter the pinCode");
                    PinCode3.requestFocus();
                }
                else if(TextUtils.isEmpty(date)){
                    Date3.setError("Please enter the date");
                    Date3.requestFocus();
                }
                else if(TextUtils.isEmpty(time)){
                    Time3.setError("Please enter the time");
                    Time3.requestFocus();
                }
                else {
                    reference.child(phone).setValue(EleOBJ).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(electrician_booking_form.this,"Your Booking is successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(electrician_booking_form.this, payment_form.class));
                                finish();
                            }
                            else{
                                try {
                                    throw Objects.requireNonNull(task.getException());
                                }catch (DatabaseException de){
                                    de.printStackTrace();
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }

                        }
                    });
                }
            }
        });


    }
}