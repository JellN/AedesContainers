package com.example.jelln.agentescontainers.view;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jelln.agentescontainers.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class local extends FragmentActivity implements OnMapReadyCallback {
Intent intent;
double latitude=0, longitude=0;
String tipo, quantidade, endereco;
    private GoogleMap mMap;
    private Marker marcador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
         intent = getIntent();
        Bundle bundle = intent.getExtras();
        latitude = bundle.getDouble("latitude");
        longitude = bundle.getDouble("longitude");
        tipo = bundle.getString("tipo");
        quantidade = bundle.getString("quantidade");
        endereco = bundle.getString("endereco");


    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getAllLocations();
    }
    private void getAllLocations() {

            LatLng latLng = new LatLng (latitude, longitude);
            String tipol = tipo;
            String quantidadel = quantidade;
            addGreenMarker(latLng, tipol, quantidadel);




    }
    private void addGreenMarker(LatLng latLng, String tipo, String quantidade) {

        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        MarkerOptions markerOptions=new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Tipo: "+ tipo+" - Quantidade: "+ quantidade);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        // markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.marcador));
     marcador =  mMap.addMarker(markerOptions);
        LatLng zoom = new LatLng(latitude, longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(zoom));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(zoom, 17));

    }
}
