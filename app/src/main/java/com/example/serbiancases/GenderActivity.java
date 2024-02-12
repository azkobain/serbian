package com.example.serbiancases;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.thecode.aestheticdialogs.AestheticDialog;
import com.thecode.aestheticdialogs.DialogStyle;
import com.thecode.aestheticdialogs.DialogType;
import com.thecode.aestheticdialogs.OnDialogClickListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;

public class GenderActivity extends AppCompatActivity {
    private ConstraintLayout rootLayout;
    private CircularTimerView circularTimerView;
    private CountDownTimer countDownTimer;
    private ImageView[] imageViews;
    private int currImage = -1;

    int pTouchX = -1, pTouchY = -1;

    private int[] imageLocation = new int[2];

    private final String[] answers = new String[]{"M", "F", "N"};
    private String answer;
    private int score = 0;
    private TextView scoreView;

    private String serbianWord;
    private String russianWord;
    private String gender;
    private int points;

    private List<QuestionGender> questions, restQuestions;
    private int[] randomSequence;
    private int currentQuestion = 0;

    AestheticDialog.Builder errDialog, okDialog;

    long lastTouchTime;

    private final int NO_QUESTION = 0;
    private final int NEXT_QUESTION = 1;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_gender);

        lastTouchTime = System.currentTimeMillis();

        errDialog = new AestheticDialog.Builder(this, DialogStyle.FLASH, DialogType.ERROR);
        errDialog.setOnClickListener(new OnDialogClickListener() {
            @Override
            public void onClick(AestheticDialog.Builder dialog) {
//                Log.d("errDialog", "onClick");
                restQuestions.add(new QuestionGender(serbianWord, russianWord, gender, points));
                nextQuestion();
                dialog.dismiss();
            }
        });

        okDialog = new AestheticDialog.Builder(this, DialogStyle.FLASH, DialogType.SUCCESS);
        okDialog.setOnClickListener(new OnDialogClickListener() {
            @Override
            public void onClick(AestheticDialog.Builder dialog) {
                dialog.dismiss();
                Intent intent = new Intent(GenderActivity.this, RulesActivity.class);
                startActivity(intent);
            }
        });

        scoreView = findViewById(R.id.scoreGender);
        String tmp = "Score: " + score;
        scoreView.setText(tmp);

        ImageView imageMale = findViewById(R.id.male);
        ImageView imageFemale = findViewById(R.id.female);
        ImageView imageNeutral = findViewById(R.id.neutral);
        imageViews = new ImageView[]{imageMale, imageFemale, imageNeutral};
        int[] boxOpen = new int[]{R.drawable.m_open, R.drawable.f_open, R.drawable.n_open};
        int[] boxClose = new int[]{R.drawable.m_close, R.drawable.f_close, R.drawable.n_close};

        rootLayout = findViewById(R.id.genderGameLayout);
        rootLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                int touchX, touchY;

                switch (action) {
                    case MotionEvent.ACTION_DOWN:
//                        Log.d("ACTION_DOWN", "ACTION_DOWN");
                        long downTime = System.currentTimeMillis();
                        if (downTime - lastTouchTime < 500)
                        {
                            break;
                        }

                        pTouchX = touchX = (int) event.getRawX();
                        pTouchY = touchY = (int) event.getRawY();

//                        String out = Float.toString(touchX) + " " + Float.toString(touchY);
//                        Log.d("ACTION_DOWN", out);

                        if (currImage != -1)
                            imageViews[currImage].setImageResource(boxClose[currImage]);

                        for (int i = 0; i < 3; ++i)
                        {
                            imageViews[i].getLocationOnScreen(imageLocation);
                            if (touchX >= imageLocation[0] && touchX <= imageLocation[0] + imageViews[i].getWidth()
                                    && touchY >= imageLocation[1] && touchY <= imageLocation[1] + imageViews[i].getHeight())
                            {
                                currImage = i;
                                imageViews[currImage].setImageResource(boxOpen[currImage]);
//                                Log.d("ACTION_DOWN", "boxOpen");
                                break;
                            }
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
//                        Log.d("ACTION_MOVE", "ACTION_MOVE");
                        long moveTime = System.currentTimeMillis();
                        if (moveTime - lastTouchTime < 500)
                        {
                            break;
                        }

                        touchX = (int) event.getRawX();
                        touchY = (int) event.getRawY();
                        if (touchX == pTouchX && touchY == pTouchY)
                            break;
                        pTouchX = touchX;
                        pTouchY = touchY;
//                        String out1 = "touchXY = " + touchX + " " + touchY;
//                        Log.d("ACTION_DOWN", out1);

                        for (int i = 0; i < 3; ++i)
                        {
//                            out1 = "i = " + i;
//                            Log.d("ACTION_MOVE", out1);
//                            out1 = "currImage = " + currImage;
//                            Log.d("ACTION_MOVE", out1);
                            imageViews[i].getLocationOnScreen(imageLocation);
//                            out1 = "Image x " + imageLocation[0] + " to " + (imageLocation[0] + imageViews[i].getWidth()) +
//                                    ", y " + imageLocation[1] + " to " + (imageLocation[1] + imageViews[i].getHeight());
//                            Log.d("ACTION_MOVE", out1);
                            if (touchX >= imageLocation[0] && touchX <= imageLocation[0] + imageViews[i].getWidth()
                                    && touchY >= imageLocation[1] && touchY <= imageLocation[1] + imageViews[i].getHeight())
                            {
                                if (currImage == i)
                                    break;
                                if (currImage != -1)
                                    imageViews[currImage].setImageResource(boxClose[currImage]);
//                                Log.d("ACTION_MOVE", "boxOpen");
                                currImage = i;
                                imageViews[currImage].setImageResource(boxOpen[currImage]);
                                break;
                            }
                            else if (currImage != -1)
                            {
//                                Log.d("ACTION_MOVE", "boxClose");
                                imageViews[currImage].setImageResource(boxClose[currImage]);
                                currImage = -1;
                            }
                        }
                        break;
                    case MotionEvent.ACTION_UP:
//                        Log.d("ACTION_UP", "ACTION_UP");
                        long upTime = System.currentTimeMillis();
                        if (upTime - lastTouchTime >= 500)
                        {
                            if (currImage != -1)
                            {
//                            Log.d("ACTION_UP", "boxClose");
                                answer = answers[currImage];
                                imageViews[currImage].setImageResource(boxClose[currImage]);
                                checkAnswer();
                                currImage = -1;
                            }
                        }
                        lastTouchTime = upTime;

                        break;
                }

                return true;
            }
        });

        questions = XmlGenderParser.parseXml(this, "NounsGender1.xml");
        restQuestions = new ArrayList<>();
        randomSequence = RandomSequenceGenerator.generateRandomSequence(questions.size());
        serbianWord = questions.get(randomSequence[currentQuestion]).getSerbian();
        russianWord = questions.get(randomSequence[currentQuestion]).getRussian();
        gender = questions.get(randomSequence[currentQuestion]).getGender();
        points = questions.get(randomSequence[currentQuestion++]).getPoints();

        circularTimerView = findViewById(R.id.circularTimerView);

        setupCountDownTimer(10000);
        countDownTimer.start();
    }

    private void setupCountDownTimer(long millisInFuture) {
        countDownTimer = new CountDownTimer(millisInFuture, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int progress = (int) ((millisUntilFinished / 10000.0) * 100);
                circularTimerView.setProgress(progress, serbianWord);
            }

            @Override
            public void onFinish() {
                answer = "";
                checkAnswer();
            }
        };
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel(); // Important to cancel the timer when the activity is destroyed
    }
    void nextQuestion()
    {
        int res = prepareNextQuestion();

        countDownTimer.cancel();
        if (res == NEXT_QUESTION)
        {
            setupCountDownTimer(10000);
            countDownTimer.start();
            answer = "";
        }

    }
    void checkAnswer()
    {
        if (answer.equals(gender))
        {
            score += points;
            String tmp = "Score: " + score;
            scoreView.setText(tmp);
            nextQuestion();
        }
        else
        {
            countDownTimer.cancel();
            String gen = gender.equals("M")? "Мужской": (gender.equals("F")? "Женский": "Средний");
            String tmp = serbianWord + "\n" + russianWord + "\n" + gen;
            errDialog.setCancelable(false).setTitle("Запомните!").setMessage(tmp).show();
        }
    }
    int prepareNextQuestion()
    {
        if (currentQuestion == questions.size())
        {
            if (restQuestions.size() == 0)
            {
//                Log.d("WINWIN","You win");
                countDownTimer.cancel();
                okDialog.setCancelable(false).setTitle("Вы молодец!!!").
                        setMessage("Не осталось неотвеченных вопросов.").show();
                return NO_QUESTION;
            }
            else
            {
                questions = new ArrayList<>(restQuestions);
                Log.d("question.size", Integer.toString(questions.size()));
                restQuestions.clear();
                randomSequence = RandomSequenceGenerator.generateRandomSequence(questions.size());
                currentQuestion = 0;
            }
        }
//        Log.d("STRANGE", "After return");
        serbianWord = questions.get(randomSequence[currentQuestion]).getSerbian();
        russianWord = questions.get(randomSequence[currentQuestion]).getRussian();
        gender = questions.get(randomSequence[currentQuestion]).getGender();
        points = questions.get(randomSequence[currentQuestion++]).getPoints();
        return NEXT_QUESTION;
    }
}
