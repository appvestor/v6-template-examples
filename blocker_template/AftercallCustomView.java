//
//  Created by Calldorado Team on August 24th 2017.
//  Copyright © 2017 Calldorado ApS. All rights reserved.
//
//  Licensed under the Calldorado SDK Template License Version 1;
//  you must comply with this license in order to use this file.
//  You may obtain a copy of the License at the following URL:
//  https://github.com/Calldorado-com/calldorado-template-examples
//

package com.sappalodapps.callblocker.fragment.wic.aftercall;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.calldorado.android.ui.views.custom.CalldoradoCustomView;

import com.sappalodapps.callblocker.R;

public class AftercallCustomView extends CalldoradoCustomView {

    private static final String TAG = AftercallCustomView.class.getName();
    private RelativeLayout rl;
    private Context context;
    private ImageView blockButtonImage, blockNowButton;
    private ImageView aftercallPlus;
    private ImageView aftercallBurger;
    private TextView blockButtonText, blockNowTextView;
    private EditText noteTextEdit;
    private ImageView editTextImage;
    private LinearLayout confirmationLayout;
    private RelativeLayout noteLayout;

    public AftercallCustomView(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public View getRootView() {

        rl = (RelativeLayout) inflate(context, R.layout.custom_aftercall_layout, getRelativeViewGroup());
        blockButtonImage = (ImageView) rl.findViewById(R.id.image_block_button);
        blockButtonImage.setTag("user_not_blocked");
        blockButtonText = (TextView) rl.findViewById(R.id.image_block_button_text);
        aftercallPlus = (ImageView) rl.findViewById(R.id.image_plus_icon);
        aftercallBurger = (ImageView) rl.findViewById(R.id.image_burger_icon);
        noteTextEdit = (EditText) rl.findViewById(R.id.aftercall_note_text_edit);
        editTextImage = (ImageView) rl.findViewById(R.id.image_edit_text);
        confirmationLayout = (LinearLayout) rl.findViewById(R.id.confirmation_layout);
        blockNowButton = (ImageView) rl.findViewById(R.id.block_now_image);
        blockNowTextView = (TextView) rl.findViewById(R.id.block_now);
        noteLayout = (RelativeLayout) rl.findViewById(R.id.note_layout);

        boolean isContactBlocked = (boolean) findItem(getPhoneNumber(), false);

        if (isContactBlocked) {
            blockButtonImage.setImageResource(R.drawable.aftercall_user_is_blocked);
            blockButtonImage.setTag("user_blocked");
            blockButtonText.setText("This person is blocked");
            aftercallBurger.setVisibility(VISIBLE);
            aftercallPlus.setVisibility(VISIBLE);
            noteTextEdit.setFocusable(false);
            noteTextEdit.setFocusableInTouchMode(false);
            noteTextEdit.setVisibility(GONE);
            editTextImage.setVisibility(GONE);
            setAftercallBurgerOnClick();
            setAftercallPlusOnClick();
            Log.d(TAG, "already blocked");
        }

        if (blockButtonImage.getTag().equals("user_not_blocked")) {
            blockButtonImage.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        // TODO your code here
                        confirmationLayout.setVisibility(VISIBLE);
                        noteTextEdit.setFocusable(false);
                        noteTextEdit.setFocusableInTouchMode(false);
                        setBlockNowImageOnClick();
                        setBlockNowTextOnClick();
                        hideKeyboard(context, noteTextEdit);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        return rl;
    }

    private void showConfirmationOfBlockScreen() {
        confirmationLayout.setVisibility(GONE);
        saveItem(getPhoneNumber(), true);
        blockButtonImage.setImageResource(R.drawable.aftercall_user_is_blocked);
        blockButtonImage.setTag("user_blocked");
        blockButtonImage.setOnClickListener(null);
        blockButtonText.setText("You have now sucessfully blocked this person");
        noteLayout.setVisibility(GONE);
        aftercallBurger.setVisibility(VISIBLE);
        aftercallPlus.setVisibility(VISIBLE);
        setAftercallBurgerOnClick();
        setAftercallPlusOnClick();
    }

    private void setBlockNowImageOnClick() {
        blockNowButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // TODO your code here
                    showConfirmationOfBlockScreen();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setBlockNowTextOnClick() {
        blockNowTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // TODO your code here
                    showConfirmationOfBlockScreen();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setAftercallBurgerOnClick() {
        aftercallBurger.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // TODO your code here
                    showFeedbackMessage("Your code here!");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setAftercallPlusOnClick() {
        aftercallPlus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // TODO your code here
                    showFeedbackMessage("Your code here!");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void hideKeyboard(Context context, EditText editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}
