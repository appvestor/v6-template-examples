package com.your.package;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.AudioManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.calldorado.android.ui.views.custom.CalldoradoCustomView;
import callidentifier.record.voice.AftercallPlayer;
import callidentifier.record.voice.AftercallRecorder;
import callidentifier.record.voice.R;


public class AftercallCustomView extends CalldoradoCustomView {

    private RelativeLayout ll;
    private LinearLayout playerBar, buttonContainer2;
    private Button recCallButton, recVoiceButton, lastCallButton;
    private ImageButton shareButton, settingsButton, fileButton, playButton, saveButton, pauseButton, stopButton, deleteButton, recButton;
    private RelativeLayout playButtonLayout, pauseButtonLayout, stopButtonLayout, saveButtonLayout, deleteButtonLayout, recButtonLayout;
    private static final String TAG = AftercallCustomView.class.getName();
    private boolean isVoiceRecordingFromMainApp = false, willRecordNextCall = false, anyAvailableRecordings = false;
    private Context context;
    private AftercallRecorder recorder = null;
    private AftercallPlayer player = null;
    private int prevSTREAM_DTMFVolume = -1;
    private AudioManager audioManager = null;

    public AftercallCustomView(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public View getRootView() {
        Log.d(TAG, "getRootView()");
        ll = (RelativeLayout) inflate(context, R.layout.fragment_aftercall, getRelativeViewGroup());
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        prevSTREAM_DTMFVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC); //reading user settings volume to reset later
        lastCallButton = (Button) ll.findViewById(R.id.aftercall_button1);
        recCallButton = (Button) ll.findViewById(R.id.aftercall_button2);
        recVoiceButton = (Button) ll.findViewById(R.id.aftercall_button3);
        shareButton = (ImageButton) ll.findViewById(R.id.aftercall_button4);
        settingsButton = (ImageButton) ll.findViewById(R.id.aftercall_button5);
        fileButton = (ImageButton) ll.findViewById(R.id.aftercall_button6);
        playerBar = (LinearLayout) ll.findViewById(R.id.ac_Playerlayout);
        buttonContainer2 = (LinearLayout) ll.findViewById(R.id.button_cont_vc_id);
        playerBar.setVisibility(View.GONE);
        buttonContainer2.setVisibility(View.GONE);

        final SharedPreferences recordStatePreference = context.getSharedPreferences("callRecordFeature", 0);
        //
        boolean lastCallWasRecorded = recordStatePreference.getBoolean("wasLastCallRecorded", false);
        Log.d (TAG, "lastCallWasRecorded = " + lastCallWasRecorded);
        if (lastCallWasRecorded) {
            recordStatePreference.edit().putBoolean("wasLastCallRecorded", false).apply();
            //add tracking or logic depending on whether the just finished call was recorded. Preference set to true in recording service if call recorded
        }
        anyAvailableRecordings = isAnyRecordingPresent();
        if (!anyAvailableRecordings) {
            lastCallButton.setVisibility(View.GONE);
            shareButton.setVisibility(View.GONE);
        }
        lastCallButton.setClickable(true);
        recCallButton.setClickable(true);
        recVoiceButton.setClickable(true);
        shareButton.setClickable(true);
        settingsButton.setClickable(true);
        fileButton.setClickable(true);

        lastCallButton.setText("PLAY LAST RECORD"); //hardcoding strings for this template
        lastCallButton.setTextColor(Color.parseColor("#FFFFFF"));

        recCallButton.setText("REC NEXT CALL");
        recCallButton.setTextColor(Color.parseColor("#FFFFFF"));

        isVoiceRecordingFromMainApp = false; //add logic to disable playing & recording if user is already recording in the hosting app
        if (isVoiceRecordingFromMainApp) {
            lastCallButton.setVisibility(View.GONE);
            recVoiceButton.setClickable(false);
        }
        recVoiceButton.setText("REC VOICE");
        recVoiceButton.setTextColor(Color.parseColor("#FFFFFF"));

        recCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Record call button pressed");
                if (willRecordNextCall) {
                    recCallButton.setCompoundDrawablesWithIntrinsicBounds(null, null, context.getResources().getDrawable(R.drawable.rec_off), null);
                    willRecordNextCall = false;
                    recordStatePreference.edit().putBoolean("shouldRecordNextCall", false).apply(); //to be read by phone state receiver to trigger recording
                } else {
                    recCallButton.setCompoundDrawablesWithIntrinsicBounds(null, null, context.getResources().getDrawable(R.drawable.rec_on), null);
                    willRecordNextCall = true;
                    recordStatePreference.edit().putBoolean("shouldRecordNextCall", true).apply(); //to be read by phone state receiver to trigger recording
                }
            }
        });

        lastCallButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Last call button pressed");
                generateButtonContainer(1);
            }
        });

        recVoiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Rec voice button pressed");
                if (!isVoiceRecordingFromMainApp) {
                    generateButtonContainer(2);
                }
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Share button pressed");  //share last voice file if any present
                //Add link or action
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Settings button pressed");
                //Add link or action
            }
        });

        fileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "File button pressed");
                //Add link or action
            }
        });

        return ll;
    }

    private boolean isAnyRecordingPresent() {
        //add logic to check if we have any recordings available. If not, deactivate play and share buttons
        return true;
    }


    private void generateButtonContainer(int includeNo) {
        if (includeNo == 1) {
            lastCallButton.setVisibility(View.GONE);
            if (buttonContainer2 != null && buttonContainer2.getVisibility() == View.VISIBLE) {
                recVoiceButton.setVisibility(View.VISIBLE);
                buttonContainer2.setVisibility(View.GONE);
            }
            if (audioManager != null && prevSTREAM_DTMFVolume != -1) {
                AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE); //turn up the volume temporarily
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), AudioManager.FLAG_SHOW_UI);
            }
            //Creating a player object to play the last recording
            //player = new AftercallPlayer(ll);  //class handing playing a record and updating the view during play
            //player.initAftercallPlayer(getCalldoradoContext());
        } else if (includeNo == 2) {
            recVoiceButton.setVisibility(View.GONE);
            if (playerBar != null && playerBar.getVisibility() == View.VISIBLE) {
                lastCallButton.setVisibility(View.VISIBLE);
                playerBar.setVisibility(View.GONE);
            }
            if (player != null) {
                //player.stopPlaying();
            }
            recButton = (ImageButton) buttonContainer2.findViewById(R.id.aftercall_rec);
            playButton = (ImageButton) buttonContainer2.findViewById(R.id.aftercall_play);
            pauseButton = (ImageButton) buttonContainer2.findViewById(R.id.aftercall_pause);
            stopButton = (ImageButton) buttonContainer2.findViewById(R.id.aftercall_stop);
            saveButton = (ImageButton) buttonContainer2.findViewById(R.id.aftercall_save);
            deleteButton = (ImageButton) buttonContainer2.findViewById(R.id.aftercall_delete);
            recButtonLayout = (RelativeLayout) buttonContainer2.findViewById(R.id.rec_button_layout);
            playButtonLayout = (RelativeLayout) buttonContainer2.findViewById(R.id.play_button_layout);
            pauseButtonLayout = (RelativeLayout) buttonContainer2.findViewById(R.id.pause_button_layout);
            stopButtonLayout = (RelativeLayout) buttonContainer2.findViewById(R.id.stop_button_layout);
            saveButtonLayout = (RelativeLayout) buttonContainer2.findViewById(R.id.save_button_layout);
            deleteButtonLayout = (RelativeLayout) buttonContainer2.findViewById(R.id.delete_button_layout);
            if (isVoiceRecordingFromMainApp) {
                activateButton(recButton, false);
            } else {
                activateButton(recButton, true);
            }
        } else if (includeNo == 3) {
            if (playerBar != null && playerBar.getVisibility() == View.VISIBLE) {
                lastCallButton.setVisibility(View.VISIBLE);
                playerBar.setVisibility(View.GONE);
            }
            if (buttonContainer2 != null && buttonContainer2.getVisibility() == View.VISIBLE) {
                recVoiceButton.setVisibility(View.VISIBLE);
                buttonContainer2.setVisibility(View.GONE);
            }
        } else {
            return;
        }
        setButtonList(includeNo);
    }

    private void activateButton(ImageButton imageButton, boolean shouldShowActive) {
        if (shouldShowActive) {
            imageButton.getDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
        } else {
            imageButton.getDrawable().clearColorFilter();
        }
    }

    private void setButtonList(int buttonState) {

        switch (buttonState) {
            case 1: //last call
                Log.d(TAG, "setting up buttons for last call handling");
                playerBar.setVisibility(View.VISIBLE);
                break;
            case 2: //voice record
                Log.d(TAG, "setting up buttons for voice recording");
                //recorder = new AftercallRecorder(buttonContainer2); //Class responsible of recording voice
                //recorder.initAftercallRecorder(getCalldoradoContext());
                recButtonLayout.setVisibility(View.VISIBLE);
                saveButtonLayout.setVisibility(View.GONE);
                deleteButtonLayout.setVisibility(View.VISIBLE);
                pauseButtonLayout.setVisibility(View.VISIBLE);
                stopButtonLayout.setVisibility(View.VISIBLE);
                playButtonLayout.setVisibility(View.GONE);
                activateButton(recButton, false);
                deleteButtonLayout.setVisibility(View.GONE);
                stopButtonLayout.setVisibility(View.GONE);
                pauseButtonLayout.setVisibility(View.GONE);
                stopButton.setActivated(false);
                deleteButton.setActivated(false);
                pauseButton.setActivated(false);
                deleteButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "voice rec actions:   delete clicked");
                        recButton.getDrawable().clearColorFilter();
                        deleteButtonLayout.setVisibility(View.GONE);
                        stopButtonLayout.setVisibility(View.GONE);
                        recButtonLayout.setVisibility(View.VISIBLE);
                        pauseButtonLayout.setVisibility(View.GONE);
                        //recorder.acRecDelete();
                    }
                });
                recButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "voice rec actions:   rec clicked");
                        activateButton(recButton, true);
                        deleteButtonLayout.setVisibility(View.VISIBLE);
                        stopButtonLayout.setVisibility(View.VISIBLE);
                        recButtonLayout.setVisibility(View.VISIBLE);
                        pauseButtonLayout.setVisibility(View.VISIBLE);
                        //recorder.acRecRec();
                    }
                });
                pauseButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "voice rec actions:   pause clicked");
                        recButton.getDrawable().clearColorFilter();
                        recButton.getDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
                        deleteButtonLayout.setVisibility(View.VISIBLE);
                        stopButtonLayout.setVisibility(View.VISIBLE);
                        recButtonLayout.setVisibility(View.VISIBLE);
                        pauseButtonLayout.setVisibility(View.VISIBLE);
                        //recorder.acRecPause();

                    }
                });
                stopButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "voice rec actions:   stop clicked");
                        activateButton(recButton, false);
                        deleteButtonLayout.setVisibility(View.GONE);
                        stopButtonLayout.setVisibility(View.GONE);
                        recButtonLayout.setVisibility(View.VISIBLE);
                        pauseButtonLayout.setVisibility(View.GONE);
                        //recorder.acRecStop();
                        if (!anyAvailableRecordings && isAnyRecordingPresent()) { //We have made a new recording where there was none before
                            anyAvailableRecordings = true;
                            lastCallButton.setVisibility(View.VISIBLE);
                            shareButton.setVisibility(View.VISIBLE);
                        }
                    }
                });
                buttonContainer2.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void executeOnDestroy() {
        //onDestroy() is generally unreliable. Better to use executeOnPause()
    }

    @Override
    public void executeOnPause() {
        Log.d(TAG, "excuteOnPause()");
        audioManager.setStreamVolume(AudioManager.STREAM_DTMF, prevSTREAM_DTMFVolume, 0); //resetting volume to user setting
        //stop & unbind services, stop background threads and similar here
        if (recorder != null) {
            //recorder.onDestroy();
        }
        if (player != null) {
            //player.onDestroy();
        }
    }

    @Override
    public void executeOnResume() {
        Log.d(TAG, "excuteOnResume()");
    }

    @Override
    public void executeOnStop() {
        Log.d(TAG, "excuteOnStop()");
    }
}
