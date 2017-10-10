package com.androidninja.wordpress.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.util.Log;


public class MyIntentService extends IntentService {
    public static final String PARAM_IN_MSG = "imsg";
    public static final String PARAM_OUT_MSG = "omsg";

    String TAG = MyIntentService.class.getSimpleName();


    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i(TAG,"onHandleIntent");
        String msg = intent.getStringExtra(PARAM_IN_MSG);
        SystemClock.sleep(10000);
        String result = msg+" "+ DateFormat.format("MM/dd/yy hh:mmaa",System.currentTimeMillis());

        //Send BC
        Intent bcintent = new Intent();
        bcintent.setAction(MainActivity.Receiover.ACTION_RESP);
        bcintent.addCategory(Intent.CATEGORY_DEFAULT);
        bcintent.putExtra(PARAM_OUT_MSG,result);
        sendBroadcast(bcintent);
    }
}


