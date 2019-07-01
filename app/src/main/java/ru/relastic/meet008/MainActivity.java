package ru.relastic.meet008;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public final static int LOADER_ID = 1111;

    public final static String KEY_FRAGMENT_COLOR = "key_fragment_color";
    public final static String KEY_FRAGMENT_DATA = "key_fragment_data";

    public final static int WHAT_MESSAGE_DATA = 1000;

    private MyFragment myFragment1, myFragment2, myFragment3;

    private MyAdapter mAdapter;
    private static boolean isFirstStart = false;
    private MyHandler myHandler = new MyHandler();
    private MyLoaderCallbacks myLoaderCallbacks = new MyLoaderCallbacks();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isFirstStart = (savedInstanceState==null);
        initViews();
        initListeners();
        init();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        TextView textView = new TextView(getApplicationContext());
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundColor(Color.MAGENTA);
        textView.setText("Это область фрагмента");
        textView.setTag("mTextView");
        textView.setId((int)5787L);
        myFragment2.getLayout().addView(textView);

        RecyclerView mRecyclerView = new RecyclerView(getApplicationContext());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        ArrayList<String> data = myFragment3.getArguments().getStringArrayList(KEY_FRAGMENT_DATA);
        myFragment3.getArguments().putStringArrayList(KEY_FRAGMENT_DATA, data);
        mAdapter = new MyAdapter(this, data);
        mRecyclerView.setAdapter(mAdapter);
        myFragment3.getLayout().addView(mRecyclerView);

        Bundle args = new Bundle();
        args.putInt(KEY_FRAGMENT_COLOR, Color.MAGENTA);
        getSupportLoaderManager().initLoader(LOADER_ID, args, myLoaderCallbacks);
        MyAsyncTask task = new MyAsyncTask(myHandler, textView);
        task.execute("");
        MyThread thread = new MyThread(myHandler);
        thread.startThread();
    }


    private void initViews() {

    }
    private void initListeners() {

    }
    private void init() {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction;
        if (isFirstStart) {
            fragmentTransaction = fragmentManager.beginTransaction();

            Bundle bundle;
            myFragment1 = new MyFragment();
            bundle = myFragment1.getArguments();
            if (bundle == null) {bundle = new Bundle();}
            bundle.putInt(MyFragment.KEY_BACKGROUNDCOLOR,Color.YELLOW);
            myFragment1.setArguments(bundle);
            fragmentTransaction.add(R.id.layout_top, myFragment1, "myFragment1");

            myFragment2 = new MyFragment();
            bundle = myFragment2.getArguments();
            if (bundle == null) {bundle = new Bundle();}
            bundle.putInt(MyFragment.KEY_BACKGROUNDCOLOR,Color.LTGRAY);
            myFragment2.setArguments(bundle);
            fragmentTransaction.add(R.id.layout_lower_left, myFragment2, "myFragment2");

            myFragment3 = new MyFragment();
            bundle = myFragment3.getArguments();
            if (bundle == null) {bundle = new Bundle();}
            bundle.putInt(MyFragment.KEY_BACKGROUNDCOLOR,Color.CYAN);
            myFragment3.setArguments(bundle);
            fragmentTransaction.add(R.id.layout_lower_right, myFragment3, "myFragment3");

            fragmentTransaction.commit();
        }else {
            myFragment1 = (MyFragment) fragmentManager.findFragmentByTag("myFragment1");
            myFragment2 = (MyFragment) fragmentManager.findFragmentByTag("myFragment2");
            myFragment3 = (MyFragment) fragmentManager.findFragmentByTag("myFragment3");
        }
    }

    class MyLoaderCallbacks implements LoaderManager.LoaderCallbacks<Integer> {

        @NonNull
        @Override
        public Loader<Integer> onCreateLoader(int i, @Nullable Bundle bundle) {
            Loader<Integer> mLoader = null;
            if (i == LOADER_ID) {
                mLoader = new MyLoader(MainActivity.this, bundle);
            }
            return  mLoader;
        }

        @Override
        public void onLoadFinished(@NonNull Loader<Integer> loader, Integer integer) {
            myFragment1.getLayout().setBackgroundColor(integer);
            if (((MyLoader)loader).getColor()!=Color.GREEN) {
                if (((MyLoader)loader).getColor()==Color.MAGENTA){
                    ((MyLoader)loader).setColor(Color.CYAN);
                }else {
                    ((MyLoader)loader).setColor(Color.GREEN);
                }
                loader.startLoading();
            }
        }

        @Override
        public void onLoaderReset(@NonNull Loader<Integer> loader) {
            Log.v("LOG:", "onLoaderReset");
        }
    }

    class MyHandler extends Handler {
        MyHandler() {
            super(Looper.getMainLooper());
        }
        @Override
        public void handleMessage(Message msg) {
            String text;
            switch (msg.what) {
                case WHAT_MESSAGE_DATA:
                    mAdapter.addString(msg.getData().getString(KEY_FRAGMENT_DATA));
                    mAdapter.notifyItemChanged(mAdapter.getItemCount());
                    break;
            }
        }
    }
}
