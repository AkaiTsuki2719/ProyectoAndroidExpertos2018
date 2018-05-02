package com.example.expertos.proyectoandroidexpertos2018;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class TourSearchFragment extends Fragment implements View.OnClickListener{


    private Button btnTourSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_toursearch, null);

        btnTourSearch = v.findViewById(R.id.btnTourSearch);
        btnTourSearch.setOnClickListener(this);

        return v;

    }


    @Override
    public void onClick(View v) {

        Intent intent = new Intent(getActivity(), ResultSearchActivity.class);
        startActivity(intent);

    }
}
