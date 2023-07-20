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
import com.example.homie.java_classes.cleaning;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class cleaning_booking_form extends AppCompatActivity {

    EditText nameET, emailET, phoneEt, addressET, pincodeET, dateEt, timeET;
    Button payNow;
    FirebaseDatabase rootNode;
    DatabaseReference references;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaning_booking_form);

        nameET = findViewById(R.id.fullNameET);
        emailET = findViewById(R.id.emailET);
        phoneEt = findViewById(R.id.phoneET);
        addressET = findViewById(R.id.addressET);
        pincodeET = findViewById(R.id.pinCodeET);
        dateEt = findViewById(R.id.dateET);
        timeET = findViewById(R.id.timeET);
        payNow = findViewById(R.id.payBtn);

        rootNode = FirebaseDatabase.getInstance();
        references = rootNode.getReference("Bookings").child("Cleaning");

        payNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // GET ALL THE VALUES
                String name = nameET.getText().toString().trim();
                String email = emailET.getText().toString().trim();
                String phone = phoneEt.getText().toString().trim();
                String address = addressET.getText().toString().trim();
                String pinCode = pincodeET.getText().toString().trim();
                String date = dateEt.getText().toString().trim();
                String time = timeET.getText().toString().trim();

                cleaning cleaningOBJ = new cleaning(name, email, phone, address, pinCode, date, time);

                if (TextUtils.isEmpty(name)) {
                    nameET.setError("Please enter the name");
                    nameET.requestFocus();
                } else if (TextUtils.isEmpty(email)) {
                    emailET.setError("Please enter the email");
                    emailET.requestFocus();
                } else if (TextUtils.isEmpty(phone)) {
                    phoneEt.setError("Please enter the phone");
                    phoneEt.requestFocus();
                }
                   else if (phone.length() != 10) {
                        phoneEt.setError("The length should be 10");
                        phoneEt.requestFocus();

                } else if (TextUtils.isEmpty(address)) {
                    addressET.setError("Please enter the address");
                    addressET.requestFocus();
                }
                   else if (TextUtils.isEmpty(pinCode)) {
                    pincodeET.setError("Please enter the pinCode");
                    pincodeET.requestFocus();
                }
                   else if (TextUtils.isEmpty(date)) {
                    dateEt.setError("Please enter the date");
                    dateEt.requestFocus();
                }
                   else if (TextUtils.isEmpty(time)) {
                    timeET.setError("Please enter the time");
                    timeET.requestFocus();
                }
                   else {
                    references.child(phone).setValue(cleaningOBJ).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(cleaning_booking_form.this, "Your booking is successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(cleaning_booking_form.this, payment_form.class));
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

