package com.example.tinderclone.NotificationWindows;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.android.volley.VolleyError;
import com.example.tinderclone.DB.DBHandler;
import com.example.tinderclone.Helpers.ModelsFiller;
import com.example.tinderclone.Helpers.Utils;
import com.example.tinderclone.R;
import com.example.tinderclone.Users.User;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class GeoDataActivity extends AppCompatActivity {

    private Button locationActivateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_data);

        Intent givenIntent = getIntent();
        User user = givenIntent.getParcelableExtra("user");

        boolean isAllow = ActivityCompat.checkSelfPermission(GeoDataActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED;

        locationActivateButton = findViewById(R.id.locationActivateButton);
        locationActivateButton.setOnClickListener(view -> {
            DBHandler.getUserByEmailAndPassword(this, user.getMail(), user.getPassword(), new DBHandler.VolleyCallback() {
                @Override
                public void onSuccess(String result) {
                    try {
                        JSONObject obj = new JSONObject(result);
                        User mainUser = ModelsFiller.fillUser(obj);

                        DBHandler.getPhotos(GeoDataActivity.this, mainUser.getMail(), new DBHandler.VolleyCallback() {
                            @Override
                            public void onSuccess(String result) {
                                List<String> images = new ArrayList<>();
                                ModelsFiller.fillImagesList(images, result);
                                if (images.size() > 0)
                                    mainUser.setMainPhoto(images.get(0));
                                else
                                    mainUser.setMainPhoto("");

                                DBHandler.updateUser(GeoDataActivity.this, mainUser);
                                startActivity(Utils.goToNextActivity(mainUser, new Intent(GeoDataActivity.this, SendNotificationsActivity.class)));
                            }

                            @Override
                            public void onError(VolleyError error) { }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(VolleyError error) { }
            });
        });
    }
}
