package com.example.homie;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;

import java.util.Objects;

public class register extends AppCompatActivity {

    FirebaseAuth mAuth;
    public final static String TAG="RegisterActivity";
    EditText emailEt, passwordEt, comPasswordEt, phoneEt ;
    TextView redirectSignIn;

    Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        emailEt = findViewById(R.id.emailET);
        passwordEt = findViewById(R.id.signUpPasswordET);
        comPasswordEt = findViewById(R.id.signUpComPasswordET);
        signUp = findViewById(R.id.registrationSignUp);
        redirectSignIn = findViewById(R.id.signInTV);
        phoneEt = findViewById(R.id.mobileET);

        signUp.setOnClickListener(view -> {
            String userEmail = emailEt.getText().toString().trim();
            String phone = phoneEt.getText().toString().trim();
            String pass = passwordEt.getText().toString().trim();
            String comPass = comPasswordEt.getText().toString().trim();

            if (TextUtils.isEmpty(userEmail)) {
                Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                emailEt.setError("email is required");
                emailEt.requestFocus();
            }else if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
                Toast.makeText(this, "Please re-enter your email", Toast.LENGTH_SHORT).show();
                emailEt.setError("Valid email is required");
                emailEt.requestFocus();
            }
            else if (TextUtils.isEmpty(phone)) {
                Toast.makeText(getApplicationContext(), "Enter phone number!", Toast.LENGTH_SHORT).show();
                phoneEt.setError("Phone is required");
                emailEt.requestFocus();
            }else if (TextUtils.isEmpty(pass)) {
                Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                emailEt.setError("password is required");
                emailEt.requestFocus();

            }else if (TextUtils.isEmpty(comPass)) {
                Toast.makeText(getApplicationContext(), "Confirm your password!", Toast.LENGTH_SHORT).show();
                emailEt.setError("confirm password is required");
                emailEt.requestFocus();
            }else if (!pass.equals(comPass)) {
                Toast.makeText(getApplicationContext(), "Passwords do not match!", Toast.LENGTH_SHORT).show();
                emailEt.setError(" password and confirm password are not equal");
                emailEt.requestFocus();
            }else{

            mAuth.createUserWithEmailAndPassword(userEmail,pass).addOnCompleteListener(task->{

                    if (task.isSuccessful()) {
                        Toast.makeText(register.this, "Sign-Up Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(register.this, login.class));
                        finish();
                    } else {
                        // Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(register.this, "Sign-Up Failed", Toast.LENGTH_SHORT).show();
                        // updateUI(null);
                    }
                    try{
                        throw Objects.requireNonNull(task.getException());
                    }catch (FirebaseAuthEmailException e){
                        Toast.makeText(this, "Re-enter email", Toast.LENGTH_SHORT).show();
                    }catch (FirebaseAuthUserCollisionException e){
                        Toast.makeText(this, "User already created", Toast.LENGTH_SHORT).show();
                        emailEt.setError("User is already created");
                        emailEt.requestFocus();
                    }catch (Exception e){
                        Log.e(TAG,e.getMessage());
                    }
            });}
        });
        redirectSignIn.setOnClickListener(view -> {
            startActivity(new Intent(register.this, login.class));
            finish();
        });


    }
}
