package com.example.e_voting_mobile.voting_history;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.e_voting_mobile.MainActivity;
import com.example.e_voting_mobile.NavigationHost;
import com.example.e_voting_mobile.R;
import com.example.e_voting_mobile.service.SessionManager;
import com.example.e_voting_mobile.ui.support.ContactSupportDialogFragment;
import com.example.e_voting_mobile.util.NetworkStateReceiver;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class HistoryActivity extends AppCompatActivity implements NavigationHost {

    private AppBarConfiguration mAppBarConfiguration;
    private NavigationView navigationView;
    private TextView authenticatedEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        this.registerReceiver(new NetworkStateReceiver(), intentFilter);
        Toolbar toolbar = findViewById(R.id.toolbar_history);
        setSupportActionBar(toolbar);
        FloatingActionButton email = findViewById(R.id.support_email);
        email.setOnClickListener(view -> {
            ContactSupportDialogFragment.display(getSupportFragmentManager());
        });
        FloatingActionButton call = findViewById(R.id.support_call);
        call.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "069384394"));
            startActivity(intent);
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout_history);
        navigationView = findViewById(R.id.nav_view_history);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_history)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_history);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            switch (id) {
                case R.id.nav_logout:
                    SessionManager.getInstance().setAuthenticationEmail(null);
                    SessionManager.getInstance().setAuthenticationToken(null);
                    Intent i = new Intent(HistoryActivity.this, MainActivity.class);
                    startActivity(i);
                    break;
            }
            return false;
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.history, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_history);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void navigateTo(Fragment fragment, boolean addToBackstack) {
        FragmentTransaction transaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_history, fragment);

        if (addToBackstack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }

    public void updateEmailText(String authenticationEmail) {
        authenticatedEmail = navigationView.getHeaderView(0).findViewById(R.id.authenticated_email);
        authenticatedEmail.setText(authenticationEmail);
    }

}
