package com.example.educationalapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    RadioGroup rgLanguage, rgTextSize;
    Button btnSave;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        rgLanguage = findViewById(R.id.rgLanguage);
        rgTextSize = findViewById(R.id.rgTextSize);
        btnSave = findViewById(R.id.btnSave);

        preferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        loadPreferences();

        btnSave.setOnClickListener(v -> savePreferences());
    }

    private void savePreferences() {
        SharedPreferences.Editor editor = preferences.edit();

        // اللغة
        int selectedLang = rgLanguage.getCheckedRadioButtonId();
        if (selectedLang == R.id.rbArabic) {
            editor.putString("language", "AR");
        } else {
            editor.putString("language", "EN");
        }

        // حجم النص
        int selectedSize = rgTextSize.getCheckedRadioButtonId();
        if (selectedSize == R.id.rbSmall) {
            editor.putString("textSize", "Small");
        } else if (selectedSize == R.id.rbLarge) {
            editor.putString("textSize", "Large");
        } else {
            editor.putString("textSize", "Medium");
        }

        editor.apply();
        finish();
    }

    private void loadPreferences() {
        String language = preferences.getString("language", "EN");
        String textSize = preferences.getString("textSize", "Medium");

        if (language.equals("AR")) {
            rgLanguage.check(R.id.rbArabic);
        } else {
            rgLanguage.check(R.id.rbEnglish);
        }

        switch (textSize) {
            case "Small":
                rgTextSize.check(R.id.rbSmall);
                break;
            case "Large":
                rgTextSize.check(R.id.rbLarge);
                break;
            default:
                rgTextSize.check(R.id.rbMedium);
        }
    }
}
