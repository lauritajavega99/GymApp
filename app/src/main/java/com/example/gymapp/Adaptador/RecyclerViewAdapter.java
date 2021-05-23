package com.example.gymapp.Adaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymapp.Interfaz.ItemClickListener;
import com.example.gymapp.Model.Ejercicio;
import com.example.gymapp.R;
import com.example.gymapp.VistaEjercicio;

import java.util.List;

class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView imagen;
    public TextView texto;

    private ItemClickListener itemClickListener;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        imagen = (ImageView)itemView.findViewById(R.id.imgEjercicio);
        texto = (TextView) itemView.findViewById(R.id.lblEjercicio);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition());

    }
}

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private List<Ejercicio> ejercicioList;
    private Context context;

    public RecyclerViewAdapter(List<Ejercicio> ejercicioList, Context context) {
        this.ejercicioList = ejercicioList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_ejercicio, parent, false);

        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.imagen.setImageResource(ejercicioList.get(position).getImagen_id());
        holder.texto.setText(ejercicioList.get(position).getNombre());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Llamo a una nueva activity
                Intent intent =new Intent(context, VistaEjercicio.class);
                intent.putExtra("imagen_id", ejercicioList.get(position).getImagen_id());
                intent.putExtra("nombre", ejercicioList.get(position).getNombre());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ejercicioList.size();
    }
}
