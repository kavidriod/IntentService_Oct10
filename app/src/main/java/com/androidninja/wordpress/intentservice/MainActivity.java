package com.androidninja.wordpress.intentservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//https://www.survivingwithandroid.com/2014/01/android-service-tutorial-2.html
/*IntentService
1.This class is useful when we don’t need to handle multiple requests at the same time.
2.create a separate thread to handle the request
3.create a request queue and pass one Intent at time
4.create a default implementation of onStartCommand
5.stop the service when all the requests are processed*/

public class MainActivity extends AppCompatActivity {

    Button intentserviceButton;
    EditText intentserviceEditText;
    TextView intentserviceTextViewResule;

String TAG = MainActivity.class.getSimpleName();
    Receiover receiover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerReceiver();

        intentserviceButton = (Button) findViewById(R.id.intentserviceButton);
        intentserviceEditText = (EditText) findViewById(R.id.intentserviceEditText);
        intentserviceTextViewResule = (TextView) findViewById(R.id.intentserviceTextViewResule);

        intentserviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input  = intentserviceEditText.getText().toString();
                Intent intent = new Intent(getApplicationContext(),MyIntentService.class);
                intent.putExtra(MyIntentService.PARAM_IN_MSG,input);
                startService(intent);
            }
        });
    }

    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter(Receiover.ACTION_RESP);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        receiover = new Receiover();
        registerReceiver(receiover,intentFilter);
    }

    public  class  Receiover extends BroadcastReceiver{
       public  static  final String ACTION_RESP = "com.androidninja.wordpress.intentservice.MainActivity";
         String TAG = Receiover.class.getSimpleName();

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG,"onReceive ");
            String result =  intent.getStringExtra(MyIntentService.PARAM_OUT_MSG);
            intentserviceTextViewResule.setText(result);
        }
    }


}
