package com.example.expertos.proyectoandroidexpertos2018;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import com.example.expertos.proyectoandroidexpertos2018.utilRest.devazt.networking.HttpClient;
import com.example.expertos.proyectoandroidexpertos2018.utilRest.devazt.networking.OnHttpRequestComplete;
import com.example.expertos.proyectoandroidexpertos2018.utilRest.devazt.networking.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements View.OnClickListener{

    //
    //inicializar variables
    private Button btnGo;
    DrawerLayout menu;
    private TextView txtVTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_home, null);

        //
        //intanciar shared preferences de estereotipo
        SharedPreferences prefs = getActivity().getSharedPreferences("estereoripos", Context.MODE_PRIVATE);

        //
        //inicializar variables respecto a sus componentes
        btnGo = v.findViewById(R.id.btnGo);
        btnGo.setOnClickListener((View.OnClickListener) this);

        menu= getActivity().findViewById(R.id.drawer_layout);

        txtVTitle = v.findViewById(R.id.txtVTitle);
        txtVTitle.append(" "+ prefs.getString("estereotipo", null).toUpperCase()+"!");

        return v;
    }


    @Override
    public void onClick(View v) {
        //
        //desplegar menu
        menu.openDrawer(Gravity.LEFT);
    }
}
