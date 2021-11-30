package com.example.bibliotecadelibros20.entidades;

import java.io.Serializable;

public class Libro implements Serializable, Cloneable {

    private int id;
    private String titulo;
    private String descripcion;
    private String url;
    private int cantidad;
    private String imagen;
    private Autor autor;

    public Libro() {
    }

    public Libro(int id, String titulo, String descripcion, String url, int cantidad, String imagen, Autor autor) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.url = url;
        this.cantidad = cantidad;
        this.imagen = imagen;
        this.autor = autor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public Libro clone() throws CloneNotSupportedException {

        Libro libroNuevo = new Libro(this.id, this.titulo, this.descripcion, this.url, this.cantidad, this.imagen, this.autor);
        return libroNuevo;
    }

}
