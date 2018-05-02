package com.example.expertos.proyectoandroidexpertos2018;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ServicesFragment extends Fragment {

    private ListView lstResults;
    private String[] results = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5",
            "Item 6", "Otras preguntas", "Enviar comentario"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_services, null);


        lstResults = v.findViewById(R.id.lstVResults);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, results);

        lstResults.setAdapter(adaptador);

        lstResults.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub
                FragmentManager fragmentManager= getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container2, new ServiceInfoFragment()).addToBackStack(null).commit();

            }

        });
        return v;
    }


}