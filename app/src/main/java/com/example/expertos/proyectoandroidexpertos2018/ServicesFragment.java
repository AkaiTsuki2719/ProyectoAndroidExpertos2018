package com.example.expertos.proyectoandroidexpertos2018;

import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ServicesFragment extends Fragment {

    ImageView imgVBack4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_services, null);


        imgVBack4 = v.findViewById(R.id.imgVBack4);

        imgVBack4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONArray jsonBack;
                try {
                    jsonBack = new JSONArray(getActivity().getIntent().getStringExtra("dataFull"));

                    Intent intentDetail = new Intent(getContext(), SearchResultActivity.class);

                    intentDetail.putExtra("dataBack", jsonBack.toString());

                    startActivity(intentDetail);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        // 1. pass context and dat     a to the custom adapter
        MyAdapter adapter = null;
        try {
            adapter = new MyAdapter(getContext(), generateData(getActivity().getIntent().getStringExtra("dataDetail")));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // if extending Activity 2. Get ListView from activity_main.xml
        ListView listView = (ListView) v.findViewById(R.id.listview);

        // 3. setListAdapter
        listView.setAdapter(adapter); //if extending Activity

        //setListAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub
               // FragmentManager fragmentManager = getFragmentManager();

                //fragmentManager.beginTransaction().replace(R.id.container2, new ServiceInfoFragment()).commit();
            }

        });
        return v;
    }

    private ArrayList<Model> generateData(String dataResult) throws JSONException {

        JSONObject jsonDetailCompany;
        JSONArray jsonDetailTour;
        JSONArray jsonDetailItinerary;
        JSONArray jsonDetailActivity;
        JSONArray jsonDetailAttraction;
        JSONObject jsonService;

        jsonDetailCompany = new JSONObject(dataResult);
        jsonDetailActivity = jsonDetailCompany.getJSONArray("actividad");
        jsonDetailAttraction = jsonDetailCompany.getJSONArray("atraccion");
        jsonDetailTour = jsonDetailCompany.getJSONArray("tour");
        jsonDetailItinerary = jsonDetailCompany.getJSONArray("itinerario");


        ArrayList<Model> models = new ArrayList<Model>();

        if(jsonDetailActivity.length() >= 1){
            String service = "Actividad";
            for(int currentActivity = 0; currentActivity < jsonDetailActivity.length();currentActivity++){
                jsonService = jsonDetailActivity.getJSONObject(currentActivity);

                models.add(new Model(R.drawable.options,jsonService.get("nombre_actividad").toString(),service));

            }

        }

        if(jsonDetailAttraction.length() >= 1){
            String service = "Atraccion";
            for(int currentAtraction = 0; currentAtraction < jsonDetailAttraction.length();currentAtraction++){
                jsonService = jsonDetailAttraction.getJSONObject(currentAtraction);

                models.add(new Model(R.drawable.options,jsonService.get("nombre_atraccion").toString(),service));
            }

        }

        if(jsonDetailTour.length() >= 1){
            String service = "Tour";
            for(int currentTour = 0; currentTour < jsonDetailTour.length();currentTour++){
                jsonService = jsonDetailTour.getJSONObject(currentTour);

                models.add(new Model(R.drawable.options,jsonService.get("nombre_tour").toString(),service));
            }

        }

        if(jsonDetailItinerary.length() >= 1){
            String service = "Itinerario";
            for(int currentItinerary = 0; currentItinerary < jsonDetailItinerary.length();currentItinerary++){
                jsonService = jsonDetailItinerary.getJSONObject(currentItinerary);


                models.add(new Model(R.drawable.options,jsonService.get("nombre_itinerario").toString(),service));
            }

        }


        return models;
    }


}
