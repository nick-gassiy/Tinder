package com.example.tinderclone.SignIn;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.example.tinderclone.DB.DBHandler;
import com.example.tinderclone.DB.SharedPrefManager;
import com.example.tinderclone.Helpers.ModelsFiller;
import com.example.tinderclone.Helpers.Utils;
import com.example.tinderclone.MainWindows.MainWindowActivity;
import com.example.tinderclone.R;
import com.example.tinderclone.Users.User;
import org.json.JSONException;
import org.json.JSONObject;

public class EmailSignInActivity extends AppCompatActivity {

    private EditText emailSignInEditText;
    private EditText passwordSignInEditText;
    private TextView errorPasswordEmail;
    private Button emailSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_sign_in);

        emailSignInEditText = findViewById(R.id.emailSignInEditText);
        passwordSignInEditText = findViewById(R.id.passwordSignInEditText);
        errorPasswordEmail = findViewById(R.id.errorPasswordEmail);
        emailSignInButton = findViewById(R.id.emailSignInButton);

        emailSignInButton.setOnClickListener(v -> signIn());
    }

    private void signIn() {
        DBHandler.getUserByEmailAndPassword(EmailSignInActivity.this, emailSignInEditText.getText().toString().trim(),
                passwordSignInEditText.getText().toString().trim(), new DBHandler.VolleyCallback() {
                    @Override
                    public void onSuccess(String result) {
                        try {
                            errorPasswordEmail.setVisibility(View.INVISIBLE);
                            JSONObject obj = new JSONObject(result);

                            SharedPrefManager.getInstance(getApplicationContext()).userLogin(
                                    obj.getInt("id"),
                                    obj.getString("email"),
                                    obj.getString("password"),
                                    obj.getString("phone"));

                            User mainUser = ModelsFiller.fillUser(obj);

                            startActivity(Utils.goToNextActivity(mainUser, new Intent(EmailSignInActivity.this, MainWindowActivity.class)));
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            errorPasswordEmail.setVisibility(View.VISIBLE);
                            errorPasswordEmail.setText("Неверные данные");
                        }
                    }

                    @Override
                    public void onError(VolleyError error) { }
                });
    }
}