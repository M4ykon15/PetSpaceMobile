package com.example.petspace.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.petspace.HomeActivity;
import com.example.petspace.InfoActivity;
import com.example.petspace.LoginActivity;
import com.example.petspace.Model.Animais;
import com.example.petspace.R;

import java.util.List;

public class AdapteRecyclerViewAnimais extends RecyclerView.Adapter<AdapteRecyclerViewAnimais.ViewHolder>{

    private List<Animais> mValues;
    public AdapteRecyclerViewAnimais(List<Animais> listaDeItens) {
        this.mValues = listaDeItens;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list_card, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder,int position) {
        holder.mItem = mValues.get(position);

        holder.mTextViewNome.setText(mValues.get(position).getNome());
        holder.mTextViewRaca.setText(mValues.get(position).getRaca());
        holder.mTextViewIdade.setText(mValues.get(position).getIdade());

        if(mValues.get(position).getSexo().equals("F")) {
            holder.mImageViewGener.setImageResource(R.drawable.ic_male);
        } else {
            holder.mImageViewGener.setImageResource(R.drawable.ic_female);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), InfoActivity.class);
                intent.putExtra("id", Integer.toString(holder.mItem.getId()));
                view.getContext().startActivity(intent);
            }
        });

    }

    public static Bitmap converterByteToBipmap(byte[] foto) {
        Bitmap bmp = null;
        Bitmap bitmapReduzido = null;
        byte[] x = foto;

        try {
            bmp = BitmapFactory.decodeByteArray(x, 0, x.length);

            bitmapReduzido = Bitmap.createScaledBitmap(bmp, 80, 80, true);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmapReduzido;
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final RelativeLayout mIdView;
        private final ImageView mImageViewAnimais, mImageViewGener;
        private final TextView mTextViewNome;
        private final TextView mTextViewRaca;
        private final TextView mTextViewIdade;
        public Animais mItem;

        public ViewHolder(View itemView) {
            super(itemView);

            mIdView = itemView.findViewById(R.id.cardAnimais);
            mImageViewAnimais = itemView.findViewById(R.id.img_cardAnimais);
            mImageViewGener = itemView.findViewById(R.id.gener_card);
            mTextViewNome = itemView.findViewById(R.id.nome_card);
            mTextViewRaca = itemView.findViewById(R.id.raca_card);
            mTextViewIdade = itemView.findViewById(R.id.idade_card);

        }
    }
}
