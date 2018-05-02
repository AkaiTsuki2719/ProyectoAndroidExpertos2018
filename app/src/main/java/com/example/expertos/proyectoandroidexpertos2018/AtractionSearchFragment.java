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

public class AtractionSearchFragment extends Fragment implements View.OnClickListener{


    private Button btnAtractionSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_atractionsearch, null);

        btnAtractionSearch = v.findViewById(R.id.btnAtractionSearch);
        btnAtractionSearch.setOnClickListener(this);

        return v;

    }


    @Override
    public void onClick(View v) {

        Intent intent = new Intent(getActivity(), ResultSearchActivity.class);
        startActivity(intent);

    }
}
