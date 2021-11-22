package com.example.bibliotecadelibros20.entidades;

import java.io.Serializable;

public class Autor implements Serializable {

    private int id;
    private String nombre;

    public Autor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
