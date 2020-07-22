package com.example.e_voting_mobile.util;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.util.Objects;

public class NetworkStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("app", "Network connectivity change");
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setCancelable(false);
        dialog.setTitle("Alert");
        dialog.setMessage("There's no network connectivity");
        dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Retry", (arg0, arg1) -> {
            if (NetworkConnectivity.isNetworkAvailable(context)) {
                dialog.dismiss();
            } else {
                dialog.cancel();
                onReceive(context, intent);
            }

        });
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Exit", (arg0, arg1) -> {
            System.exit(0);
        });
        // create and show the alert dialog
        if (intent.getExtras() != null) {
            NetworkInfo ni = (NetworkInfo) intent.getExtras().get(ConnectivityManager.EXTRA_NETWORK_INFO);
            if (ni != null && ni.getState() == NetworkInfo.State.CONNECTED) {
                Log.i("app", "Network " + ni.getTypeName() + " connected");
                dialog.hide();
            }

        }
        if (Objects.requireNonNull(intent.getExtras()).getBoolean(ConnectivityManager.EXTRA_NO_CONNECTIVITY, Boolean.FALSE)) {

            Log.d("app", "There's no network connectivity");

            dialog.show();
        }

    }
}
