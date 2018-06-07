package com.example.expertos.proyectoandroidexpertos2018;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;

public class PlaceInformationFragment extends Fragment implements View.OnClickListener, OnMapReadyCallback {

    DrawerLayout menu;
    private GoogleMap mMap;
    ImageView imgVBack;
    TextView txtVAbout;
    ImageView imgVCompanyP;
    Double lat;
    Double lon;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_place_information, null);

        imgVBack = v.findViewById(R.id.imgVBack);
        txtVAbout = v.findViewById(R.id.txtVAbout);
        imgVCompanyP = v.findViewById(R.id.imgVCompanyP);

        imgVBack.setOnClickListener(new View.OnClickListener() {
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
       // Toast.makeText(getContext(), getActivity().getIntent().getStringExtra("dataDetail"), Toast.LENGTH_LONG).show();
        JSONObject jsonDetailCompany;
        JSONArray jsonDetailImages;
        JSONObject jsonImage;

        try {
            jsonDetailCompany = new JSONObject(getActivity().getIntent().getStringExtra("dataDetail"));

            jsonDetailImages = jsonDetailCompany.getJSONArray("img");

            jsonImage = jsonDetailImages.getJSONObject(0);

            txtVAbout.setText(jsonDetailCompany.get("descripcion").toString());

            String imageUrl=jsonImage.get("imgRoute").toString();
            String image = imageUrl.replace("\\", "");

            Picasso.with(getContext()) //Context
                    .load(image) //URL/FILE
                    .into(imgVCompanyP);//an ImageView Object to show the loaded image;


            lat = Double.parseDouble(jsonDetailCompany.get("latitud").toString());
            lon = Double.parseDouble(jsonDetailCompany.get("longitud").toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }


        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        return v;
    }


    @Override
    public void onClick(View v) {
        menu.openDrawer(Gravity.LEFT);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        // Add a marker in Sydney, Australia, and move the camera.
        LatLng marker = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(marker).title("Marker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14.0f));

    }
}