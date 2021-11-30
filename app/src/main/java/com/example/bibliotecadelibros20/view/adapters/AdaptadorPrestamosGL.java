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
import com.example.bibliotecadelibros20.entidades.Prestamo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdaptadorPrestamosGL extends RecyclerView.Adapter<AdaptadorPrestamosGL.ViewHolderDatos> implements View.OnClickListener {
    ArrayList<Prestamo> listaPrestamos;
    ArrayList<Prestamo> listaOriginal;
    private View.OnClickListener listener;
    Context context;

    public AdaptadorPrestamosGL(Context context, ArrayList<Prestamo> listaPrestamos) {
        this.listaPrestamos = listaPrestamos;
        this.context = context;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaPrestamos);
    }

    public void filtrado(String busqueda) {
        int longitud = busqueda.length();
        if (longitud == 0) {
            listaPrestamos.clear();
            listaPrestamos.addAll(listaOriginal);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                List<Prestamo> collection = listaPrestamos.stream().filter(i -> i.getLibro().getTitulo().toLowerCase()
                        .contains(busqueda.toLowerCase())).collect(Collectors.toList());
                listaPrestamos.clear();
                listaPrestamos.addAll(collection);
            } else {
                for (Prestamo p : listaOriginal) {
                    if (p.getLibro().getTitulo().contains(busqueda.toLowerCase())) {
                        listaPrestamos.add(p);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdaptadorPrestamosGL.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_adminlibrosprestados, null, false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPrestamosGL.ViewHolderDatos holder, int position) {
        Glide.with(context)
                .load(listaPrestamos.get(position).getLibro().getImagen())
                .error(R.drawable.ic_error_imagen_24dp)
                .into(holder.ivPortada);
        holder.titulo.setText(listaPrestamos.get(position).getLibro().getTitulo());
        holder.autor.setText(listaPrestamos.get(position).getLibro().getAutor().getNombre());
    }

    @Override
    public int getItemCount() {
        return listaPrestamos.size();
    }


    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        ImageView ivPortada;
        TextView titulo, autor;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            ivPortada = itemView.findViewById(R.id.ivPortada);
            titulo = itemView.findViewById(R.id.tvTituloLibro);
            autor = itemView.findViewById(R.id.tvAutorLibro);

        }
    }


    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

}
