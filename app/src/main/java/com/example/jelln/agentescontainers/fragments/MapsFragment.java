package com.example.jelln.agentescontainers.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jelln.agentescontainers.MainActivity;
import com.example.jelln.agentescontainers.Notifications.Token;
import com.example.jelln.agentescontainers.R;
import com.example.jelln.agentescontainers.control.Conexao;
import com.example.jelln.agentescontainers.control.PermissionUtils;
import com.example.jelln.agentescontainers.model.Containers;
import com.example.jelln.agentescontainers.model.Usuarios;
import com.example.jelln.agentescontainers.view.Cadastro;
import com.example.jelln.agentescontainers.view.MessageActivity;
import com.example.jelln.agentescontainers.view.login;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;


import com.example.jelln.agentescontainers.R;
import com.google.firebase.iid.FirebaseInstanceId;


public class MapsFragment extends Fragment implements
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        GoogleMap.OnMyLocationChangeListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback
{
    public MapsFragment(){

    }
    private NavigationView nav;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private LatLngBounds limite;
    private Location cadastrar;
    /**
     * Request code for location permission request.
     *
     * @see #onRequestPermissionsResult(int, String[], int[])
     */
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    int PLACE_PICKER_REQUEST = 1;
    /**
     * Flag indicating whether a requested permission has been denied after returning in
     * {@link #onRequestPermissionsResult(int, String[], int[])}.
     */
    private boolean mPermissionDenied = false;
    private  TextView marcar;

    Button button1;
    LatLng PERTH=null;
    private Marker mPerth;
    public GoogleMap mMap;
    Geocoder geocoder;
    String city;
    String state;
    String country;
    private List<Containers> listContainers = new ArrayList<Containers>();
    String Id;
    SupportMapFragment mapFragment;
    ImageButton map_atualiza;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        iniciomaps();
        user = FirebaseAuth.getInstance().getCurrentUser();
        marcar = view.findViewById(R.id.marcar_local);
        marcar.setVisibility(View.INVISIBLE);
        map_atualiza = view.findViewById(R.id.map_refresh);
        eventoClick();
        eventoDatabase();



        updateToken(FirebaseInstanceId.getInstance().getToken());


        return view;
    }

    private void iniciomaps() {
        mapFragment=(SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapfragment);
        geocoder = new Geocoder(getContext(), Locale.getDefault());
        if(mapFragment == null){
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            mapFragment = SupportMapFragment.newInstance();
            ft.replace(R.id.mapfragment, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
    }

    private void updateToken(String token){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Tokens");
        Token token1 = new Token(token);
        reference.child(user.getUid()).setValue(token1);
    }

    private void eventoClick() {

        marcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastro(cadastrar);
            }
        });
        map_atualiza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Atualizando mapa", Toast.LENGTH_SHORT).show();
                mMap.clear();
                eventoDatabase();
            }
        });
    }

    private void cadastro(Location local) {
        Containers c = new Containers();

        try {
            List<Address> addresses = geocoder.getFromLocation(local.getLatitude(), local.getLongitude(), 1);

            if  (addresses != null && addresses.size() > 0){

                city = addresses.get(0).getLocality();
                state = addresses.get(0).getAdminArea();
                country = addresses.get(0).getCountryName();

            }

        }

        catch (IOException e) {
            e.printStackTrace();
        }


        c.setLongitude(local.getLongitude());
        c.setLatitude(local.getLatitude());
        PERTH = new LatLng(c.getLatitude(), c.getLongitude());
        mPerth = mMap.addMarker(new MarkerOptions().position(PERTH).title("Seu local"));
        mPerth.setTag(0);
        c.setEndereco(String.valueOf(new Date().getTime()));
        c.setMarker(mPerth);
        mPerth.remove();


        Intent ss = new Intent(getActivity(), Cadastro.class);
        Bundle bundle = new Bundle();

        bundle.putString("city", city);
        bundle.putString("state", state);
        bundle.putString("country", country);
        bundle.putDouble("latitude", local.getLatitude());
        bundle.putDouble("longitude", local.getLongitude());
        ss.putExtras(bundle);
        getActivity().startActivity(ss);



    }



    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
        enableMyLocation();
        mMap.setOnMyLocationChangeListener(this);
        Toast.makeText(getContext(), "Atualizando mapa", Toast.LENGTH_SHORT).show();


    }
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(getActivity(), LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);


        }
    }


    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(getContext(), "Sua Localização Atual", Toast.LENGTH_SHORT).show();

        return false;
    }

    @Override
    public void onMyLocationChange(Location location) {
        LatLng zoom = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLng(zoom));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(zoom, 17));
        limite = new LatLngBounds(
                new LatLng(location.getLatitude(),location.getLongitude() ), new LatLng(location.getLatitude(), location.getLongitude()));
// Constrain the camera target to the Adelaide bounds.
        mMap.setLatLngBoundsForCameraTarget(limite);
        mMap.setMinZoomPreference(17.0f);
        mMap.setMaxZoomPreference(17.0f);
        cadastrar = location;
        marcar.setVisibility(View.VISIBLE);

    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(getContext(), "Dados de Localização:\n" + location.getLatitude(), Toast.LENGTH_LONG).show();

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            Intent ss = new Intent(getActivity(), MainActivity.class);
            getActivity().startActivity(ss);        } else {
            // Display the missing permission error dialog when the fragments resume.
            mPermissionDenied = true;
        }
    }
    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getChildFragmentManager(), "dialog");
    }

    public void eventoDatabase() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Containers");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.getValue() != null)
                    getAllLocations((Map<String, Object>) dataSnapshot.getValue());
            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void getAllLocations(Map<String, Object> locations) {

        for(Map.Entry<String, Object> entry : locations.entrySet()) {
            Date newDate = new Date(Long.valueOf(entry.getKey()));
            Map singleLocation = (Map) entry.getValue();
            LatLng latLng = new LatLng ((Double)singleLocation.get("latitude"), (Double)singleLocation.get("longitude"));
            String tipo = String.valueOf(singleLocation.get("tipo"));
            String quantidade = String.valueOf(singleLocation.get("quantidade"));
            addGreenMarker(newDate, latLng, tipo, quantidade);



        }
    }

    private void addGreenMarker(Date newDate, LatLng latLng, String tipo, String quantidade) {

        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        MarkerOptions markerOptions=new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(dt.format(newDate)+" - Tipo: "+ tipo.toString()+" - Quantidade: "+ quantidade.toString());
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        // markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.marcador));
        mPerth =    mMap.addMarker(markerOptions);


    }
}
