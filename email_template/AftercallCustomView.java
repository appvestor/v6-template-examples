//
//  Created by Calldorado Team on August 24th 2017.
//  Copyright © 2017 Calldorado ApS. All rights reserved.
//
//  Licensed under the Calldorado SDK Template License Version 1;
//  you must comply with this license in order to use this file.
//  You may obtain a copy of the License at the following URL:
//  https://github.com/Calldorado-com/calldorado-template-examples
//

package com.email.email.views;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.calldorado.android.ui.views.custom.CalldoradoCustomView;
import com.email.email.R;
import java.util.ArrayList;

public class AftercallCustomView extends CalldoradoCustomView {

    private boolean isEditFiedlEnabled;
    private ArrayList<String> emailList;
    private String contactID;

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
        final EditText etSavedEmail = (EditText) bg.findViewById(R.id.et_saved_email);
        final EditText etSubject = (EditText) bg.findViewById(R.id.et_subject);
        final EditText etBody = (EditText) bg.findViewById(R.id.et_body);
        final ImageView btEdit = (ImageView) bg.findViewById(R.id.bt_edit);
        final TextView btSend = (TextView) bg.findViewById(R.id.bt_send);

        // Init

        if (isEditFiedlEnabled) // disable btSend if editing email
            btSend.setBackgroundResource(R.drawable.button_grey_bg);
        else
            btSend.setBackgroundResource(R.drawable.button_blue_bg);

        emailList = new ArrayList<>();
        fillEmailList();

        String email = (String) findItem(getPhoneNumber(), "");
        if (email.isEmpty() && !emailList.isEmpty()) email = emailList.get(0);

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
        }

        // Action

        etSavedEmail.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    if (hasFocus && !isEditFiedlEnabled) {
                        etSavedEmail.setEnabled(false);
                        etSubject.requestFocus();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (isEditFiedlEnabled) { // save current email
                        String typedEmail = etSavedEmail.getText().toString();
                        if (isValidEmail(typedEmail)) {
                            saveItem(getPhoneNumber(), typedEmail);
                            if (contactID != null && !emailList.contains(typedEmail)) {
                                emailList.add(typedEmail);
                                updateContact(typedEmail);
                            }

                            btEdit.setBackgroundResource(R.drawable.edit_icon);
                            btSend.setBackgroundResource(R.drawable.button_blue_bg);

                            isEditFiedlEnabled = false;
                            etSavedEmail.setEnabled(false);
                            etSubject.requestFocus();
                        }
                        else showFeedbackMessage("Please enter a valid e-mail.");
                    }
                    else { // edit current email
                        btEdit.setBackgroundResource(R.drawable.save_icon);
                        btSend.setBackgroundResource(R.drawable.button_grey_bg);

                        isEditFiedlEnabled = true;
                        etSavedEmail.setEnabled(true);
                        etSavedEmail.requestFocus();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    btAdd.setVisibility(GONE);
                    llSaveEmail.setVisibility(VISIBLE);
                    llWriteEmail.setVisibility(GONE);
                    etTypedEmail.requestFocus();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    btAdd.setVisibility(VISIBLE);
                    llSaveEmail.setVisibility(GONE);
                    llWriteEmail.setVisibility(GONE);

                    Activity activity = (Activity) getCalldoradoContext();
                    if (activity.getCurrentFocus() != null) { // hide keyboard
                        InputMethodManager imm = (InputMethodManager)
                                activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(activity.getCurrentFocus()
                                .getWindowToken(), 0);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String typedEmail = etTypedEmail.getText().toString();
                    if (isValidEmail(typedEmail)) { // save email also for numbers not in phonebook
                        saveItem(getPhoneNumber(), typedEmail);
                        if (contactID != null && !emailList.contains(typedEmail)) {
                            emailList.add(typedEmail);
                            updateContact(typedEmail);
                        }

                        etSavedEmail.setText(typedEmail);
                        etSavedEmail.setEnabled(false);
                        etSubject.requestFocus();

                        btAdd.setVisibility(GONE);
                        llSaveEmail.setVisibility(GONE);
                        llWriteEmail.setVisibility(VISIBLE);
                    }
                    else showFeedbackMessage("Please enter a valid e-mail.");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btSend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!isEditFiedlEnabled) { // do nothing if new email is not saved
                        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",
                                etSavedEmail.getText().toString(), null));
                        intent.putExtra(Intent.EXTRA_SUBJECT, etSubject.getText().toString());
                        intent.putExtra(Intent.EXTRA_TEXT, etBody.getText().toString());
                        getCalldoradoContext().startActivity(Intent.createChooser(intent, "OPEN WITH"));
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
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

    public void fillEmailList() {
        Cursor c0 = null;
        Cursor c1 = null;
        try {
            Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(getPhoneNumber()));
            String[] projection = {ContactsContract.Contacts._ID, ContactsContract.PhoneLookup.LOOKUP_KEY, ContactsContract.RawContacts._ID, ContactsContract.PhoneLookup.DISPLAY_NAME};
            c0 = getContext().getContentResolver().query(uri, projection, null, null, null);
            if (c0 != null) {
                if (c0.moveToFirst()) {
                    int _ID = c0.getColumnIndex(ContactsContract.Contacts._ID);
                    contactID = c0.getString(_ID);
                    if (contactID != null) {
                        c1 = getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactID, null, null);
                        if (c1 != null) {
                            int dataColumn = c1.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA);
                            if (c1.moveToFirst()) {
                                do {
                                    if (c1.getString(dataColumn) != null
                                            && c1.getString(dataColumn).length() > 0) {
                                        emailList.add(c1.getString(dataColumn));
                                    }
                                } while (c1.moveToNext());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to find email from contact.", e);

        } finally {
            if (c0 != null) c0.close();
            if (c1 != null) c1.close();
        }
    }

    private void updateContact(String typedEmail) {
        try {
            ContentValues values = new ContentValues();
            values.put(ContactsContract.Data.RAW_CONTACT_ID, contactID);
            values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE);
            values.put(ContactsContract.CommonDataKinds.Email.DATA, typedEmail);
            values.put(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_OTHER);
            getContext().getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
