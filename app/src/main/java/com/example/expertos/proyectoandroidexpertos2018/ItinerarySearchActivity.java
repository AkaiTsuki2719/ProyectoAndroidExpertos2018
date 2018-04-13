package com.example.expertos.proyectoandroidexpertos2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;


public class ItinerarySearchActivity extends AppCompatActivity implements View.OnClickListener{


    private Button btnSearchItinerary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary_search);
        start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);//Menu Resource, Menu
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                //start activity
                Intent intentMain = new Intent(ItinerarySearchActivity.this, MainActivity.class);
                startActivity(intentMain);
                return true;
            case R.id.item2:
                //start activity
                Intent intentProduct = new Intent(ItinerarySearchActivity.this, ProductSearchActivity.class);
                startActivity(intentProduct);
                return true;
            case R.id.item3:
                //start activity
                Intent intentPackage= new Intent(ItinerarySearchActivity.this, PackageSearchActivity.class);
                startActivity(intentPackage);
                return true;
            case R.id.item4:
                //start activity
                Intent intentTour = new Intent(ItinerarySearchActivity.this, TourSearchActivity.class);
                startActivity(intentTour);
                return true;
            case R.id.item5:
                //start activity
                Intent intentItinerary = new Intent(ItinerarySearchActivity.this, ItinerarySearchActivity.class);
                startActivity(intentItinerary);
                return true;
            case R.id.item6:
                //start activity
                Intent intentAtraction = new Intent(ItinerarySearchActivity.this, AtractionSearchActivity.class);
                startActivity(intentAtraction);
                return true;
            case R.id.item7:
                //start activity
                Intent intentActivity = new Intent(ItinerarySearchActivity.this, ActivitySearchActivity.class);
                startActivity(intentActivity);
                return true;
            case R.id.item8:
                //start activity
                Intent intentHelp = new Intent(ItinerarySearchActivity.this, HelpActivity.class);
                startActivity(intentHelp);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void start(){

        Spinner spinnerNumberofStops =  findViewById(R.id.spnNumberofStops);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterNumberofStops = ArrayAdapter.createFromResource(this,
                R.array.numberofstops, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterNumberofStops.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerNumberofStops.setAdapter(adapterNumberofStops);

        Spinner spinnerFeeding =  findViewById(R.id.spnFeeding);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterFeeding = ArrayAdapter.createFromResource(this,
                R.array.feeding, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterFeeding.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerFeeding.setAdapter(adapterFeeding);

        Spinner spinnerNumberofDays =  findViewById(R.id.spnNumberofDays);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterNumberofDays = ArrayAdapter.createFromResource(this,
                R.array.numberofdays, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterNumberofDays.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerNumberofDays.setAdapter(adapterNumberofDays);

        btnSearchItinerary= findViewById(R.id.btnSearchItinerary);
        btnSearchItinerary.setOnClickListener(this);


    }

    @Override
    public void onClick(View view){

        //start activity buy
        Intent intentSearchItinerary = new Intent(ItinerarySearchActivity.this, ResultSearchActivity.class);
        startActivity(intentSearchItinerary);

    }
}
