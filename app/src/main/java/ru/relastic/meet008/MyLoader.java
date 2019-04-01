package ru.relastic.meet008;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

public class MyLoader extends AsyncTaskLoader <Integer> {
    int newColor = Color.BLACK;

    public MyLoader(@NonNull Context context, Bundle args) {
        super(context);
        newColor = args.getInt(MainActivity.KEY_FRAGMENT_COLOR);
    }

    @Nullable
    @Override
    public Integer loadInBackground() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return newColor;
    }
}
