package com.example.remindme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.widget.Toast;

public class My_Alarms extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Vibrator v = (Vibrator)context.getSystemService(context.VIBRATOR_SERVICE);
        v.vibrate(900);
    }
}
