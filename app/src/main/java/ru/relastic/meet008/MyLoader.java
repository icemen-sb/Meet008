package ru.relastic.meet008;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

public class MyLoader extends AsyncTaskLoader <Integer> {
    private @ColorInt int newColor;

    MyLoader(@NonNull Context context, Bundle args) {
        super(context);
        newColor = args.getInt(MainActivity.KEY_FRAGMENT_COLOR);
    }

    @Nullable
    @Override
    public Integer loadInBackground() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return newColor;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        onForceLoad();
    }

    void setColor(@ColorInt int color) {
        newColor = color;
    }
    @ColorInt int getColor() {
        return newColor;
    }
}
