package com.example.tinderclone.SignIn;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tinderclone.Helpers.Utils;
import com.example.tinderclone.R;
import com.hbb20.CountryCodePicker;

public class PhoneSignInActivity extends AppCompatActivity {

    private EditText phoneText;
    private Button nextButton;
    private CountryCodePicker ccp;
    private TextView errorPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);

        ccp = findViewById(R.id.ccp);
        phoneText = findViewById(R.id.phoneText);
        ccp.registerCarrierNumberEditText(phoneText);
        nextButton = findViewById(R.id.nextButton);
        errorPhone = findViewById(R.id.errorPhone);

        Utils.registrationButton(phoneText, nextButton, errorPhone, "Вы не ввели номер телефона");
        nextButton.setOnClickListener(v -> startActivity(new Intent(PhoneSignInActivity.this, ConfirmCodeSignInActivity.class)));
    }
}