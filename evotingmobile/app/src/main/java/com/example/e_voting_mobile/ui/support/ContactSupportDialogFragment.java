package com.example.e_voting_mobile.ui.support;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.e_voting_mobile.R;
import com.example.e_voting_mobile.util.Constants;

import java.util.Objects;

public class ContactSupportDialogFragment extends DialogFragment {
    Button bClose;
    Button bSend;
    EditText eMessage;
    Spinner sRole;
    EditText ePhoneNr;
    EditText eName;
    ContactSupportDialogViewModel contactSupportDialogViewModel;

    public static ContactSupportDialogFragment display(FragmentManager supportFragmentManager) {
        ContactSupportDialogFragment contactSupportDialogFragment = new ContactSupportDialogFragment();
        contactSupportDialogFragment.show(supportFragmentManager, "support_dialog");
        return contactSupportDialogFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.email_support_popup, container, false);
        contactSupportDialogViewModel = new ViewModelProvider(this).get(ContactSupportDialogViewModel.class);
        bClose = rootView.findViewById(R.id.button_close);
        bSend = rootView.findViewById(R.id.button_action);
        eMessage = rootView.findViewById(R.id.message);
        sRole = rootView.findViewById(R.id.roles_list_dropDown);
        ePhoneNr = rootView.findViewById(R.id.phone_nr);
        eName = rootView.findViewById(R.id.nameText);

        return rootView;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bClose.setOnClickListener(v -> dismiss());
        bSend.setOnClickListener(v -> {

            Intent it = new Intent(Intent.ACTION_SEND);
            it.putExtra(Intent.EXTRA_EMAIL, new String[]{Constants.SUPPORT_EMAIL});
            it.putExtra(Intent.EXTRA_SUBJECT, "Question from " + sRole.getSelectedItem().toString() + ", " + eName.getText());
            it.putExtra(Intent.EXTRA_TEXT, eMessage.getText() + "\n\n\n Contact sender: " + ePhoneNr.getText());
            it.setType("message/rfc822");
            startActivity(Intent.createChooser(it, "Choose Mail App"));

            dismiss();
        });
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            Objects.requireNonNull(dialog.getWindow()).setLayout(width, height);
        }
    }
}
