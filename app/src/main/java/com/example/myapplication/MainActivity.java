package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private MoveButton moveButton;
    private RedrawTest redrawTest;
    final Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        rand.nextInt(8);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        redrawTest = findViewById(R.id.redrawTest);
        moveButton = findViewById(R.id.moveButton);
        moveButton.setOnTouchListener((View arg0, MotionEvent arg1) -> {
            if (arg1.getAction() == MotionEvent.ACTION_UP) {
                redrawTest.redraw(rand.nextInt(8), rand.nextInt(8), rand.nextInt(redrawTest.nChestPieces));
            }
            moveButton.performClick();
            return true;
        });
    }
}