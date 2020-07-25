package com.example.tinderclone.Registration;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tinderclone.Helpers.Utils;
import com.example.tinderclone.R;
import com.example.tinderclone.Users.User;
import com.hbb20.CountryCodePicker;

public class PhoneNumberActivity extends AppCompatActivity {

    private EditText phoneText;
    private Button nextButton;
    private CountryCodePicker ccp;
    private TextView errorPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);

        User user = new User();
        Intent intent = new Intent(PhoneNumberActivity.this, ConfirmCodeActivity.class);

        ccp = findViewById(R.id.ccp);
        phoneText = findViewById(R.id.phoneText);
        ccp.registerCarrierNumberEditText(phoneText);
        nextButton = findViewById(R.id.nextButton);
        errorPhone = findViewById(R.id.errorPhone);

        Utils.registrationButton(phoneText, nextButton, errorPhone, getResources().getString(R.string.number_registered_error));

        nextButton.setOnClickListener(v -> {
            user.setPhoneNumber(ccp.getFullNumberWithPlus().replace(" ", ""));
            startActivity(Utils.goToNextActivity(user, intent));
        });
    }
}
