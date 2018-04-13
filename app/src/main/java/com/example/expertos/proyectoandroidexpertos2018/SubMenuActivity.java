package com.example.expertos.proyectoandroidexpertos2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class SubMenuActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton imgBtnProduct;
    private ImageButton imgBtnPackage;
    private ImageButton imgBtnTour;
    private ImageButton imgBtnItinerary;
    private ImageButton imgBtnAtraction;
    private ImageButton imgBtnActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_menu);
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
                Intent intentMain = new Intent(SubMenuActivity.this, MainActivity.class);
                startActivity(intentMain);
                return true;
            case R.id.item2:
                //start activity
                Intent intentProduct = new Intent(SubMenuActivity.this, ProductSearchActivity.class);
                startActivity(intentProduct);
                return true;
            case R.id.item3:
                //start activity
                Intent intentPackage= new Intent(SubMenuActivity.this, PackageSearchActivity.class);
                startActivity(intentPackage);
                return true;
            case R.id.item4:
                //start activity
                Intent intentTour = new Intent(SubMenuActivity.this, TourSearchActivity.class);
                startActivity(intentTour);
                return true;
            case R.id.item5:
                //start activity
                Intent intentItinerary = new Intent(SubMenuActivity.this, ItinerarySearchActivity.class);
                startActivity(intentItinerary);
                return true;
            case R.id.item6:
                //start activity
                Intent intentAtraction = new Intent(SubMenuActivity.this, AtractionSearchActivity.class);
                startActivity(intentAtraction);
                return true;
            case R.id.item7:
                //start activity
                Intent intentActivity = new Intent(SubMenuActivity.this, ActivitySearchActivity.class);
                startActivity(intentActivity);
                return true;
            case R.id.item8:
                //start activity
                Intent intentHelp = new Intent(SubMenuActivity.this, HelpActivity.class);
                startActivity(intentHelp);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //inicialize components
    public void start(){

        imgBtnProduct= findViewById(R.id.imgBtnProduct);
        imgBtnProduct.setOnClickListener( this);

        imgBtnPackage= findViewById(R.id.imgBtnPackage);
        imgBtnPackage.setOnClickListener( this);

        imgBtnTour= findViewById(R.id.imgBtnTour);
        imgBtnTour.setOnClickListener( this);

        imgBtnItinerary= findViewById(R.id.imgBtnItinerary);
        imgBtnItinerary.setOnClickListener( this);

        imgBtnAtraction= findViewById(R.id.imgBtnAtraction);
        imgBtnAtraction.setOnClickListener( this);

        imgBtnActivity= findViewById(R.id.imgBtnActivity);
        imgBtnActivity.setOnClickListener( this);
    }


    @Override
    public void onClick(View view){

        if(view.getId()==imgBtnProduct.getId()) {
            //start activity buy
            Intent intentProduct = new Intent(SubMenuActivity.this, ProductSearchActivity.class);
            startActivity(intentProduct);
        }else{
            if(view.getId()==imgBtnPackage.getId()) {
                //start activity buy
                Intent intentPackage = new Intent(SubMenuActivity.this, PackageSearchActivity.class);
                startActivity(intentPackage);
            }else{
                if(view.getId()==imgBtnTour.getId()) {
                    //start activity buy
                    Intent intentTour = new Intent(SubMenuActivity.this, TourSearchActivity.class);
                    startActivity(intentTour);
                }else{
                    if(view.getId()==imgBtnItinerary.getId()) {
                        //start activity buy
                        Intent intentItinerary = new Intent(SubMenuActivity.this, ItinerarySearchActivity.class);
                        startActivity(intentItinerary);
                    }else{
                        if(view.getId()==imgBtnAtraction.getId()) {
                            //start activity buy
                            Intent intentAtraction = new Intent(SubMenuActivity.this, AtractionSearchActivity.class);
                            startActivity(intentAtraction);
                        }else{
                            if(view.getId()==imgBtnActivity.getId()) {
                                //start activity buy
                                Intent intentActivity = new Intent(SubMenuActivity.this, ActivitySearchActivity.class);
                                startActivity(intentActivity);
                            }
                        }
                    }
                }
            }
        }




    }

}
