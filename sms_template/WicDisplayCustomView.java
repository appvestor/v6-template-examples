package com.concentriclivers.views;

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

        int dpUnits = convertDpToPixel(5);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        final LinearLayout ll = new LinearLayout(getContext());
        ll.setLayoutParams(lp);

        final TextView textView = new TextView(getContext());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setTextColor(Color.DKGRAY);
        textView.setMaxLines(3);

        String text = (String) findItem(getPhoneNumber(), "");
        if (text != null ) {
            textView.setText(text);
            ll.setPadding(3 * dpUnits, 2 * dpUnits, 3 * dpUnits, 2 * dpUnits);
            ll.setBackgroundColor(Color.parseColor("#f4f7f9"));
            ll.addView(textView);
        }

        return ll;
    }
}
