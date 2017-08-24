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
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.calldorado.android.ui.views.custom.CalldoradoCustomView;

public class WicActionCustomView extends CalldoradoCustomView {

    public WicActionCustomView(Context context) {
        super(context);
    }

    @Override
    public View getRootView() {

        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(android.R.drawable.ic_menu_info_details);

        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // TODO your code here
                    String note = (String) findItem(getPhoneNumber(), "");
                    if (note.isEmpty()) note = "Hello World!";
                    showFeedbackMessage(note);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(convertDpToPixel(30), convertDpToPixel(30));
        LinearLayout ll = new LinearLayout(getContext());

        ll.setLayoutParams(lp);
        ll.addView(imageView);

        return ll;
    }
}
