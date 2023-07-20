package com.example.homie.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.homie.R;
import com.example.homie.login;

public class customer_dasboard extends AppCompatActivity {

    CardView cleaning, babySitting, laundry, electrician, plumber, feedBack, logOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_dasboard);

        cleaning = findViewById(R.id.cleaning);
        babySitting = findViewById(R.id.babySitting);
        laundry = findViewById(R.id.laundry);
        electrician = findViewById(R.id.electrician);
        plumber =  findViewById(R.id.plumbing);
        feedBack = findViewById(R.id.feedBack);
        logOut = findViewById(R.id.LogOut);

        cleaning.setOnClickListener(view -> startActivity(new Intent(customer_dasboard.this, cleaning_booking_form.class)));
        babySitting.setOnClickListener(view -> startActivity(new Intent(customer_dasboard.this, baby_sitting_booking_form.class)));
        laundry.setOnClickListener(view -> startActivity(new Intent(customer_dasboard.this, laundary_booking_form.class)));
        electrician.setOnClickListener(view -> startActivity(new Intent(customer_dasboard.this, electrician_booking_form.class)));
        plumber.setOnClickListener(view -> startActivity(new Intent(customer_dasboard.this, plumbing_booking_form.class)));
        feedBack.setOnClickListener(view -> startActivity(new Intent(customer_dasboard.this, customer_feedback.class)));

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(customer_dasboard.this, login.class);
                startActivity(intent);
                finish();
            }
        });


    }
}