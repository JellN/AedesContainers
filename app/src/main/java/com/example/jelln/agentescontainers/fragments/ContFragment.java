package com.example.jelln.agentescontainers.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.jelln.agentescontainers.Adapter.ContainerAdapter;
import com.example.jelln.agentescontainers.Adapter.UserAdapter;
import com.example.jelln.agentescontainers.R;
import com.example.jelln.agentescontainers.control.Conexao;
import com.example.jelln.agentescontainers.model.Containers;
import com.example.jelln.agentescontainers.model.Usuarios;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ContFragment extends Fragment {

    private RecyclerView recyclerView;
    private ContainerAdapter containerAdapter;
    private List<Containers> mCont;
    EditText search_conts;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cont, container, false);

        // Inflate the layout for this fragment
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mCont = new ArrayList<>();
        readUsers();

        search_conts = view.findViewById(R.id.search_cont);
        search_conts.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchConts(charSequence.toString().toLowerCase());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return view;
    }



    private void searchConts(String s) {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Query query = FirebaseDatabase.getInstance().getReference("UsersContainers").child(user.getUid()).orderByChild("search")
                .startAt(s)
                .endAt(s+"\uf8ff");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mCont.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Containers c = snapshot.getValue(Containers.class);
                    if(!c.getTipo().equals(user.getUid())){
                        mCont.add(c);
                    }
                }
                containerAdapter = new ContainerAdapter(getContext(), mCont);
                recyclerView.setAdapter(containerAdapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void readUsers() {
        FirebaseAuth firebaseAuth = Conexao.getFirebaseAuth();
        final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("UsersContainers").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (search_conts.getText().toString().equals("")) {
                    mCont.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Containers containers = snapshot.getValue(Containers.class);
                        assert containers != null;
                        if (!containers.getTipo().equals(firebaseUser.getUid())) {
                            mCont.add(containers);
                        }
                    }

                    containerAdapter = new ContainerAdapter(getContext(), mCont);
                    recyclerView.setAdapter(containerAdapter);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
