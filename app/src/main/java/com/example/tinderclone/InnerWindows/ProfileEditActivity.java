package com.example.tinderclone.InnerWindows;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.tinderclone.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileEditActivity extends AppCompatActivity {
    TextView edit;
    TextView view;
    private BottomNavigationView settingsEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        edit = findViewById(R.id.profileEditTextView);
        view = findViewById(R.id.profileViewTextView);
        LinearLayout navigationLayout = findViewById(R.id.settingsEdit);

        getSupportFragmentManager().beginTransaction().replace(R.id.settingsContainer, new ProfileInfoEditFragment()).commit();

        edit.setOnClickListener(v -> {
            Fragment selectedFragment = null;
            selectedFragment = new ProfileInfoEditFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.settingsContainer, selectedFragment).commit();
        });


        view.setOnClickListener(v -> {
            Fragment selectedFragment = null;
            selectedFragment = new ProfileViewFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.settingsContainer, selectedFragment).commit();
        });
    }
}