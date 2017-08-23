package com.sappalodapps.callblocker.fragment.wic.aftercall;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.calldorado.android.ui.views.custom.CalldoradoCustomView;
import com.sappalodapps.callblocker.R;


public class WicActionCustomView extends CalldoradoCustomView {

    public WicActionCustomView(Context context) {
        super(context);
    }

    @Override
    public View getRootView() {

        RelativeLayout.LayoutParams wicButtonLayoutLP = new RelativeLayout.LayoutParams(convertDpToPixel(30), convertDpToPixel(30));
        RelativeLayout wicButtonLayout = new RelativeLayout(getContext());
        wicButtonLayout.setLayoutParams(wicButtonLayoutLP);

        RelativeLayout.LayoutParams wicButton1LP = new RelativeLayout.LayoutParams(convertDpToPixel(30), convertDpToPixel(30));
        wicButton1LP.addRule(RelativeLayout.CENTER_IN_PARENT);

        final Button wicButton = new Button(getContext());
        wicButton.setLayoutParams(wicButton1LP);
        wicButton.setBackgroundResource(R.drawable.wic_block_button);
        wicButton.setClickable(true);

        wicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // TODO your code here
                    saveItem(getPhoneNumber(), true);
                    showFeedbackMessage("Number blocked!");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        wicButtonLayout.addView(wicButton);
        return wicButtonLayout;
    }
}

