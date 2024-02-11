package com.example.serbiancases;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NounsGenderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouns_gender);

        Button infoButton = findViewById(R.id.infoButton);
        Button testButton = findViewById(R.id.testButton);

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToStubScreen("Information about Genders");
            }
        });

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToStubScreen("Test for Genders");
            }
        });

    }

    private void navigateToStubScreen(String buttonName) {
        Intent intent = new Intent(this, StubActivity.class);
        intent.putExtra("buttonName", buttonName);
        startActivity(intent);
    }
}