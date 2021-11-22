package com.example.bibliotecadelibros20.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bibliotecadelibros20.R;
import com.example.bibliotecadelibros20.entidades.Libro;

import java.util.ArrayList;

public class AdaptadorLibrosDisponibles extends RecyclerView.Adapter<AdaptadorLibrosDisponibles.ViewHolderDatos> {
    ArrayList<Libro> listaLibros;
    Context context;

    public AdaptadorLibrosDisponibles(Context context,ArrayList<Libro> listaLibros) {
        this.context = context;
        this.listaLibros = listaLibros;
    }

    @NonNull
    @Override
    public AdaptadorLibrosDisponibles.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_librosdisponibles, null, false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorLibrosDisponibles.ViewHolderDatos holder, int position) {
        holder.titulo.setText(listaLibros.get(position).getTitulo());
        Glide.with(context)
                .load(listaLibros.get(position).getImagen())
                .into(holder.ivPortada);
        holder.autor.setText(listaLibros.get(position).getAutor().getNombre());
    }

    @Override
    public int getItemCount() {
        return listaLibros.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView titulo, autor;
        ImageView ivPortada;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.tvTituloLibro);
            ivPortada = itemView.findViewById(R.id.ivPerfil);
            autor = itemView.findViewById(R.id.tvAutorLibro);


        }
    }
}
