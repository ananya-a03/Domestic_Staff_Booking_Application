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

import com.example.homie.java_classes.Laundry;
import com.example.homie.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class laundary_booking_form extends AppCompatActivity {

    EditText fullName2, phone2, email2, address2, pinCode2, date2, time2;
    Button payNow2;
    private DatabaseReference references;
    FirebaseDatabase rootNode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundary_booking_form);

        fullName2 = findViewById(R.id.fullNameET2);
        email2 = findViewById(R.id.emailET2);
        phone2 = findViewById(R.id.phoneET2);
        address2 = findViewById(R.id.addressET2);
        pinCode2 = findViewById(R.id.pinCodeET2);
        date2 = findViewById(R.id.dateET2);
        time2 = findViewById(R.id.timeET2);
        payNow2 = findViewById(R.id.payBtn2);


        rootNode = FirebaseDatabase.getInstance();
        references = rootNode.getReference("Bookings").child("Laundry");

        payNow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // GET ALL THE VALUES
                String name = fullName2.getText().toString().trim();
                String email = email2.getText().toString().trim();
                String phone = phone2.getText().toString().trim();
                String address = address2.getText().toString().trim();
                String pinCode = pinCode2.getText().toString().trim();
                String date = date2.getText().toString().trim();
                String time = time2.getText().toString().trim();

                Laundry LaundryOBJ = new Laundry(name, email, phone, address, pinCode, date, time);


                if (TextUtils.isEmpty(name)) {
                    fullName2.setError("Please enter the name");
                    fullName2.requestFocus();
                } else if (TextUtils.isEmpty(email)) {
                    email2.setError("Please enter the email");
                    email2.requestFocus();
                } else if (TextUtils.isEmpty(phone)) {
                    phone2.setError("Please enter the phone");
                    phone2.requestFocus();
                }
                else if (phone.length() != 10) {
                    phone2.setError("The length should be 10");
                    phone2.requestFocus();

                } else if (TextUtils.isEmpty(address)) {
                    address2.setError("Please enter the address");
                    address2.requestFocus();
                }
                else if (TextUtils.isEmpty(pinCode)) {
                    pinCode2.setError("Please enter the pinCode");
                    pinCode2.requestFocus();
                }
                else if (TextUtils.isEmpty(date)) {
                    date2.setError("Please enter the date");
                    date2.requestFocus();
                }
                else if (TextUtils.isEmpty(time)) {
                    time2.setError("Please enter the time");
                    time2.requestFocus();
                }
                else {
                    references.child(phone).setValue(LaundryOBJ).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(laundary_booking_form.this, "Your booking is successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(laundary_booking_form.this, payment_form.class));
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