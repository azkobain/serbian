package com.example.serbiancases;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;

public class RulesActivity extends AppCompatActivity {
    private Button prevButton;
    private Button nextButton;
    private TextView rulesTextView;
    private int state;
    private final Integer STATE_BEGIN = 1;
    private final Integer STATE_MIDDLE = 2;
    private final Integer STATE_TEST = 3;
    private final ArrayList<Pair<String, Integer>> rules = new ArrayList<Pair<String, Integer>>() {{
        add(new Pair<>("    Внимательно прочитай информацию о родах имён существительных:", STATE_BEGIN));
        add(new Pair<>("    В сербском существительные мужского рода обычно оканчиваются на согласный, иногда на " +
                "гласный -а (tata, čika, sudija и т. п.) и -о (sto, posao). В отличие от русского " +
                "языка существительные иностранного происхождения, оканчивающиеся на -о, -е, -u, -i, " +
                "относятся не к среднему, а к мужскому роду: moj auto, taj mali bife, njegov kratki " +
                "intervju, moskovski žuti taksi.", STATE_BEGIN));
        add(new Pair<>("    Существительные женского рода обычно имеют окончание -а, но некоторые оканчиваются на " +
                "согласный (kost, radost) и на -о (so «соль», misao «мысль»).", STATE_MIDDLE));
        add(new Pair<>("    Существительные среднего рода оканчиваются на -о или -е, за исключением слова doba " +
                "«период времени, пора».", STATE_MIDDLE));
        add(new Pair<>("    Теперь можешь проверить свои знания на практике или прочитать теорию ещё раз!", STATE_TEST));
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_rules);

        state = 0;

        rulesTextView = findViewById(R.id.rulesTextView);

        rulesTextView.setTextSize(18); // 18sp
        Typeface typeface = Typeface.create("sans-serif-condensed", Typeface.NORMAL);
        rulesTextView.setTypeface(typeface);

        String stateString = rules.get(state).first;
        rulesTextView.setText(stateString);

        prevButton = findViewById(R.id.prevButton);
        hideButton(prevButton);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer stateInt = rules.get(state).second;
                if (Objects.equals(stateInt, STATE_TEST))
                {
                    state = 1;
                    prevButton.setText("Назад");
                    nextButton.setText("Вперёд");
                }
                else
                {
                    --state;
                }

                String stateString = rules.get(state).first;
                stateInt = rules.get(state).second;

                if (Objects.equals(stateInt, STATE_BEGIN))
                {
                    hideButton(prevButton);
                }
                if (Objects.equals(stateInt, STATE_MIDDLE))
                {
                    prevButton.setText("Назад");
                    nextButton.setText("Вперёд");
                }
                rulesTextView.setText(stateString);
            }
        });

        nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer stateInt = rules.get(state).second;
                if (Objects.equals(stateInt, STATE_TEST))
                {
                    Intent intent = new Intent(RulesActivity.this, GenderActivity.class);
                    startActivity(intent);
                }
                else
                {
                    state++;

                    String stateString = rules.get(state).first;
                    stateInt = rules.get(state).second;

                    if (Objects.equals(stateInt, STATE_BEGIN))
                    {
                        hideButton(prevButton);
                    }
                    else
                    {
                        showButton(prevButton);
                    }

                    if (Objects.equals(stateInt, STATE_TEST))
                    {
                        prevButton.setText("Повторить");
                        nextButton.setText("Тест");
                    }
                    rulesTextView.setText(stateString);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void hideButton(Button button)
    {
        button.setVisibility(View.INVISIBLE);
        button.setClickable(false);
    }
    private void showButton(Button button)
    {
        button.setVisibility(View.VISIBLE);
        button.setClickable(true);
    }
}
