package com.example.tinderclone.Registration;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.example.tinderclone.AdditionalRegistration.NameActivity;
import com.example.tinderclone.Helpers.Utils;
import com.example.tinderclone.R;
import com.example.tinderclone.Users.User;

public class AdditionalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional);

        Intent givenIntent = getIntent();
        User user = givenIntent.getParcelableExtra("user");

        Button nextAfterReg = findViewById(R.id.nextAfterReg);
        nextAfterReg.setOnClickListener(v -> startActivity(Utils.goToNextActivity(user, new Intent(AdditionalActivity.this, NameActivity.class))));
    }
}
