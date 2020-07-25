package com.example.tinderclone.NotificationWindows;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tinderclone.Helpers.Utils;
import com.example.tinderclone.MainWindows.MainWindowActivity;
import com.example.tinderclone.R;
import com.example.tinderclone.Users.User;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class SendNotificationsActivity extends AppCompatActivity {

    private Button notificationActivateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_notifications);

        notificationActivateButton = findViewById(R.id.notificationActivateButton);
        Intent givenIntent = getIntent();
        User user = givenIntent.getParcelableExtra("user");

        notificationActivateButton.setOnClickListener(view -> startActivity(Utils
                .goToNextActivity(user, new Intent(SendNotificationsActivity.this, MainWindowActivity.class))));
    }
}
