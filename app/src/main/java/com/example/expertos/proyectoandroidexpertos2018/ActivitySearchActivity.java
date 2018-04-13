package com.example.expertos.proyectoandroidexpertos2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ActivitySearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
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
                Intent intentMain = new Intent(ActivitySearchActivity.this, MainActivity.class);
                startActivity(intentMain);
                return true;
            case R.id.item2:
                //start activity
                Intent intentProduct = new Intent(ActivitySearchActivity.this, ProductSearchActivity.class);
                startActivity(intentProduct);
                return true;
            case R.id.item3:
                //start activity
                Intent intentPackage= new Intent(ActivitySearchActivity.this, PackageSearchActivity.class);
                startActivity(intentPackage);
                return true;
            case R.id.item4:
                //start activity
                Intent intentTour = new Intent(ActivitySearchActivity.this, TourSearchActivity.class);
                startActivity(intentTour);
                return true;
            case R.id.item5:
                //start activity
                Intent intentItinerary = new Intent(ActivitySearchActivity.this, ItinerarySearchActivity.class);
                startActivity(intentItinerary);
                return true;
            case R.id.item6:
                //start activity
                Intent intentAtraction = new Intent(ActivitySearchActivity.this, AtractionSearchActivity.class);
                startActivity(intentAtraction);
                return true;
            case R.id.item7:
                //start activity
                Intent intentActivity = new Intent(ActivitySearchActivity.this, ActivitySearchActivity.class);
                startActivity(intentActivity);
                return true;
            case R.id.item8:
                //start activity
                Intent intentHelp = new Intent(ActivitySearchActivity.this, HelpActivity.class);
                startActivity(intentHelp);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
