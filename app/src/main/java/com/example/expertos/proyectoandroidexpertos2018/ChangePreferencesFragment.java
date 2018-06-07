package com.example.expertos.proyectoandroidexpertos2018;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.expertos.proyectoandroidexpertos2018.utilRest.devazt.networking.HttpClient;
import com.example.expertos.proyectoandroidexpertos2018.utilRest.devazt.networking.OnHttpRequestComplete;
import com.example.expertos.proyectoandroidexpertos2018.utilRest.devazt.networking.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

public class ChangePreferencesFragment extends Fragment implements View.OnClickListener {

    private Button btnReady;
    private ProgressBar pgrBLoading2;
    private Spinner spnFirstQuestion;
    private Spinner spnSecondQuestion;
    private Spinner spnThirdQuestion;
    private Spinner spnFourthQuestion;
    private Spinner spnFifthQuestion;
    private ImageButton imgBtnWhat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_change_preferences, null);

        imgBtnWhat = v.findViewById(R.id.imgBtnWhat);
        imgBtnWhat.setOnClickListener(this);

        btnReady = v.findViewById(R.id.btnReady);
        btnReady.setOnClickListener(this);

        pgrBLoading2 = v.findViewById(R.id.pgrBLoading2);
        pgrBLoading2.setVisibility(View.INVISIBLE);


        //spiner genero
        spnFirstQuestion = v.findViewById(R.id.spnFirstQuestion);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterGender = ArrayAdapter.createFromResource(getContext(),
                R.array.usergender, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spnFirstQuestion.setAdapter(adapterGender);

        //spiner edad
        spnSecondQuestion = v.findViewById(R.id.spnSecondQuestion);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterAge = ArrayAdapter.createFromResource(getContext(),
                R.array.agerange, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterAge.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spnSecondQuestion.setAdapter(adapterAge);

        //spiner lugar
        spnThirdQuestion = v.findViewById(R.id.spnThirdQuestion);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterPlace = ArrayAdapter.createFromResource(getContext(),
                R.array.placepreferences, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterPlace.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spnThirdQuestion.setAdapter(adapterPlace);

        //spiner busca
        spnFourthQuestion = v.findViewById(R.id.spnFourthQuestion);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterWant = ArrayAdapter.createFromResource(getContext(),
                R.array.activitypreference, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterWant.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spnFourthQuestion.setAdapter(adapterWant);

        //spiner gasta
        spnFifthQuestion = v.findViewById(R.id.spnFifthQuestion2);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterSpend = ArrayAdapter.createFromResource(getContext(),
                R.array.spendpreference, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterSpend.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spnFifthQuestion.setAdapter(adapterSpend);


        SharedPreferences prefs = getActivity().getSharedPreferences("estereoripos", Context.MODE_PRIVATE);

        spnFirstQuestion.setSelection(adapterGender.getPosition(prefs.getString("genero", null)));
        spnSecondQuestion.setSelection(adapterAge.getPosition(prefs.getString("edad", null)));
        spnThirdQuestion.setSelection(adapterPlace.getPosition(prefs.getString("lugar", null)));
        spnFourthQuestion.setSelection(adapterWant.getPosition(prefs.getString("busca", null)));
        spnFifthQuestion.setSelection(adapterSpend.getPosition(prefs.getString("dinero", null)));

        return v;
    }


    public void openDialog(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setMessage("Queremos pedirte información relacionada a tus preferencias como turista para así mejorar tu experiencia durante el uso de nuestra aplicación....");

        alertDialogBuilder.setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    public void onClick(View view) {

        if (view.getId() == R.id.btnReady) {

            final SharedPreferences prefs = this.getActivity().getSharedPreferences("estereoripos", Context.MODE_PRIVATE);



            if (prefs.getString("estereotipo", null) == null) {

            } else {

                String url = "https://letstripapp.000webhostapp.com/api/estereotipo/obtener?genero="+spnFirstQuestion.getSelectedItem().toString().substring(0,1)+"&edad="+ spnSecondQuestion.getSelectedItem().toString()+"&preferencia_lugar="+ spnThirdQuestion.getSelectedItem().toString()+"&que_busca="+ spnFourthQuestion.getSelectedItem().toString()+"&disposicion_economica="+ spnFifthQuestion.getSelectedItem().toString();

                String finalUrl = url.replace(" ", "%20");

                pgrBLoading2.setVisibility(View.VISIBLE);

                HttpClient client = new HttpClient(new OnHttpRequestComplete() {
                    @Override
                    public void onComplete(Response status) {
                        if (status.isSuccess()) {
                            Gson gson = new GsonBuilder().create();
                            try {
                                JSONObject jsono = new JSONObject(status.getResult());


                                prefs.edit().remove("estereotipo").commit();
                                prefs.edit().remove("genero").commit();
                                prefs.edit().remove("edad").commit();
                                prefs.edit().remove("lugar").commit();
                                prefs.edit().remove("busca").commit();
                                prefs.edit().remove("dinero").commit();


                                SharedPreferences.Editor editor = prefs.edit();
                                editor.putString("estereotipo", jsono.get("estereotipo").toString());
                                editor.putString("genero", spnFirstQuestion.getSelectedItem().toString());
                                editor.putString("edad", spnSecondQuestion.getSelectedItem().toString());
                                editor.putString("lugar", spnThirdQuestion.getSelectedItem().toString());
                                editor.putString("busca", spnFourthQuestion.getSelectedItem().toString());
                                editor.putString("dinero", spnFifthQuestion.getSelectedItem().toString());

                                editor.commit();

                            } catch (Exception e) {
                                System.out.println("Fallo!");
                                e.printStackTrace();
                            }
                        }
                    }
                });
                client.excecute(finalUrl);

                FragmentManager fragmentManager = getFragmentManager();

                fragmentManager.beginTransaction().replace(R.id.container, new HomeFragment()).addToBackStack(null).commit();


            }


        } else {
            if (view.getId() == R.id.imgBtnWhat) {

                openDialog(view);

            }
        }

    }


}
