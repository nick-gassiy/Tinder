package com.example.tinderclone;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.tinderclone.DB.Constants;
import com.example.tinderclone.DB.RequestHandler;
import com.example.tinderclone.DB.SharedPrefManager;
import com.example.tinderclone.Helpers.ModelsFiller;
import com.example.tinderclone.Helpers.Utils;
import com.example.tinderclone.MainWindows.MainWindowActivity;
import com.example.tinderclone.Registration.PhoneNumberActivity;
import com.example.tinderclone.SignIn.SignInActivity;
import com.example.tinderclone.Users.User;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button registration;
    private Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            String email = SharedPrefManager.getInstance(this).getUserEmail();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_GET_USER_BY_EMAIL_ONLY, response ->  {
                try {
                    User user = ModelsFiller.fillUser(new JSONObject(response));
                    startActivity(Utils.goToNextActivity(user, new Intent(MainActivity.this, MainWindowActivity.class)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, error -> {}) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("email", email);
                    return params;
                }
            };
            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        }

        registration = findViewById(R.id.reg);
        signIn = findViewById(R.id.log);

        registration.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, PhoneNumberActivity.class)));
        signIn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SignInActivity.class)));
    }
}
