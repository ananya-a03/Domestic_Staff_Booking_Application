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

import com.example.homie.R;
import com.example.homie.java_classes.baby_sitting;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class baby_sitting_booking_form extends AppCompatActivity {

    EditText fullName5, phone5, email5, address5, pinCode5, date5, time5;
    Button payNow5;
    private DatabaseReference references;
    FirebaseDatabase rootNode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_sitting_booking_form);
        fullName5 = findViewById(R.id.fullNameET5);
        email5 = findViewById(R.id.emailET5);
        phone5 = findViewById(R.id.phoneET5);
        address5 = findViewById(R.id.addressET5);
        pinCode5 = findViewById(R.id.pinCodeET5);
        date5 = findViewById(R.id.dateET5);
        time5 = findViewById(R.id.timeET5);
        payNow5 = findViewById(R.id.payBtn5);

        rootNode = FirebaseDatabase.getInstance();
        references = rootNode.getReference("Bookings").child("Baby Sitting");

        payNow5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // GET ALL THE VALUES
                String name = fullName5.getText().toString().trim();
                String email = email5.getText().toString().trim();
                String phone = phone5.getText().toString().trim();
                String address = address5.getText().toString().trim();
                String pinCode = pinCode5.getText().toString().trim();
                String date = date5.getText().toString().trim();
                String time = time5.getText().toString().trim();

                baby_sitting BabyOBJ = new baby_sitting(name, email, phone, address, pinCode, date, time);

                if (TextUtils.isEmpty(name)) {
                    fullName5.setError("Please enter the name");
                    fullName5.requestFocus();
                } else if (TextUtils.isEmpty(email)) {
                    email5.setError("Please enter the email");
                    email5.requestFocus();
                } else if (TextUtils.isEmpty(phone)) {
                    phone5.setError("Please enter the phone");
                    phone5.requestFocus();
                }
                else if (phone.length() != 10) {
                    phone5.setError("The length should be 10");
                    phone5.requestFocus();

                } else if (TextUtils.isEmpty(address)) {
                    address5.setError("Please enter the address");
                    address5.requestFocus();
                }
                else if (TextUtils.isEmpty(pinCode)) {
                    pinCode5.setError("Please enter the pinCode");
                    pinCode5.requestFocus();
                }
                else if (TextUtils.isEmpty(date)) {
                    date5.setError("Please enter the date");
                    date5.requestFocus();
                }
                else if (TextUtils.isEmpty(time)) {
                    time5.setError("Please enter the time");
                    time5.requestFocus();
                }
                else {
                    references.child(phone).setValue(BabyOBJ).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(baby_sitting_booking_form.this, "Your booking is successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(baby_sitting_booking_form.this, payment_form.class));
                                finish();
                            } else {
                                try {
                                    throw Objects.requireNonNull(task.getException());
                                } catch (DatabaseException de) {
                                    de.printStackTrace();
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    });
                }
            }
        });

    }

}