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

public class ConfirmCodeActivity extends AppCompatActivity {

    private TextView myPhoneNumber;
    private Button nextButton;
    private EditText verifyCodeEditText;
    private TextView errorCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_code);

        Intent givenIntent = getIntent();
        User user = givenIntent.getParcelableExtra("user");

        nextButton = findViewById(R.id.codeNextButton);
        myPhoneNumber = findViewById(R.id.myCode);
        errorCode = findViewById(R.id.errorCode);

        verifyCodeEditText = findViewById(R.id.verifyCodeEditText);
        myPhoneNumber.setText(user.getPhoneNumber());

        myPhoneNumber.setText(user.getPhoneNumber());

        Utils.registrationButton(verifyCodeEditText, nextButton, 5, errorCode);

        Intent intent = new Intent(ConfirmCodeActivity.this, EmailActivity.class);
        nextButton.setOnClickListener(v -> startActivity(Utils.goToNextActivity(user, intent)));
    }
}