//
//  Created by Calldorado Team on August 24th 2017.
//  Copyright © 2017 Calldorado ApS. All rights reserved.
//
//  Licensed under the Calldorado SDK Template License Version 1;
//  you must comply with this license in order to use this file.
//  You may obtain a copy of the License at the following URL:
//  https://github.com/Calldorado-com/calldorado-template-examples
//

package com.badharry.heartattack.views;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.calldorado.android.ui.views.custom.CalldoradoCustomView;


public class WicDisplayCustomView extends CalldoradoCustomView {

    public WicDisplayCustomView(Context context) {
        super(context);
    }

    @Override
    public View getRootView() {

        int dpUnits = convertDpToPixel(5);
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        final LinearLayout ll = new LinearLayout(getContext());
        ll.setLayoutParams(llp);

        final TextView textView = new TextView(getContext());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        textView.setTextColor(Color.WHITE);

        String note = (String) findItem(getPhoneNumber(),"");

        if (!note.isEmpty()) {
            textView.setText(note);
            ll.setPadding(3*dpUnits, 2*dpUnits, 3*dpUnits, 2*dpUnits);
            ll.setBackgroundColor(Color.parseColor("#F44336"));
            ll.addView(textView);
        }

        return ll;
    }
}
