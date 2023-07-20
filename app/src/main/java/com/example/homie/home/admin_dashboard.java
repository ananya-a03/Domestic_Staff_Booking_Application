package com.example.homie.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.homie.R;
import com.example.homie.admin_retrieve.baby_sitting_retrieve;
import com.example.homie.admin_retrieve.cleaning_retrieve;
import com.example.homie.admin_retrieve.electrician_retrieve;
import com.example.homie.admin_retrieve.feedback_retrieve;
import com.example.homie.admin_retrieve.laundry_retreive;
import com.example.homie.login;
import com.example.homie.admin_retrieve.plumbing_reterive;

public class admin_dashboard extends AppCompatActivity {

    CardView adminLogout;
    CardView cleaning;
    CardView baby_sitting;
    CardView laundry;
    CardView plumbing;
    CardView electrician;
    CardView feedBacks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        adminLogout = findViewById(R.id.adminLogOut);
        cleaning = findViewById(R.id.adminCleaning);
        baby_sitting = findViewById(R.id.adminBabySitting);
        laundry = findViewById(R.id.adminLaundry);
        plumbing = findViewById(R.id.adminPlumbing);
        electrician = findViewById(R.id.adminElectrician);
        feedBacks = findViewById(R.id.adminfeedBack);

        cleaning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(admin_dashboard.this, cleaning_retrieve.class));
            }
        });
        baby_sitting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(admin_dashboard.this, baby_sitting_retrieve.class));
            }
        });
        laundry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(admin_dashboard.this, laundry_retreive.class));

            }
        });
        plumbing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(admin_dashboard.this, plumbing_reterive.class));
            }
        });

        electrician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(admin_dashboard.this, electrician_retrieve.class));
            }
        });

        feedBacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(admin_dashboard.this, feedback_retrieve.class));
            }
        });

        adminLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin_dashboard.this, login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}