package com.example.homie;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.homie.home.admin_dashboard;
import com.example.homie.home.customer_dasboard;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    FirebaseAuth auth;
    EditText loginEmail, loginPassword;
    Button signInBtn;
    TextView signUpRedirectText;
    GoogleSignInClient googleSignInClient;
    //SignInButton signInWithGoogle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        loginEmail = findViewById(R.id.EmailET);
        loginPassword = findViewById(R.id.passwordET);
        signInBtn = findViewById(R.id.signInBtn);
        signUpRedirectText = findViewById(R.id.signUpBtn);

        //signInWithGoogle = findViewById(R.id.signInGoogle);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("798829607854-gk96gkshvg2om4jk7i2h69ebdpo6ngs6.apps.googleusercontent.com")
                .requestEmail().build();

        googleSignInClient = GoogleSignIn.getClient(login.this, googleSignInOptions);
        signInBtn.setOnClickListener(view -> {
            String userEmail = loginEmail.getText().toString().trim();
            String userPass = loginPassword.getText().toString().trim();

            if (userEmail.isEmpty()) {
                loginEmail.setError("Email can't be empty");
            } else if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
                loginEmail.setError("Please re-enter your email");
            } else if (userPass.isEmpty()) {
                loginPassword.setError("Password can't be empty");
            } else {
                auth.signInWithEmailAndPassword(userEmail, userPass).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        if(userEmail.equals("admin@homie.in") && userPass.equals("admin123")){
                            Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(login.this, admin_dashboard.class));
                            finish();
                        }
                        else {
                        Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(login.this, customer_dasboard.class));
                        finish();
                        }
                    } else {
                        Toast.makeText(login.this, "Login Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });
        signUpRedirectText.setOnClickListener(view -> {
            startActivity(new Intent(login.this, register.class));
        });
//        signInWithGoogle.setOnClickListener((View.OnClickListener) view -> {
//            Intent intent = googleSignInClient.getSignInIntent();
//            startActivityForResult(intent, 100);
//        });

//        FirebaseUser firebaseUser = auth.getCurrentUser();
//
//        if (firebaseUser != null){
//            startActivity(new Intent(login.this, customer_dasboard.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
//        }

    }
}