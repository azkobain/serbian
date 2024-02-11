package com.example.serbiancases;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class NounsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouns);

        Button genderButton = findViewById(R.id.genderButton);
        Button pluralButton = findViewById(R.id.pluralButton);
        Button casesButton = findViewById(R.id.casesButton);

        genderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToActivity("Gender");
            }
        });

        pluralButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToStubScreen("Plural");
            }
        });

        casesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToStubScreen("Cases");
            }
        });
    }

    private void navigateToStubScreen(String buttonName) {
        Intent intent = new Intent(this, StubActivity.class);
        intent.putExtra("buttonName", buttonName);
        startActivity(intent);
    }

    private void navigateToActivity(String buttonName) {
        Intent intent;
        switch (buttonName)
        {
            case "Gender":
                intent = new Intent(this, FallingWordsActivity.class);
                break;
            default:
                intent = new Intent(this, StubActivity.class);
                break;
        }
        intent.putExtra("buttonName", buttonName);
        startActivity(intent);
    }
}
