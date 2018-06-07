package com.example.expertos.proyectoandroidexpertos2018;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.expertos.proyectoandroidexpertos2018.utilRest.devazt.networking.HttpClient;
import com.example.expertos.proyectoandroidexpertos2018.utilRest.devazt.networking.OnHttpRequestComplete;
import com.example.expertos.proyectoandroidexpertos2018.utilRest.devazt.networking.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

public class ItinerarySearchFragment extends Fragment implements View.OnClickListener {

    //
    //inicializar variables
    private Button btnItinerarySearch;
    private Spinner spnItineraryS;
    private Spinner spnItineraryT;
    private Spinner spnItineraryD;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_itinerarysearch, null);

        //cargar spinner con valores correspondientes
        spnItineraryT = v.findViewById(R.id.spnItineraryT);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterTransport = ArrayAdapter.createFromResource(getContext(),
                R.array.itinerarytransport, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterTransport.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spnItineraryT.setAdapter(adapterTransport);

        spnItineraryD = v.findViewById(R.id.spnItineraryD);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterDistance = ArrayAdapter.createFromResource(getContext(),
                R.array.itinerarydistance, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterDistance.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spnItineraryD.setAdapter(adapterDistance);

        spnItineraryS = v.findViewById(R.id.spnItineraryS);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterStop = ArrayAdapter.createFromResource(getContext(),
                R.array.itinerarystop, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterDistance.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spnItineraryS.setAdapter(adapterStop);

        btnItinerarySearch = v.findViewById(R.id.btnItinerarySearch);
        btnItinerarySearch.setOnClickListener(this);

        return v;

    }


    @Override
    public void onClick(View v) {

        Toast.makeText(getContext(), "Estamos procesando tu petición...", Toast.LENGTH_LONG).show();

        //
        //shared preferences de estereotipo
        final SharedPreferences prefs =
                getActivity().getSharedPreferences("estereoripos", Context.MODE_PRIVATE);

        String stereotype = prefs.getString("estereotipo", null);

        //
        //url de la peticion del respectivo servicio
        String url = "https://letstripapp.000webhostapp.com/api/empresa/obtener/itinerario?numero_paradas="+spnItineraryS.getSelectedItem().toString()+"&traslado="+spnItineraryT.getSelectedItem().toString()+"&distancia_recorrido="+spnItineraryD.getSelectedItem().toString()+"&estereotipo="+stereotype;
        String finalUrl = url.replace(" ", "%20");

        //
        //peticion
        HttpClient client = new HttpClient(new OnHttpRequestComplete() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onComplete(Response status) {
                if (status.isSuccess()) {
                    Gson gson = new GsonBuilder().create();
                    try {
                        //
                        //datos de resultado
                        JSONArray jsonArray = new JSONArray(status.getResult());

                        //cargar activity siguiente
                        Intent intentResults = new Intent(getActivity(), SearchResultActivity.class);
                        intentResults.putExtra("dataResult", jsonArray.toString());
                        startActivity(intentResults);

                    } catch (Exception e) {
                        System.out.println("Fallo!");
                        e.printStackTrace();
                    }
                }
            }
        });
        client.excecute(finalUrl);

    }
}
