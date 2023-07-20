package com.example.homie.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.homie.R;

public class payment_form extends AppCompatActivity {

    Button payBtn;
    EditText CardType, CardName, CardNum, ExpDate, CVV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_form);
        payBtn = findViewById(R.id.PaymentButton);

        CardType = findViewById(R.id.cardNameET);
        CardName = findViewById(R.id.nameOnCardET);
        CardNum = findViewById(R.id.CardNoEt);
        ExpDate = findViewById(R.id.ExpireDateET);
        CVV = findViewById(R.id.cvvET);

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cardType = CardType.getText().toString().trim();
                String cardName = CardName.getText().toString().trim();
                String cardNum = CardNum.getText().toString().trim();
                String expDate = ExpDate.getText().toString().trim();
                String cvv = CVV.getText().toString().trim();

                if (TextUtils.isEmpty(cardType)) {
                    CardType.setError("Enter the type of card");
                    CardType.requestFocus();
                } else if (TextUtils.isEmpty(cardName)) {
                    CardName.setError("Enter the Name on card");
                    CardName.requestFocus();
                } else if (TextUtils.isEmpty(cardNum)) {
                    CardNum.setError("Enter the card number");
                    CardNum.requestFocus();
                } else if (TextUtils.isEmpty(expDate)) {
                    ExpDate.setError("Enter the expiration date");
                    ExpDate.requestFocus();
                } else if (TextUtils.isEmpty(cvv)) {
                    CVV.setError("Enter the CVV");
                    CVV.requestFocus();
                } else {

                    startActivity(new Intent(payment_form.this, customer_acknowledgement.class));
                    finish();
                }
            }
        });

    }
}