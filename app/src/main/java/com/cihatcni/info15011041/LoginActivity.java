package com.cihatcni.info15011041;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void signIn(View view) {

        EditText username = findViewById(R.id.usernameText);
        EditText password = findViewById(R.id.passwordText);

        if(username.getText().toString().equals("admin")
            && password.getText().toString().equals("password")) {

            Toast.makeText(this, "Giriş yapıldı.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();

        }
        else {
            Toast.makeText(this, "Hatalı bilgi girdiniz.", Toast.LENGTH_SHORT).show();
        }

    }
}
