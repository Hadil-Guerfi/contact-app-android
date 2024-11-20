package com.example.tp1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText nameLogin, password;
    private Button buttonLogin;
    private DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        SharedPreferences sharedPre = getSharedPreferences("ref", Context.MODE_PRIVATE);
        String con = sharedPre.getString("connected", "false");
        if(con.equals("true")){
            Intent intent = new Intent(MainActivity.this, ShowContact.class);
            startActivity(intent);
            finish();
        }
        setContentView(R.layout.activity_main);
        nameLogin = findViewById(R.id.nameLogin);
        password = findViewById(R.id.telLogin);
        buttonLogin = findViewById(R.id.buttonLogin);
        dbHelper = new DatabaseHelper(this);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input
                String name = nameLogin.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if (name.equals("hadil")&&pass.equals("12345678")) {
                    // Successful login
                    SharedPreferences sharedPref = getSharedPreferences("ref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("connected", "true");
                    editor.apply();
                    Intent intent = new Intent(MainActivity.this, ShowContact.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Data  incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        startActivity(new Intent(MainActivity.this,ShowContact.class));
    }
}