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

import com.example.homie.java_classes.Plumbing;
import com.example.homie.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class plumbing_booking_form extends AppCompatActivity {

    EditText fullName1, phone1, email1, address1, pinCode1, date1, time1;
    Button payNow1;
    private DatabaseReference references;
    FirebaseDatabase rootNode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plumbing_booking_form);

        fullName1 = findViewById(R.id.fullNameET1);
        email1 = findViewById(R.id.emailET1);
        phone1 = findViewById(R.id.phoneET1);
        address1 = findViewById(R.id.addressET1);
        pinCode1 = findViewById(R.id.pinCodeET1);
        date1 = findViewById(R.id.dateET1);
        time1 = findViewById(R.id.timeET1);
        payNow1 = findViewById(R.id.payBtn1);


        rootNode = FirebaseDatabase.getInstance();
        references = rootNode.getReference("Bookings").child("Plumbing");

        payNow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // GET ALL THE VALUES
                String name = fullName1.getText().toString().trim();
                String email = email1.getText().toString().trim();
                String phone = phone1.getText().toString().trim();
                String address = address1.getText().toString().trim();
                String pinCode = pinCode1.getText().toString().trim();
                String date = date1.getText().toString().trim();
                String time = time1.getText().toString().trim();

                Plumbing PlumbingOBJ = new Plumbing(name, email, phone, address, pinCode, date, time);


                if (TextUtils.isEmpty(name)) {
                    fullName1.setError("Please enter the name");
                    fullName1.requestFocus();
                } else if (TextUtils.isEmpty(email)) {
                    email1.setError("Please enter the email");
                    email1.requestFocus();
                } else if (TextUtils.isEmpty(phone)) {
                    phone1.setError("Please enter the phone");
                    phone1.requestFocus();
                }
                else if (phone.length() != 10) {
                    phone1.setError("The length should be 10");
                    phone1.requestFocus();

                } else if (TextUtils.isEmpty(address)) {
                    address1.setError("Please enter the address");
                    address1.requestFocus();
                }
                else if (TextUtils.isEmpty(pinCode)) {
                    pinCode1.setError("Please enter the pinCode");
                    pinCode1.requestFocus();
                }
                else if (TextUtils.isEmpty(date)) {
                    date1.setError("Please enter the date");
                    date1.requestFocus();
                }
                else if (TextUtils.isEmpty(time)) {
                    time1.setError("Please enter the time");
                    time1.requestFocus();
                }
                else {
                    references.child(phone).setValue(PlumbingOBJ).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(plumbing_booking_form.this, "Your booking is successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(plumbing_booking_form.this, payment_form.class));
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