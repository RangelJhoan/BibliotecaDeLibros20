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
import com.example.bibliotecadelibros20.entidades.Prestamo;
import com.example.bibliotecadelibros20.utilidades.Utilidades;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdaptadorPrestamosLL extends RecyclerView.Adapter<AdaptadorPrestamosLL.ViewHolderDatos> {
    ArrayList<Prestamo> listaPrestamos;
    ArrayList<Prestamo> listaOriginal;
    Context context;

    public AdaptadorPrestamosLL(Context context, ArrayList<Prestamo> listaPrestamos) {
        this.listaPrestamos = listaPrestamos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_librosprestados, null, false);
        return new ViewHolderDatos(view);
    }

    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        Glide.with(context)
                .load(listaPrestamos.get(position).getLibro().getImagen())
                .error(R.drawable.ic_error_imagen_24dp)
                .into(holder.ivPortada);
        holder.titulo.setText(listaPrestamos.get(position).getLibro().getTitulo());
        holder.autor.setText(listaPrestamos.get(position).getLibro().getAutor().getNombre());
        holder.fechaPrestamo.setText(Utilidades.dateDMY(listaPrestamos.get(position).getFecha_prestamo()));
    }

    @Override
    public int getItemCount() {
        return listaPrestamos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        ImageView ivPortada;
        TextView titulo, autor, fechaPrestamo;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            ivPortada = itemView.findViewById(R.id.ivPortada);
            titulo = itemView.findViewById(R.id.tvTituloLibro);
            autor = itemView.findViewById(R.id.tvAutorLibro);
            fechaPrestamo = itemView.findViewById(R.id.tvFechaPrestamo);

        }
    }
}
