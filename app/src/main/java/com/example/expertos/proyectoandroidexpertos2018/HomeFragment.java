package com.example.expertos.proyectoandroidexpertos2018;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class HomeFragment extends Fragment implements View.OnClickListener{

    private Button btnGo;
    DrawerLayout menu;
    ListView menuList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_home, null);

        btnGo = v.findViewById(R.id.btnGo);
        btnGo.setOnClickListener((View.OnClickListener) this);

        menu= getActivity().findViewById(R.id.drawer_layout);

        return v;
    }


    @Override
    public void onClick(View v) {
        menu.openDrawer(Gravity.LEFT);
    }
}
