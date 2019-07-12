package com.example.jelln.agentescontainers.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jelln.agentescontainers.R;
import com.example.jelln.agentescontainers.fragments.MapsFragment;
import com.example.jelln.agentescontainers.model.Chat;
import com.example.jelln.agentescontainers.model.Containers;
import com.example.jelln.agentescontainers.model.Usuarios;
import com.example.jelln.agentescontainers.view.MessageActivity;
import com.example.jelln.agentescontainers.view.local;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ContainerAdapter extends RecyclerView.Adapter<ContainerAdapter.ViewHolder> {

    private Context mContext;
    private List<Containers> mCon;

    public ContainerAdapter(Context mContext, List<Containers> mCons){

        this.mCon = mCons;
        this.mContext = mContext;

    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.container_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Containers containers = mCon.get(position);
        holder.tipo.setText(containers.getTipo().toLowerCase());
        holder.quantidade.setText(containers.getQuantidade().toLowerCase());

        if(containers.getTipo().equals("Pneus")){
            holder.profile_image.setImageResource(R.drawable.pneus);

        }
        else if(containers.getTipo().equals("Garrafas")){
            holder.profile_image.setImageResource(R.drawable.garrafa);

        }
        else if(containers.getTipo().equals("Lixo")){
            holder.profile_image.setImageResource(R.drawable.lixo);

        }
        else if(containers.getTipo().equals("Tonel")){
            holder.profile_image.setImageResource(R.drawable.tonel);

        }
        else if(containers.getTipo().equals("Vaso")){
            holder.profile_image.setImageResource(R.drawable.vaso);

        }else{
            holder.profile_image.setImageResource(R.drawable.fuke2);

        }
holder.botao_mp.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
alert("Clicou: "+containers.getTipo());
        Intent intent = new Intent(mContext, local.class);
        Bundle bundle = new Bundle();
        bundle.putDouble("latitude", containers.getLatitude());
        bundle.putDouble("longitude", containers.getLongitude());
        bundle.putString("tipo", containers.getTipo());
        bundle.putString("quantidade", containers.getQuantidade());
        bundle.putString("endereco", containers.getEndereco());
        intent.putExtras(bundle);
        mContext.startActivity(intent);


    }
});


holder.botao_del.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("UsersContainers").child(user.getUid()).child(containers.
                getEndereco());
        databaseReference.removeValue();
        DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("Containers").child(containers.getEndereco());
        databaseReference2.removeValue();
        alert("Container: "+containers.getTipo()+" removido.");

    }
});
    }

    @Override
    public int getItemCount() {
        return mCon.size();
    }


    public class ViewHolder extends  RecyclerView.ViewHolder {
        public  TextView quantidade;
        public TextView tipo;
        public ImageView profile_image;
        public ImageButton botao_mp;
        public ImageButton botao_del;
        public ViewHolder(View itemView){
            super(itemView);
            tipo = itemView.findViewById(R.id.tipo);
            profile_image = itemView.findViewById(R.id.container_img);
            quantidade = itemView.findViewById(R.id.quantidade);
            botao_mp = itemView.findViewById(R.id.map_go);
            botao_del = itemView.findViewById(R.id.map_delete);



        }

    }

  public void   alert(String msg){
      Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();

  }
}
