package com.example.e_voting_mobile.ui.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_voting_mobile.R;
import com.example.e_voting_mobile.service.SessionManager;
import com.example.e_voting_mobile.voting_history.HistoryActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class HistoryFragment extends Fragment {
    private TextView authenticatedEmailText;
    private NavigationView navigationView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_history, container, false);
        initView();

        RecyclerView recList = (RecyclerView) root.findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm=new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        return root;
    }

    private void initView() {
        ((HistoryActivity) requireActivity()).updateEmailText(SessionManager.getInstance().getAuthenticationEmail());
    }
}
