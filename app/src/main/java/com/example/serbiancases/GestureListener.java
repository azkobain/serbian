package com.example.serbiancases;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class GestureListener extends GestureDetector.SimpleOnGestureListener {
    private GestureListenerCallback callback;

    public interface GestureListenerCallback {
        void onSwipeLeft();
        void onSwipeRight();
        void onDoubleTap();
    }

    public GestureListener(GestureListenerCallback callback) {
        this.callback = callback;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.d("GestureListener", "onDoubleTap");
        callback.onDoubleTap();
        return true;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        // Handle swipe gesture (left or right)
        Log.d("GestureListener", "onFling");
        float deltaX = e2.getX() - e1.getX();

        if (deltaX > 0) {
            Log.d("GestureListener", "onSwipeRight");
            callback.onSwipeRight();
        } else {
            Log.d("GestureListener", "onSwipeLeft");
            callback.onSwipeLeft();
        }

        return true;
    }
}
