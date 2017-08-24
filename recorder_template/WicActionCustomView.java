package com.your.package;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.calldorado.android.ui.views.custom.CalldoradoCustomView;
import callidentifier.record.voice.R;

public class WicActionCustomView extends CalldoradoCustomView {

    private RelativeLayout wicButtonLayout;
    private ImageButton wicButton1;
    private boolean isVoiceRecordingPossible = false;
    private Context context;
    private static final String TAG = "WicActionFragment";
    public static final String CALL_RECORDING = "com.calldorado.android.intent.CALL_RECORDING";

    public WicActionCustomView(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public View getRootView() {
        //Additions to the WIC (Will Identify Call -overlay view during phone call) should be simple and stable. Larger, non squared
        //images may look bad. Code executed here should be very stable as crashes while we use the overlay, can cause a device to hang
        //for several minutes, which can prevent any action for a user during this time interval.

        //Setting up a simple button with a color click identifier programmatically
        RelativeLayout.LayoutParams wicButtonLayoutLP = new RelativeLayout.LayoutParams(convertDpToPixel(30), convertDpToPixel(30));
        wicButtonLayout = new RelativeLayout(context);
        wicButtonLayout.setLayoutParams(wicButtonLayoutLP);
        RelativeLayout.LayoutParams wicButton1LP = new RelativeLayout.LayoutParams(convertDpToPixel(30), convertDpToPixel(30));
        wicButton1LP.addRule(RelativeLayout.CENTER_IN_PARENT);
        wicButton1 = new ImageButton(context);
        wicButton1.setLayoutParams(wicButton1LP);
        wicButton1.setBackgroundResource(R.drawable.button_cdo);
        wicButton1.setClickable(true);
        wicButton1.setPadding(convertDpToPixel(5), convertDpToPixel(5), convertDpToPixel(5), convertDpToPixel(5));
        wicButton1.setImageDrawable(context.getResources().getDrawable(R.drawable.rec_cdo));
        wicButton1.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        isVoiceRecordingPossible = true;  //add some logic here
        Log.d(TAG, "isVoiceRecordingPossible = " + isVoiceRecordingPossible);

        //user asked to record next call
        SharedPreferences recordStatePreference = context.getSharedPreferences("callRecordFeature", 0);
        if (recordStatePreference.getBoolean("shouldRecordNextCall", false)) {
            String name = getContactName();
            String phoneNo = getPhoneNumber();
            Log.d(TAG, "record activated by user on previous aftercall:    name = " + name + ",      phoneNo = " + phoneNo);
            Intent intent = new Intent();
            intent.setAction(CALL_RECORDING);
            intent.putExtra("phoneNumber", phoneNo);
            intent.putExtra("name", name);
            intent.putExtra("fromCdo", true);
            intent.putExtra("isStart", true);
            context.sendBroadcast(intent);  //informing a broadcast receiver to start recording. Record software is not part of of this template
            Log.d(TAG, "Wic -pre-ordered recording triggered");
            wicButton1.setClickable(false);
            isVoiceRecordingPossible = false;
            recordStatePreference.edit().putBoolean("shouldRecordNextCall", false).commit();
        }

        if (isVoiceRecordingPossible) {
            wicButton1.getDrawable().clearColorFilter();
            wicButton1.setClickable(true);
        } else {
            wicButton1.getDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
            wicButton1.setClickable(false);
        }

        wicButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "wic recording pressed");
                if (isVoiceRecordingPossible) {
                    String name = getContactName();
                    String phoneNo = getPhoneNumber();
                    Log.d(TAG, "onClick()    name = " + name + ",      phoneNo = " + phoneNo);
                    Intent intent = new Intent();
                    intent.setAction(CALL_RECORDING);
                    intent.putExtra("phoneNumber", phoneNo);
                    intent.putExtra("name", name);
                    intent.putExtra("fromCdo", true);
                    intent.putExtra("isStart", true);
                    context.sendBroadcast(intent); //informing a broadcast receiver to start recording. Record software is not part of of this template
                    Log.d(TAG, "Wic Call recording button receiver sent");
                    wicButton1.setClickable(false);
                    isVoiceRecordingPossible = false;
                    wicButton1.getDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                }
            }
        });
        wicButtonLayout.addView(wicButton1);
        return wicButtonLayout;
    }
}

