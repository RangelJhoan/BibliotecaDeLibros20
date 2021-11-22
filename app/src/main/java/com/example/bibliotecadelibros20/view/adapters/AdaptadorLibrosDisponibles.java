package com.example.bibliotecadelibros20.view.adapters;

import android.content.Context;
import android.os.Build;
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
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class AdaptadorLibrosDisponibles extends RecyclerView.Adapter<AdaptadorLibrosDisponibles.ViewHolderDatos> implements View.OnClickListener {
    ArrayList<Libro> listaLibros;
    ArrayList<Libro> listaOriginal;
    private View.OnClickListener listener;
    Context context;

    public AdaptadorLibrosDisponibles(Context context, ArrayList<Libro> listaLibros) {
        this.context = context;
        this.listaLibros = listaLibros;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaLibros);
    }

    //Método para la búsqueda de libros
    public void filtrado(String busqueda) {
        int longitud = busqueda.length();
        if (longitud == 0) {
            listaLibros.clear();
            listaLibros.addAll(listaOriginal);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                List<Libro> collection = listaLibros.stream().filter(i -> i.getTitulo().toLowerCase()
                        .contains(busqueda.toLowerCase())).collect(Collectors.toList());
                listaLibros.clear();
                listaLibros.addAll(collection);
            }else{
                for(Libro l: listaOriginal){
                    if(l.getTitulo().contains(busqueda.toLowerCase())){
                        listaLibros.add(l);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdaptadorLibrosDisponibles.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_librosdisponibles, null, false);
        view.setOnClickListener(this);
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
    //Métodos OnClick
    @Override
    public void onClick(View v) {
        if(listener != null){
            listener.onClick(v);
        }
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }
}
