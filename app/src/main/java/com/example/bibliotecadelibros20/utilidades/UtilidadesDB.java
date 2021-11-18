package com.example.bibliotecadelibros20.utilidades;

public class UtilidadesDB {

    //ATRIBUTOS DEL TIPO DE USUARIO
    public static final String TABLA_TIPO_USUARIO = "tipo_usuario";
    public static final String CAMPO_ID_TIPO_USUARIO = "id";
    public static final String CAMPO_DESCRIPCION = "descripcion";

    //ATRIBUTOS DEL USUARIO
    public static final String TABLA_USUARIO = "usuario";
    public static final String CAMPO_ID_USUARIO = "id";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_CORREO_ELECTRONICO = "correo_electronico";
    public static final String CAMPO_TELEFONO = "telefono";
    public static final String CAMPO_DIRECCION = "direccion";
    public static final String CAMPO_CLAVE = "clave";
    public static final String FORANEA_ID_TIPO_USUARIO = "id_tipo_usuario";

    //ATRIBUTOS DEL AUTOR
    public static final String AUTOR_TABLA = "autor";
    public static final String AUTOR_ID = "id";
    public static final String AUTOR_NOMBRE = "nombre";

    //ATRIBUTOS DEL LIBRO
    public static final String LIBRO_TABLA = "libro";
    public static final String LIBRO_ID = "id";
    public static final String LIBRO_TITULO = "titulo";
    public static final String LIBRO_DESCRIPCION = "descripcion";
    public static final String LIBRO_CONTENIDO_COMPLETO = "contenido_completo";
    public static final String LIBRO_CANTIDAD = "cantidad";
    public static final String LIBRO_IMAGEN = "imagen";
    public static final String LIBRO_FORANEA_AUTOR = "id_autor";

    //ATRIBUTOS DEL PRESTAMO
    public static final String PRESTAMO_TABLA = "prestamo";
    public static final String PRESTAMO_ID = "id";
    public static final String PRESTAMO_ID_USUARIO = "id_usuario";
    public static final String PRESTAMO_ID_LIBRO = "id_libro";
    public static final String PRESTAMO_FECHA_PRESTAMO = "fecha_prestamo";

    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE " + TABLA_USUARIO + " (" + CAMPO_ID_USUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + CAMPO_NOMBRE + " TEXT, "
            + CAMPO_CORREO_ELECTRONICO + " TEXT, " + CAMPO_TELEFONO + " TEXT, " + CAMPO_DIRECCION + " TEXT, " + CAMPO_CLAVE + " TEXT, " + FORANEA_ID_TIPO_USUARIO + " INTEGER)";

    public static final String CREAR_TABLA_TIPO_USUARIO = "CREATE TABLE " + TABLA_TIPO_USUARIO + " (" + CAMPO_ID_TIPO_USUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CAMPO_DESCRIPCION + " TEXT)";

    public static final String CREAR_TABLA_AUTOR = "CREATE TABLE " + AUTOR_TABLA + " (" + AUTOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + AUTOR_NOMBRE + " TEXT)";

    public static final String CREAR_TABLA_LIBRO = "CREATE TABLE " + LIBRO_TABLA + " (" + LIBRO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + LIBRO_TITULO + " TEXT, "
            + LIBRO_DESCRIPCION + " TEXT," + LIBRO_CONTENIDO_COMPLETO + " TEXT, " + LIBRO_CANTIDAD + " INTEGER, " + LIBRO_IMAGEN + " TEXT, " + LIBRO_FORANEA_AUTOR + " INTEGER)";

    public static final String CREAR_TABLA_PRESTAMO = "CREATE TABLE " + PRESTAMO_TABLA + " (" + PRESTAMO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + PRESTAMO_ID_USUARIO + " INTEGER, "
            + PRESTAMO_ID_LIBRO + " INTEGER, " + PRESTAMO_FECHA_PRESTAMO + " DEFAULT CURRENT_TIMESTAMP)";

}
