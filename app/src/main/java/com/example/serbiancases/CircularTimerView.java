package com.example.serbiancases;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CircularTimerView extends View {

    private Paint paint;
    private int progress;

    private String serbianWord;

    public CircularTimerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2); // Adjust stroke width as needed
        paint.setColor(Color.BLUE); // Set your desired color
        paint.setTextSize(60);
        paint.setTextAlign(Paint.Align.CENTER);

        serbianWord = "";
    }

    public void setProgress(int progress, String serbianWord) {
        this.progress = progress;
        this.serbianWord = serbianWord;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int radius = Math.min(width, height) / 2 - 2;

        // Draw the circle
        canvas.drawCircle(width / 2F, height / 2F, radius, paint);

        // Draw the progress arc
        paint.setColor(Color.RED);
        float angle = 360 * ((float) progress / 100);
        canvas.drawArc(width / 2F - radius, height / 2F - radius, width / 2F + radius, height / 2F + radius, -90, angle, false, paint);
        paint.setColor(Color.BLUE);

        Rect bounds = new Rect();
        paint.getTextBounds(serbianWord, 0, serbianWord.length(), bounds);
        int textHeight = bounds.height();
        canvas.drawText(serbianWord, width / 2F, height / 2F + textHeight / 2F, paint);
    }
}
