package com.your.package;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.calldorado.Calldorado;

public class CustomViewReceiver extends BroadcastReceiver {

    private final static String TAG = "SetupFragmntReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.calldorado.android.intent.SEARCH") ||
                intent.getAction().equals("android.intent.action.PHONE_STATE")) {
            Calldorado.setAftercallCustomView(new AftercallCustomView(context));
            Calldorado.setWicActionCustomView(new WicActionCustomView(context));
        }
    }
}
