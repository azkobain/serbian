package com.example.serbiancases;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ConfigManager configManager = new ConfigManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button nounsButton = findViewById(R.id.nounsButton);
        Button adjectivesButton = findViewById(R.id.adjectivesButton);
        Button pronounsButton = findViewById(R.id.pronounsButton);
        Button verbsButton = findViewById(R.id.verbsButton);

        nounsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToActivity("Nouns");
            }
        });

        adjectivesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToStubScreen("Adjectives");
            }
        });

        pronounsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToStubScreen("Pronouns");
            }
        });

        verbsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToStubScreen("Verbs");
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
            case "Nouns":
                intent = new Intent(this, NounsActivity.class);
                break;
            default:
                intent = new Intent(this, StubActivity.class);
                break;
        }
        intent.putExtra("buttonName", buttonName);
        startActivity(intent);
    }
}
