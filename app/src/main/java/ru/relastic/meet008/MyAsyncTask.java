package ru.relastic.meet008;

import android.os.AsyncTask;
import android.os.Handler;
import android.widget.TextView;

import java.util.Random;

public class MyAsyncTask extends AsyncTask <String, Integer, String> {
    private Handler myHandler;
    private TextView mTextView;
    private boolean interrupted = false;

    MyAsyncTask (Handler handler, TextView textView) {
        myHandler = handler;
        mTextView = textView;
    }

    @Override
    protected String doInBackground(String... strings) {
        String strRetVal = "AsynkTask is started ...";
        int i = 0;
        while(!interrupted && i<10){
            publishProgress(new Random().nextInt(899)+100);
            i++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!interrupted) {strRetVal = "AsynkTask completed ...";}
        return strRetVal;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        //Message msg = Message.obtain(myHandler, MainActivity.WHAT_MESSAGE_TEXT);
        //msg.getData().putString(MainActivity.KEY_FRAGMENT_TEXT, String.valueOf(values[0]));
        //myHandler.sendMessage(msg);
        mTextView.setText(String.valueOf(values[0]));
    }
}
