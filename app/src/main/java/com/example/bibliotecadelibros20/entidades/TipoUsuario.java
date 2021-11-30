package com.example.bibliotecadelibros20.entidades;

import java.io.Serializable;

public class TipoUsuario implements Serializable {
    private int id;
    private String descripcion;

    public TipoUsuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
