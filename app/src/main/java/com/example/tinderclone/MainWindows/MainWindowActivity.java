package com.example.tinderclone.MainWindows;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import com.example.tinderclone.R;
import com.example.tinderclone.Users.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainWindowActivity extends AppCompatActivity {

    private BottomNavigationView topNavigation;
    private User mainUser;

    private String email;
    private String password;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_window);

        Intent givenIntent = getIntent();
        mainUser = givenIntent.getParcelableExtra("user");
        email = mainUser.getMail();
        password = mainUser.getPassword();
        phone = mainUser.getPhoneNumber();

        topNavigation = findViewById(R.id.topMenu);
        topNavigation.setOnNavigationItemSelectedListener(menuItem -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("user", mainUser);
            Fragment selectedFragment = null;
            switch (menuItem.getItemId()) {
                case R.id.action_swipes:
                    selectedFragment = new CardWindowFragment();
                    selectedFragment.setArguments(bundle);
                    break;
                case R.id.action_likes:
                    selectedFragment = new LikesWindowFragment();
                    selectedFragment.setArguments(bundle);
                    break;
                case R.id.action_chat:
                    selectedFragment = new ChatWindowFragment();
                    selectedFragment.setArguments(bundle);
                    break;
                case R.id.action_profile:
                    selectedFragment = new ProfileWindowFragment();
                    selectedFragment.setArguments(bundle);
                    break;
            }
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, selectedFragment).commit();
            }
            return true;
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new CardWindowFragment(mainUser)).commit();
    }
}