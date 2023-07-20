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
import com.example.homie.java_classes.Feedbacks;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class customer_feedback extends AppCompatActivity {

    EditText fbSubject, fbQuery;
    Button submit;
    DatabaseReference references;
    FirebaseDatabase rootNode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_feedback);
        fbSubject = findViewById(R.id.subjectET);
        fbQuery = findViewById(R.id.detailedQueryET);
        submit = findViewById(R.id.fbSubmit);


        rootNode = FirebaseDatabase.getInstance();
        references = rootNode.getReference("FeedBacks");


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sub = fbSubject.getText().toString().trim();
                String Detail = fbQuery.getText().toString().trim();

                Feedbacks fbs = new Feedbacks(sub, Detail);

                if (TextUtils.isEmpty(sub)) {
                    fbSubject.setError("Please enter the subject");
                    fbSubject.requestFocus();
                } else if (TextUtils.isEmpty(Detail)) {
                    fbQuery.setError("Please brief in minimum of 20 words");
                    fbQuery.requestFocus();

                }
                else {
                    references.child(sub).setValue(fbs).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(customer_feedback.this, "Thanks for the feedback", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(customer_feedback.this, customer_dasboard.class));
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