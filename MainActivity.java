package com.example.educationalapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tvContent;
    Button btnSettings;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvContent = findViewById(R.id.tvContent);
        btnSettings = findViewById(R.id.btnSettings);

        preferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        applyPreferences();

        btnSettings.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        applyPreferences();
    }

    private void applyPreferences() {
        String language = preferences.getString("language", "EN");
        String textSize = preferences.getString("textSize", "Medium");

        // تغيير اللغة (نص تجريبي)
        if (language.equals("AR")) {
            tvContent.setText("مرحبًا بك في التطبيق التعليمي");
        } else {
            tvContent.setText("Welcome to the Educational App");
        }

        // تغيير حجم النص
        switch (textSize) {
            case "Small":
                tvContent.setTextSize(14);
                break;
            case "Large":
                tvContent.setTextSize(24);
                break;
            default:
                tvContent.setTextSize(18);
        }
    }
}
