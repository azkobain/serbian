package com.example.serbiancases;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stub);

        TextView stubText = findViewById(R.id.stubText);

        // Retrieve the button name from the intent
        String buttonName = getIntent().getStringExtra("buttonName");

        // Set the text to the button name
        stubText.setText(buttonName);
    }
}
