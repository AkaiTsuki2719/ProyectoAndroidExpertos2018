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
import android.support.v4.app.FragmentManager;
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

public class AtractionSearchFragment extends Fragment implements View.OnClickListener{

    //
    //declaracion de variables
    private Button btnAtractionSearch;
    private Spinner spnAtractionA;
    private Spinner spnAtractionTy;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_atractionsearch, null);

        //
        //cargar los spinner con los valores especificos
        spnAtractionA =  v.findViewById(R.id.spnAtractionA);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterAccess = ArrayAdapter.createFromResource(getContext(),
                R.array.atractionaccess, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterAccess.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spnAtractionA.setAdapter(adapterAccess);

        spnAtractionTy =  v.findViewById(R.id.spnAtractionTy);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterType = ArrayAdapter.createFromResource(getContext(),
                R.array.atractiontype, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spnAtractionTy.setAdapter(adapterType);

        btnAtractionSearch = v.findViewById(R.id.btnAtractionSearch);
        btnAtractionSearch.setOnClickListener(this);

        return v;
        }


    @Override
    public void onClick(View v) {

        Toast.makeText(getContext(), "Estamos procesando tu petición...", Toast.LENGTH_LONG).show();

         //
        //sharedprefences estereotipo contiene el estereotipo relacionado
        final SharedPreferences prefs =
                getActivity().getSharedPreferences("estereoripos", Context.MODE_PRIVATE);

        String stereotype = prefs.getString("estereotipo", null);

        //
        //crear url de la peticion para el respectivo servicio
        String url = "https://letstripapp.000webhostapp.com/api/empresa/obtener/atraccion?tipo_atraccion="+spnAtractionTy.getSelectedItem().toString()+"&tipo_acceso="+spnAtractionA.getSelectedItem().toString()+"&estereotipo="+stereotype;
        String finalUrl = url.replace(" ", "%20");

        //
        //realizar peticion
        HttpClient client = new HttpClient(new OnHttpRequestComplete() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onComplete(Response status) {
                if (status.isSuccess()) {
                    Gson gson = new GsonBuilder().create();
                    try {
                        //
                        //resultados de la peticion
                        JSONArray jsonArray = new JSONArray(status.getResult());

                        //
                        //ejecutar activity de resultados
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
