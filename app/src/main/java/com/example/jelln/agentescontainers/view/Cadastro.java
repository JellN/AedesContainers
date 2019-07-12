package com.example.jelln.agentescontainers.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jelln.agentescontainers.MainActivity;
import com.example.jelln.agentescontainers.R;
import com.example.jelln.agentescontainers.control.Conexao;
import com.example.jelln.agentescontainers.model.Containers;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class Cadastro extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Spinner spinnerT;
    Spinner spinnerQ;

    double latitude;
    double longitude;
    Button button1;
    private FirebaseAuth auth;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        inicializarFirebase();
        auth = Conexao.getFirebaseAuth();
        user = Conexao.getFirebaseUser();
        spinnerT = (Spinner) findViewById(R.id.spinnerT);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this, R.array.planets_array, android.R.layout.simple_spinner_item);
        spinnerT.setAdapter(arrayAdapter);
        spinnerQ = (Spinner) findViewById(R.id.spinnerQ);
        ArrayAdapter arrayAdapter2 = ArrayAdapter.createFromResource(this, R.array.quantidade, android.R.layout.simple_spinner_item);
        spinnerQ.setAdapter(arrayAdapter2);

        /*
        String state = ss.getStringExtra("state");
        String country = ss.getStringExtra("country");
         latitude = ss.getStringExtra("latitude");
         longitude = ss.getStringExtra("longitude");


        numero2 = (EditText) findViewById(R.id.editText2);
        numero3 = (EditText) findViewById(R.id.editText3);
        numero4 = (EditText) findViewById(R.id.editText4);
        numero5 = (EditText) findViewById(R.id.editText5);


        numero2.setText(String.format(state));
        numero3.setText(String.format(country));*/



        button1 = (Button) findViewById(R.id.botaocadastro);


        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v ) {

                Intent ss = getIntent();


                Bundle bundle = ss.getExtras();

                String city = bundle.getString("city");
                String state = bundle.getString("state");
                String country = bundle.getString("country");
                latitude = bundle.getDouble("latitude");
                longitude = bundle.getDouble("longitude");

                Containers c = new Containers();

                c.setCity(city);
                c.setState(state);
                c.setCountry(country);
                c.setQuantidade(spinnerQ.getSelectedItem().toString());
                c.setTipo(spinnerT.getSelectedItem().toString());
                c.setSearch(c.getTipo().toLowerCase());

                c.setLongitude(longitude);
                c.setLatitude(latitude);
                c.setEndereco(String.valueOf(new Date().getTime()));
                c.setID(user.getUid());
                databaseReference.child("Containers").child(c.getEndereco()).setValue(c);
                databaseReference.child("UsersContainers").child(c.getID()).child(c.getEndereco()).setValue(c);

                sair("Container adicionado");

            }
        });

    }

    private void sair(String msg) {
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
        Intent ss = new Intent(this, MainActivity.class);
        ss.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(ss);
        finish();
    }

    private void inicializarFirebase() {

        FirebaseApp.initializeApp(Cadastro.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main2, menu);

        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_retornar){
            Intent ss = new Intent(this, MainActivity.class);
            ss.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(ss);
            finish();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent ss = new Intent(this, MainActivity.class);
        ss.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(ss);
        finish();
        super.onBackPressed();
    }
}

