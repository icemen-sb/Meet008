package ru.relastic.meet008;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MyFragment extends Fragment {
    public static String KEY_BACKGROUNDCOLOR = "key_backgroundcolor";
    private LinearLayout mLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Context context = getActivity().getApplicationContext();
        mLayout = new LinearLayout(context);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mLayout.setBackgroundColor(arguments.getInt(KEY_BACKGROUNDCOLOR));
        }
        return mLayout;
    }
    public LinearLayout getLayout() {
        return mLayout;
    }

}
