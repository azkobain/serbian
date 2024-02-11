package com.example.serbiancases;

import static java.lang.String.valueOf;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.animation.LinearInterpolator;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class FallingWordsActivity extends AppCompatActivity implements GestureListener.GestureListenerCallback {
    private List<QuestionGender> questions;
    private int[] randomSequence;
    private GestureDetector gestureDetector;
    private TextView fallingBlock;
    private TextView scoreGender;
    private int currIndex = 0;
    private int score = 0;
    private String serb;
    private String rus;
    private String gender;
    private int points;
    private String answer;
    private ObjectAnimator fallAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_falling_words);

        questions = XmlGenderParser.parseXml(this, "NounsGender1.xml");
        randomSequence = RandomSequenceGenerator.generateRandomSequence(questions.size());

        scoreGender = findViewById(R.id.scoreGender);
        scoreGender.setText(valueOf(score));

        serb = questions.get(randomSequence[currIndex]).getSerb();
        rus = questions.get(randomSequence[currIndex]).getRus();
        gender = questions.get(randomSequence[currIndex]).getGender();
        points = questions.get(randomSequence[currIndex++]).getPoints();
        if (currIndex == questions.size())
            currIndex = 0;

        fallingBlock = findViewById(R.id.fallingBlock);
        fallingBlock.setText(serb);

        gestureDetector = new GestureDetector(this, new GestureListener(this));

        fallAnimator = ObjectAnimator.ofFloat(
                fallingBlock,
                "translationY",
                300f,  // Starting position (top of the screen)
                getResources().getDisplayMetrics().heightPixels  // Ending position (bottom of the screen)
        );
        fallAnimator.setInterpolator(new LinearInterpolator());

        // Set up the falling block animation or logic
        // For simplicity, you can use a TextView to represent the falling block
        // and animate it using ObjectAnimator or other animation techniques.
        startFallingAnimation();
    }
    private void startFallingAnimation() {
        // Set up the falling animation


        fallAnimator.setDuration(4000 - 500L * score / 10);  // Duration of the animation in millisecond

        fallAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d("onAnimationEnd", "onAnimationEnd");
                // Animation ends, handle it accordingly (e.g., reset position)
                fallAnimator.setDuration(4000 - 500L * score / 10);
                Log.d("onAnimationEnd", Integer.toString(4000 - 500 * score / 10));
                fallingBlock.setTranslationY(300f);
                serb = questions.get(randomSequence[currIndex]).getSerb();
                rus = questions.get(randomSequence[currIndex]).getRus();
                gender = questions.get(randomSequence[currIndex]).getGender();
                points = questions.get(randomSequence[currIndex++]).getPoints();
                if (currIndex == questions.size())
                    currIndex = 0;
                fallingBlock.setText(serb);
                startFallingAnimation();  // Restart the falling animation
            }
        });

        fallAnimator.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Pass touch events to the GestureDetector for gesture detection
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    @Override
    public void onSwipeLeft() {
        // Handle swipe to the left
        // You can perform actions or call methods in MiniGameActivity
        // based on the detected gesture
        // Example: update the falling block's text
        answer = "F";
        checkAnswer();
    }

    @Override
    public void onSwipeRight() {
        answer = "M";
        checkAnswer();
    }

    @Override
    public void onDoubleTap() {
        answer = "N";
        checkAnswer();
    }

    void checkAnswer()
    {
        if (answer.equals(gender))
        {
            Log.d("checkAnswer", "answer == gender");
            score += points;
            scoreGender.setText(valueOf(score));

            fallAnimator.setDuration(4000 - 500L * score / 10);
            fallingBlock.setTranslationY(300f);
            serb = questions.get(randomSequence[currIndex]).getSerb();
            rus = questions.get(randomSequence[currIndex]).getRus();
            gender = questions.get(randomSequence[currIndex]).getGender();
            points = questions.get(randomSequence[currIndex++]).getPoints();
            if (currIndex == questions.size())
                currIndex = 0;
            fallingBlock.setText(serb);
            startFallingAnimation();
        }
        answer = "";
    }
}
