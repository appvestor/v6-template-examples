package com.email.email.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.calldorado.Calldorado;
import com.email.email.BuildConfig;
import com.email.email.views.AftercallCustomView;

/**
 * Created by Jussi on 16-Aug-17.
 */

public class SetupCustomViewReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.calldorado.android.intent.SEARCH") ||
                intent.getAction().equals("android.intent.action.PHONE_STATE")) {
            Calldorado.setAftercallCustomView(new AftercallCustomView(context));
        }
    }
}
