package com.example.jelln.agentescontainers.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jelln.agentescontainers.R;
import com.example.jelln.agentescontainers.model.Chat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    public static final int MSG_TYPE_LEFT =0;
    public static final int MSG_TYPE_RIGHT =1;
    private Context mContext;
    private List<Chat> mChat;
    private String imageurl;
    FirebaseUser user;
    public MessageAdapter(Context mContext, List<Chat> mChat, String imageurl){



        this.mChat = mChat;
        this.mContext = mContext;
        this.imageurl = imageurl;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       if(viewType == MSG_TYPE_RIGHT){
           View view = LayoutInflater.from(mContext).inflate(R.layout.char_item_right, parent, false);

           return new ViewHolder(view);
       }else {
           View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, parent, false);

           return new ViewHolder(view);
       }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Chat chat = mChat.get(position);
        holder.show_message.setText(chat.getMessage());
        if(imageurl == null){
            holder.profile_image.setImageResource(R.drawable.fuke2);
        }else{
            Glide.with(mContext).load(imageurl).into(holder.profile_image);

        }

if(position == mChat.size()-1){
            if(chat.isIsseen()){
                holder.txt_enviado.setText("visualizado");
            }else{
                holder.txt_enviado.setText("entregue");
            }
}else{
          holder.txt_enviado.setVisibility(View.GONE);
}
    }

    @Override
    public int getItemCount() {
        return mChat.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {

        public TextView show_message;
        public ImageView profile_image;
        public TextView txt_enviado;
        public ViewHolder(View itemView){
            super(itemView);
            show_message = itemView.findViewById(R.id.show_message);
            profile_image = itemView.findViewById(R.id.msgperfil);
            txt_enviado = itemView.findViewById(R.id.txt_entregue);


        }

    }

    @Override
    public int getItemViewType(int position) {
        user = FirebaseAuth.getInstance().getCurrentUser();
        if(mChat.get(position).getSender().equals(user.getUid())){
            return  MSG_TYPE_RIGHT;
        }else{
            return  MSG_TYPE_LEFT;
        }
    }
}
