package ru.relastic.meet008;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private  final Context mContext;
    private final ArrayList<String> mData;

    MyAdapter(Context context, ArrayList<String> data) {
        mContext = context;
        mData = (data == null) ? new ArrayList<String>() : data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LinearLayout mLayout = new LinearLayout(mContext);
        TextView mTextView = new TextView(mContext);
        mTextView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        mLayout.addView(mTextView);
        return new MyViewHolder(mLayout, mTextView);
    }

    @Override
    public void onBindViewHolder (@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.mTextView.setText(mData.get(i));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addString(String text){
        mData.add(text);
    }


    public class MyViewHolder extends  RecyclerView.ViewHolder {
        public TextView mTextView;
        private final MainActivity activity;

        public MyViewHolder(View itemView, TextView textView){
            super(itemView);
            activity = (MainActivity)itemView.getContext();
            mTextView = textView;
        }
    }
}
