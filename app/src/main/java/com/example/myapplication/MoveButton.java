package com.example.myapplication;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

public class MoveButton extends AppCompatButton {
    public MoveButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean performClick() {
        super.performClick();
        return true;
    }
}
