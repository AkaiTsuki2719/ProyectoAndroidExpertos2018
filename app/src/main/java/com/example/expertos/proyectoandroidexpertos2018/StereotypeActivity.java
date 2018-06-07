package com.example.expertos.proyectoandroidexpertos2018;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.example.expertos.proyectoandroidexpertos2018.utilRest.devazt.networking.HttpClient;
import com.example.expertos.proyectoandroidexpertos2018.utilRest.devazt.networking.OnHttpRequestComplete;
import com.example.expertos.proyectoandroidexpertos2018.utilRest.devazt.networking.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

public class StereotypeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnReady;
    private ProgressBar pgrBLoading2;
    private Spinner spnFirstQuestion;
    private Spinner spnSecondQuestion;
    private Spinner spnThirdQuestion;
    private Spinner spnFourthQuestion;
    private Spinner spnFifthQuestion;
    private ImageButton imgBtnWhat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stereotype);
        start();
    }

    public void start() {


        imgBtnWhat = findViewById(R.id.imgBtnWhat);
        imgBtnWhat.setOnClickListener(this);

        btnReady = findViewById(R.id.btnReady);
        btnReady.setOnClickListener(this);

        pgrBLoading2 = findViewById(R.id.pgrBLoading2);
        pgrBLoading2.setVisibility(View.INVISIBLE);


        //spiner genero
        spnFirstQuestion = findViewById(R.id.spnFirstQuestion);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterGender = ArrayAdapter.createFromResource(this,
                R.array.usergender, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spnFirstQuestion.setAdapter(adapterGender);

        //spiner edad
        spnSecondQuestion = findViewById(R.id.spnSecondQuestion);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterAge = ArrayAdapter.createFromResource(this,
                R.array.agerange, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterAge.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spnSecondQuestion.setAdapter(adapterAge);

        //spiner lugar
        spnThirdQuestion = findViewById(R.id.spnThirdQuestion);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterPlace = ArrayAdapter.createFromResource(this,
                R.array.placepreferences, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterPlace.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spnThirdQuestion.setAdapter(adapterPlace);

        //spiner busca
        spnFourthQuestion = findViewById(R.id.spnFourthQuestion);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterWant = ArrayAdapter.createFromResource(this,
                R.array.activitypreference, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterWant.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spnFourthQuestion.setAdapter(adapterWant);

        //spiner gasta
        spnFifthQuestion = findViewById(R.id.spnFifthQuestion2);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterSpend = ArrayAdapter.createFromResource(this,
                R.array.spendpreference, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterSpend.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spnFifthQuestion.setAdapter(adapterSpend);

    }

    public void openDialog(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Queremos pedirte información relacionada a tus preferencias como turista para así mejorar tu experiencia durante el uso de nuestra aplicación....");

        alertDialogBuilder.setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnReady) {
            //thread to splash activity

            final SharedPreferences prefs =
                    getSharedPreferences("estereoripos", Context.MODE_PRIVATE);

            new Handler().postDelayed(new Runnable() {
                public void run() {

                    String url = "https://letstripapp.000webhostapp.com/api/estereotipo/obtener?genero=" + spnFirstQuestion.getSelectedItem().toString().substring(0, 1) + "&edad=" + spnSecondQuestion.getSelectedItem().toString() + "&preferencia_lugar=" + spnThirdQuestion.getSelectedItem().toString() + "&que_busca=" + spnFourthQuestion.getSelectedItem().toString() + "&disposicion_economica=" + spnFifthQuestion.getSelectedItem().toString();

                    String finalUrl = url.replace(" ", "%20");

                    HttpClient client = new HttpClient(new OnHttpRequestComplete() {
                        @Override
                        public void onComplete(Response status) {
                            if (status.isSuccess()) {
                                Gson gson = new GsonBuilder().create();
                                try {
                                    JSONObject jsono = new JSONObject(status.getResult());

                                    if (prefs.getString("estereotipo", null) == null) {
                                        SharedPreferences.Editor editor = prefs.edit();
                                        editor.putString("estereotipo", jsono.get("estereotipo").toString());
                                        editor.putString("genero", spnFirstQuestion.getSelectedItem().toString());
                                        editor.putString("edad", spnSecondQuestion.getSelectedItem().toString());
                                        editor.putString("lugar", spnThirdQuestion.getSelectedItem().toString());
                                        editor.putString("busca", spnFourthQuestion.getSelectedItem().toString());
                                        editor.putString("dinero", spnFifthQuestion.getSelectedItem().toString());

                                        editor.commit();

                                    }

                                } catch (Exception e) {
                                    System.out.println("Fallo!");
                                    e.printStackTrace();
                                }
                            }

                        }
                    });

                    client.excecute(finalUrl);

                    Intent intent = new Intent(StereotypeActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }, 3000);

            pgrBLoading2.setVisibility(View.VISIBLE);

        } else {
            if (view.getId() == R.id.imgBtnWhat) {

                openDialog(view);

            }
        }

    }
}
