package com.example.expertos.proyectoandroidexpertos2018;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;

public class StereotypeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnReady;
    private ProgressBar pgrBLoading2;
    private Spinner spnFirstQuestion;
    private Spinner spnSecondQuestion;
    private Spinner spnThirdQuestion;
    private Spinner spnFourthQuestion;
    private Spinner spnFifthQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stereotype);
        start();
    }

    public void start(){


        btnReady= findViewById(R.id.btnReady);
        btnReady.setOnClickListener( this);

        pgrBLoading2= findViewById(R.id.pgrBLoading2);
        pgrBLoading2.setVisibility(View.INVISIBLE);


        //spiner genero
        spnFirstQuestion =  findViewById(R.id.spnFirstQuestion);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterGender = ArrayAdapter.createFromResource(this,
                R.array.usergender, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spnFirstQuestion.setAdapter(adapterGender);

        //spiner edad
        spnSecondQuestion =  findViewById(R.id.spnSecondQuestion);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterAge = ArrayAdapter.createFromResource(this,
                R.array.agerange, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterAge.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spnSecondQuestion.setAdapter(adapterAge);

        //spiner lugar
        spnThirdQuestion =  findViewById(R.id.spnThirdQuestion);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterPlace = ArrayAdapter.createFromResource(this,
                R.array.placepreferences, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterPlace.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spnThirdQuestion.setAdapter(adapterPlace);

        //spiner busca
        spnFourthQuestion =  findViewById(R.id.spnFourthQuestion);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterWant = ArrayAdapter.createFromResource(this,
                R.array.activitypreference, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterWant.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spnFourthQuestion.setAdapter(adapterWant);

        //spiner gasta
        spnFifthQuestion =  findViewById(R.id.spnFifthQuestion2);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterSpend = ArrayAdapter.createFromResource(this,
                R.array.spendpreference, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterSpend.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spnFifthQuestion.setAdapter(adapterSpend);

    }

    @Override
    public void onClick(View view){

        //thread to splash activity
        new Handler().postDelayed(new Runnable(){
            public void run(){
                // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicaci�n
                Intent intent = new Intent(StereotypeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            };
        }, 3000);


        pgrBLoading2.setVisibility(View.VISIBLE);

    }
}
