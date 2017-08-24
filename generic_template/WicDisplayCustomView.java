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
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.badharry.heartattack.R;
import com.calldorado.android.ui.views.custom.CalldoradoCustomView;


public class WicDisplayCustomView extends CalldoradoCustomView {

    public WicDisplayCustomView(Context context) {
        super(context);
    }

    @Override
    public View getRootView() {
        
        /* 
        This UI is created from a layout resource called wic_display_layout.xml
        See how to create an UI programmatically in the class AftercallCustomView.java 
        */

        String note = (String) findItem(getPhoneNumber(),"");

        if (!note.isEmpty()) {

            LinearLayout ll = (LinearLayout) inflate(getContext(),
                    R.layout.wic_display_layout, getLinearViewGroup());

            TextView textView = (TextView) ll.findViewById(R.id.text_view);
            textView.setText(note);

            return ll;
        }

        return null;
    }
}
