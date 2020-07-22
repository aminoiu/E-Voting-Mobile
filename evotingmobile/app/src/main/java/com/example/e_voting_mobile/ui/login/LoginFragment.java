package com.example.e_voting_mobile.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.e_voting_mobile.R;
import com.example.e_voting_mobile.data.network_util.LoginRequest;
import com.example.e_voting_mobile.data.network_util.LoginResponse;
import com.example.e_voting_mobile.exceptions.LoginException;
import com.example.e_voting_mobile.service.SessionManager;
import com.example.e_voting_mobile.voting_history.HistoryActivity;

public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;
    Button loginButton;
    EditText emailText;
    EditText passwordText;
    View root = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        if (root == null) {
            root = inflater.inflate(R.layout.fragment_login, container, false);
        }

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModel.getResponseLiveData().observe(getViewLifecycleOwner(), this::handleSuccessfulLogin);
        loginViewModel.getLoginExceptionData().observe(getViewLifecycleOwner(), this::handleLoginExceptions);

        emailText = root.findViewById(R.id.editEmail);
        passwordText = root.findViewById(R.id.editPassword);
        loginButton = root.findViewById(R.id.login_button);
        emailText.clearFocus();
        if (SessionManager.getInstance().getAuthenticationEmail() != null) {
            emailText.setText(SessionManager.getInstance().getAuthenticationEmail());
            passwordText.requestFocus();
        }
        emailText.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (root != null) {
                    InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(root.getWindowToken(), 0);
                }
                return true;
            }
            return false;
        });
        passwordText.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (root != null) {
                    InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(root.getWindowToken(), 0);
                }
                return true;
            }
            return false;
        });
        loginButton.setOnClickListener(view -> {
            InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(root.getWindowToken(), 0);
            if (validateEnteredData()) {
                performLogin(emailText.getText().toString(), passwordText.getText().toString());
            }
        });


        return root;
    }

    private void handleLoginExceptions(LoginException e) {
        if (e.getExceptionCause().equals("CONNECTION")) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        } else {
            emailText.setError(e.getMessage());
            passwordText.setError(e.getMessage());
        }
    }


    private boolean validateEnteredData() {
        boolean isValid = true;
        if (isEmpty(emailText)) {
            emailText.setError("You must enter email to login!");
            isValid = false;
        } else if (isEmpty(passwordText)) {
            passwordText.setError("You must enter password to login!");
            isValid = false;

        }
        if (!isEmail(emailText)) {
            emailText.setError("Enter valid email!");
            isValid = false;

        }
        return isValid;
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    private void performLogin(String email, String pass) {
        loginViewModel.login(new LoginRequest(email, pass));
    }

    private void handleSuccessfulLogin(LoginResponse loginResponse) {
        if (loginResponse != null) {
            Toast.makeText(getContext(), "Welcome " + loginResponse.getEmail() + "!!!", Toast.LENGTH_LONG).show();
            SessionManager.getInstance().setAuthenticationToken(loginResponse.getToken());
            SessionManager.getInstance().setAuthenticationEmail(loginResponse.getEmail());
            SessionManager.getInstance().setAuthenticatedRoles(loginResponse.getRoles());
            Intent intent = new Intent(getActivity(), HistoryActivity.class);
            startActivity(intent);
            requireActivity().finish();
            //the User details fragment will be accessed only by ADMIN users

            Log.i("BEARER TOKEN", loginResponse.getToken());
        }
    }

}