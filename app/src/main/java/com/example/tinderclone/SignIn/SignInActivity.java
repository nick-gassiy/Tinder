package com.example.tinderclone.SignIn;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.example.tinderclone.R;

public class SignInActivity extends AppCompatActivity {
    private Button googleBtn;
    private Button emailBtn;
    private Button numberBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        googleBtn = findViewById(R.id.googleSignIn);
        emailBtn = findViewById(R.id.emailSignIn);
        numberBtn = findViewById(R.id.numberSignIn);

        numberBtn.setOnClickListener(v -> startActivity(new Intent(SignInActivity.this, PhoneSignInActivity.class)));
        emailBtn.setOnClickListener(v -> startActivity(new Intent(SignInActivity.this, EmailSignInActivity.class)));
    }
}
