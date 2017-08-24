//
//  Created by Calldorado Team on August 24th 2017.
//  Copyright © 2017 Calldorado ApS. All rights reserved.
//
//  Licensed under the Calldorado SDK Template License Version 1;
//  you must comply with this license in order to use this file.
//  You may obtain a copy of the License at the following URL:
//  https://github.com/Calldorado-com/calldorado-template-examples
//

package com.your.package;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.TypedValue;
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

        String text = (String) findItem(getPhoneNumber(), "");

        if (!text.isEmpty()) {

            int dpUnits = convertDpToPixel(16);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            final LinearLayout ll = new LinearLayout(getContext());
            ll.setLayoutParams(lp);

            final TextView textView = new TextView(getContext());
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setTextColor(Color.DKGRAY);
            textView.setMaxLines(3);

            textView.setText(text);
            ll.setPadding(dpUnits, dpUnits, dpUnits, dpUnits);
            ll.setBackgroundColor(Color.parseColor("#f4f7f9"));
            ll.addView(textView);
            return ll;
        }

        return null;
    }
}
