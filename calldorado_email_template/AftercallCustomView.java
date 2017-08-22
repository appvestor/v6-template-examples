package com.email.email.views;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.calldorado.android.ui.views.custom.CalldoradoCustomView;
import com.email.email.R;

/**
 * Created by Jussi on 16-Aug-17.
 */

public class AftercallCustomView extends CalldoradoCustomView {

    boolean isEditFiedlEnabled;
    EditText etSavedEmail;
    EditText etSubject;
    EditText etBody;

    public AftercallCustomView(Context context) {
        super(context);
    }

    @Override
    public View getRootView() {

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout ll = new LinearLayout(getContext());
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setLayoutParams(lp);

        final LinearLayout bg = (LinearLayout) inflate(getContext(),
                R.layout.custom_aftercall_layout, new LinearLayout(getContext()));

        // UI 1
        final TextView btAdd = (TextView) bg.findViewById(R.id.bt_add);

        // UI 2
        final LinearLayout llSaveEmail = (LinearLayout) bg.findViewById(R.id.ll_save_email);
        final EditText etTypedEmail = (EditText) bg.findViewById(R.id.et_typed_email);
        final TextView btSave = (TextView) bg.findViewById(R.id.bt_save);
        final TextView btCancel = (TextView) bg.findViewById(R.id.bt_cancel);

        // UI 3
        final LinearLayout llWriteEmail = (LinearLayout) bg.findViewById(R.id.ll_write_email);
        etSavedEmail = (EditText) bg.findViewById(R.id.et_saved_email);
        etSubject = (EditText) bg.findViewById(R.id.et_subject);
        etBody = (EditText) bg.findViewById(R.id.et_body);
        final ImageView btEdit = (ImageView) bg.findViewById(R.id.bt_edit);
        final TextView btSend = (TextView) bg.findViewById(R.id.bt_send);

        // Init

        if (isEditFiedlEnabled)
            btSend.setBackgroundResource(R.drawable.button_grey_bg);
        else // disable btSend if editing email
            btSend.setBackgroundResource(R.drawable.button_blue_bg);

        String email = (String) findItem(getPhoneNumber(), "");
        if (email.isEmpty()) {
            btAdd.setVisibility(VISIBLE);
            llSaveEmail.setVisibility(GONE);
            llWriteEmail.setVisibility(GONE);
        }
        else {
            btAdd.setVisibility(GONE);
            llSaveEmail.setVisibility(GONE);
            llWriteEmail.setVisibility(VISIBLE);
            etSavedEmail.setText(email);
            etSavedEmail.setEnabled(false);
            etSubject.requestFocus();
        }

        // Action

        btEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEditFiedlEnabled) { // save current email
                    String typedEmail = etSavedEmail.getText().toString();
                    if (isValidEmail(typedEmail)) {
                        saveItem(getPhoneNumber(), typedEmail);
                        btEdit.setBackgroundResource(R.drawable.edit_icon);
                        btSend.setBackgroundResource(R.drawable.button_blue_bg);
                        etSavedEmail.setEnabled(false);
                        etSubject.requestFocus();
                        isEditFiedlEnabled = false;
                    }
                    else showFeedbackMessage("Please enter a valid e-mail.");
                }
                else { // edit current email
                    btEdit.setBackgroundResource(R.drawable.save_icon);
                    btSend.setBackgroundResource(R.drawable.button_grey_bg);
                    etSavedEmail.setEnabled(true);
                    etSavedEmail.requestFocus();
                    isEditFiedlEnabled = true;
                }
            }
        });

        btAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                btAdd.setVisibility(GONE);
                llSaveEmail.setVisibility(VISIBLE);
                llWriteEmail.setVisibility(GONE);
                etTypedEmail.requestFocus();
            }
        });

        btCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                btAdd.setVisibility(VISIBLE);
                llSaveEmail.setVisibility(GONE);
                llWriteEmail.setVisibility(GONE);
            }
        });

        btSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String typedEmail = etTypedEmail.getText().toString();
                if (isValidEmail(typedEmail)) {
                    saveItem(getPhoneNumber(), typedEmail);
                    etSavedEmail.setText(typedEmail);
                    etSavedEmail.setEnabled(false);
                    etSubject.requestFocus();

                    btAdd.setVisibility(GONE);
                    llSaveEmail.setVisibility(GONE);
                    llWriteEmail.setVisibility(VISIBLE);
                }
                else showFeedbackMessage("Please enter a valid e-mail.");
            }
        });

        btSend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEditFiedlEnabled) { // do nothing if new email is not saved
                    Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",
                            etSavedEmail.getText().toString(), null));
                    intent.putExtra(Intent.EXTRA_SUBJECT, etSubject.getText().toString());
                    intent.putExtra(Intent.EXTRA_TEXT, etBody.getText().toString());
                    getCalldoradoContext().startActivity(Intent.createChooser(intent, "OPEN WITH"));
                }
            }
        });

        ll.addView(bg);
        return ll;
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    @Override
    public void executeOnResume() {
        etSavedEmail.setEnabled(false);
        etSubject.requestFocus();
    }
}
