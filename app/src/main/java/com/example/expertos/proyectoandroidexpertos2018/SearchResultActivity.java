package com.example.expertos.proyectoandroidexpertos2018;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchResultActivity extends AppCompatActivity {

    //inicializar variables
    ImageView imgVBack2;


    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        //inicializar componentes respectivos
        imgVBack2 = findViewById(R.id.imgVBack2);
        imgVBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intentDetail = new Intent(SearchResultActivity.this, MainActivity.class);
                    startActivity(intentDetail);
            }
        });


        //
        //decidir que cargar en la vista
        if (SearchResultActivity.this.getIntent().getStringExtra("dataBack") != null) {

            // 1. pass context and data to the custom adapter
            MyAdapter adapter = null;
            try {
                adapter = new MyAdapter(this, generateData(this.getIntent().getStringExtra("dataBack")));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // if extending Activity 2. Get ListView from activity_main.xml
            ListView listView = findViewById(R.id.lVResults);

            // 3. setListAdapter
            listView.setAdapter(adapter); //if extending Activity

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                    //start activity buy
                    try {
                        JSONArray jsonArray = new JSONArray(SearchResultActivity.this.getIntent().getStringExtra("dataBack"));
                        JSONObject objJsonCompany =  jsonArray.getJSONObject(position);
                        JSONObject objJsonDetailed = objJsonCompany.getJSONObject("Empresa");
                        Intent intentDetail = new Intent(SearchResultActivity.this, DetailsActivity.class);
                        intentDetail.putExtra("dataDetail", objJsonDetailed.toString());
                        intentDetail.putExtra("dataFull", jsonArray.toString());
                        startActivity(intentDetail);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                // TODO Auto-generated method stub


            });
        }else {

            // 1. pass context and data to the custom adapter
            MyAdapter adapter = null;

            try {
                adapter = new MyAdapter(this, generateData(this.getIntent().getStringExtra("dataResult")));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // if extending Activity 2. Get ListView from activity_main.xml
            ListView listView = findViewById(R.id.lVResults);

            // 3. setListAdapter
            listView.setAdapter(adapter); //if extending Activity

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                    //start activity buy
                    try {
                        JSONArray jsonArray = new JSONArray(SearchResultActivity.this.getIntent().getStringExtra("dataResult"));
                        JSONObject objJsonCompany =  jsonArray.getJSONObject(position);
                        JSONObject objJsonDetailed = objJsonCompany.getJSONObject("Empresa");

                        Intent intentDetail = new Intent(SearchResultActivity.this, DetailsActivity.class);
                        intentDetail.putExtra("dataDetail", objJsonDetailed.toString());
                        intentDetail.putExtra("dataFull", jsonArray.toString());
                        startActivity(intentDetail);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                // TODO Auto-generated method stub


            });
        }

    }


    private ArrayList<Model> generateData(String dataResult) throws JSONException {

        //
        //modelo de la lista
        JSONArray jsonArray = new JSONArray(dataResult);
        ArrayList<Model> models = new ArrayList<Model>();

        for(int currentCompany=0; currentCompany < jsonArray.length(); currentCompany++){

            JSONObject objJsonCompany =  jsonArray.getJSONObject(currentCompany);
            JSONObject objJsonDetailed = objJsonCompany.getJSONObject("Empresa");

            models.add(new Model(R.drawable.options, objJsonDetailed.get("nombre").toString(), currentCompany+""));
        }

        return models;
    }


}
