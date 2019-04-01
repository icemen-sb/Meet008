package ru.relastic.meet008;

import android.os.Handler;
import android.os.Message;

import java.util.Random;

public class MyThread implements Runnable {
    private Handler myHandler;
    private boolean interrupted = false;

    MyThread (Handler handler) {
        myHandler = handler;
    }

    @Override
    public void run() {
        int i = 0;
        while (!interrupted && i<10) {
            Message msg = Message.obtain(myHandler, MainActivity.WHAT_MESSAGE_DATA);
            Random rnd= new Random();
            rnd.nextInt();
            msg.getData().putString(MainActivity.KEY_FRAGMENT_DATA, "Data value: " +
                    String.valueOf(new Random().nextInt(899)+100));
            myHandler.sendMessage(msg);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }

    public void startThread(){
        Thread t = new Thread(this);
        t.start();
    }
    public void stopThread(){
        interrupted = true;
    }
}
