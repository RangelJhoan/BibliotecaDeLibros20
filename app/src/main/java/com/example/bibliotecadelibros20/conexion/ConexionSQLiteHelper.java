package com.example.bibliotecadelibros20.conexion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.bibliotecadelibros20.entidades.Autor;
import com.example.bibliotecadelibros20.entidades.Libro;
import com.example.bibliotecadelibros20.entidades.Prestamo;
import com.example.bibliotecadelibros20.entidades.TipoUsuario;
import com.example.bibliotecadelibros20.entidades.Usuario;
import com.example.bibliotecadelibros20.utilidades.Sesion;
import com.example.bibliotecadelibros20.utilidades.UtilidadesDB;

import java.util.ArrayList;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    private static ConexionSQLiteHelper instancia = null;
    private Context mCxt;

    //PARAMETROS CONEXIÓN
    private static final String DATABASE_NAME = "bibliotecadelibros";
    private static final int DATABASE_VERSION = 1;

    //Crear conexión
    public static ConexionSQLiteHelper getInstance(Context ctx) {
        if (instancia == null) {
            instancia = new ConexionSQLiteHelper(ctx.getApplicationContext());
        }
        return instancia;
    }

    private ConexionSQLiteHelper(@Nullable Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        this.mCxt = ctx;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UtilidadesDB.CREAR_TABLA_TIPO_USUARIO);
        db.execSQL(UtilidadesDB.CREAR_TABLA_USUARIO);
        db.execSQL(UtilidadesDB.CREAR_TABLA_AUTOR);
        db.execSQL(UtilidadesDB.CREAR_TABLA_LIBRO);
        db.execSQL(UtilidadesDB.CREAR_TABLA_PRESTAMO);
        db.execSQL("insert into tipo_usuario (descripcion) values('usuario')");
        db.execSQL("insert into tipo_usuario (descripcion) values('admin')");
        db.execSQL("insert into usuario (nombre,correo_electronico, clave, id_tipo_usuario) values('Admin','admin','admin','2')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UtilidadesDB.TABLA_TIPO_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + UtilidadesDB.TABLA_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + UtilidadesDB.AUTOR_TABLA);
        db.execSQL("DROP TABLE IF EXISTS " + UtilidadesDB.LIBRO_TABLA);
        db.execSQL("DROP TABLE IF EXISTS " + UtilidadesDB.PRESTAMO_TABLA);
        db.execSQL("insert into tipo_usuario (descripcion) values('usuario')");
        db.execSQL("insert into tipo_usuario (descripcion) values('admin')");
        db.execSQL("insert into usuario (nombre,correo_electronico, clave, id_tipo_usuario) values('Admin','admin','admin','2')");
        onCreate(db);
    }

    //GESTIÓN DE USUARIOS
    public long insertarUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UtilidadesDB.CAMPO_NOMBRE, usuario.getNombre());
        values.put(UtilidadesDB.CAMPO_CORREO_ELECTRONICO, usuario.getCorreo_electronico());
        values.put(UtilidadesDB.CAMPO_TELEFONO, usuario.getTelefono());
        values.put(UtilidadesDB.CAMPO_DIRECCION, usuario.getDireccion());
        values.put(UtilidadesDB.CAMPO_CLAVE, usuario.getClave());
        values.put(UtilidadesDB.FORANEA_ID_TIPO_USUARIO, 1);

        long resultado = db.insert(UtilidadesDB.TABLA_USUARIO, UtilidadesDB.CAMPO_ID_USUARIO, values);

        db.close();
        return resultado;
    }

    public int iniciarSesion(String correo, String clave) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT u.id, u.nombre, t.id, t.descripcion " +
                "FROM " + UtilidadesDB.TABLA_USUARIO + " u " +
                "JOIN " + UtilidadesDB.TABLA_TIPO_USUARIO + " t ON  t.id = u.id_tipo_usuario " +
                "WHERE " + UtilidadesDB.CAMPO_CORREO_ELECTRONICO + " = ? and " + UtilidadesDB.CAMPO_CLAVE + " = ?", new String[]{correo, clave});
        if (cursor.moveToFirst()) {
            Sesion.usuario.setId(cursor.getInt(0));
            Sesion.usuario.setNombre(cursor.getString(1));
            int id_tipo_usuario = cursor.getInt(2);
            TipoUsuario tipoUsuario = new TipoUsuario();
            tipoUsuario.setId(id_tipo_usuario);
            tipoUsuario.setDescripcion(cursor.getString(3));
            Sesion.usuario.setTipoUsuario(tipoUsuario);

            return id_tipo_usuario;
        } else {
            //Tipo de usuario = 0, No se encontró usuario
            return 0;
        }
    }

    //USUARIO
    public ArrayList<Libro> consultarLibro() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Libro> listaLibros = new ArrayList<>();
        Libro libro = null;
        Autor autor = null;
        Cursor cursor = db.rawQuery("SELECT l.id, l.titulo,l.imagen, l.descripcion, l.cantidad, l.url, a.id, a.nombre " +
                "FROM " + UtilidadesDB.LIBRO_TABLA + " l " +
                "JOIN " + UtilidadesDB.AUTOR_TABLA + " a ON l.id_autor = a.id", null);
        while (cursor.moveToNext()) {
            libro = new Libro();
            autor = new Autor();
            libro.setId(cursor.getInt(0));
            libro.setTitulo(cursor.getString(1));
            libro.setImagen(cursor.getString(2));
            libro.setDescripcion(cursor.getString(3));
            libro.setCantidad(cursor.getInt(4));
            libro.setUrl(cursor.getString(5));
            autor.setId(cursor.getInt(6));
            autor.setNombre(cursor.getString(7));
            libro.setAutor(autor);
            listaLibros.add(libro);
        }
        cursor.close();
        db.close();
        return listaLibros;
    }

    public ArrayList<Prestamo> consultarLibrosPrestadosUsu(int id_usuario) {
        SQLiteDatabase db = this.getReadableDatabase();
        Libro libro = null;
        Prestamo prestamo = null;
        Autor autor = null;
        ArrayList<Prestamo> listaPrestamo = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT l.titulo, l.imagen, a.nombre, p.fecha_prestamo, l.descripcion, l.url, p.id, l.cantidad, l.id " +
                "FROM usuario u " +
                "JOIN prestamo p ON p.id_usuario = u.id " +
                "JOIN libro l ON l.id = p.id_libro " +
                "JOIN autor a ON a.id = l.id_autor " +
                "WHERE u.id = " + id_usuario, null);

        while (cursor.moveToNext()) {
            libro = new Libro();
            prestamo = new Prestamo();
            autor = new Autor();

            autor.setNombre(cursor.getString(2));
            prestamo.setFecha_prestamo(cursor.getString(3));
            prestamo.setId(cursor.getInt(6));
            libro.setTitulo(cursor.getString(0));
            libro.setImagen(cursor.getString(1));
            libro.setDescripcion(cursor.getString(4));
            libro.setUrl(cursor.getString(5));
            libro.setCantidad(cursor.getInt(7));
            libro.setId(cursor.getInt(8));
            libro.setAutor(autor);

            prestamo.setLibro(libro);
            listaPrestamo.add(prestamo);
        }
        cursor.close();
        db.close();
        return listaPrestamo;
    }

    public long prestarLibro(Libro libro, int id_usuario) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(UtilidadesDB.PRESTAMO_ID_LIBRO, libro.getId());
        values.put(UtilidadesDB.PRESTAMO_ID_USUARIO, id_usuario);

        long res = db.insert(UtilidadesDB.PRESTAMO_TABLA, UtilidadesDB.PRESTAMO_ID, values);
        db.close();
        return res;
    }

    //ADMINISTRADOR

    public long agregarLibro(Libro libro){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT id FROM autor WHERE nombre = ?", new String[]{String.valueOf(libro.getAutor().getNombre())});

        if (cursor.moveToFirst()) {
            ContentValues values = new ContentValues();
            values.put(UtilidadesDB.LIBRO_TITULO, libro.getTitulo());
            values.put(UtilidadesDB.LIBRO_DESCRIPCION, libro.getDescripcion());
            values.put(UtilidadesDB.LIBRO_URL, libro.getUrl());
            values.put(UtilidadesDB.LIBRO_CANTIDAD, libro.getCantidad());
            values.put(UtilidadesDB.LIBRO_IMAGEN, libro.getImagen());
            values.put(UtilidadesDB.LIBRO_FORANEA_AUTOR, cursor.getInt(0));

            long res = db.insert(UtilidadesDB.LIBRO_TABLA, UtilidadesDB.LIBRO_ID, values);
            cursor.close();
            db.close();
            return res;
        } else {
            ContentValues valuesAutor = new ContentValues();
            valuesAutor.put(UtilidadesDB.AUTOR_NOMBRE, libro.getAutor().getNombre());

            long resAutor = db.insert(UtilidadesDB.AUTOR_TABLA, UtilidadesDB.AUTOR_ID, valuesAutor);

            if (resAutor > 0) {
                ContentValues values = new ContentValues();
                values.put(UtilidadesDB.LIBRO_TITULO, libro.getTitulo());
                values.put(UtilidadesDB.LIBRO_DESCRIPCION, libro.getDescripcion());
                values.put(UtilidadesDB.LIBRO_URL, libro.getUrl());
                values.put(UtilidadesDB.LIBRO_CANTIDAD, libro.getCantidad());
                values.put(UtilidadesDB.LIBRO_IMAGEN, libro.getImagen());
                values.put(UtilidadesDB.LIBRO_FORANEA_AUTOR, resAutor);

                long res = db.insert(UtilidadesDB.LIBRO_TABLA, UtilidadesDB.LIBRO_ID, values);
                db.close();
                cursor.close();
                return res;
            } else {
                db.close();
                cursor.close();
                return resAutor;
            }
        }
    }

    public long actualizarLibro(Libro libro){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT id FROM autor WHERE nombre = ?", new String[]{String.valueOf(libro.getAutor().getNombre())});
        String[] where = {String.valueOf(libro.getId())};

        if (cursor.moveToFirst()) {
            ContentValues values = new ContentValues();
            values.put(UtilidadesDB.LIBRO_TITULO, libro.getTitulo());
            values.put(UtilidadesDB.LIBRO_DESCRIPCION, libro.getDescripcion());
            values.put(UtilidadesDB.LIBRO_URL, libro.getUrl());
            values.put(UtilidadesDB.LIBRO_CANTIDAD, libro.getCantidad());
            values.put(UtilidadesDB.LIBRO_IMAGEN, libro.getImagen());
            values.put(UtilidadesDB.LIBRO_FORANEA_AUTOR, cursor.getInt(0));

            long respuesta = db.update(UtilidadesDB.LIBRO_TABLA, values, UtilidadesDB.LIBRO_ID + " = ?", where);

            db.close();
            cursor.close();
            return respuesta;
        } else {
            ContentValues valuesAutor = new ContentValues();
            valuesAutor.put(UtilidadesDB.AUTOR_NOMBRE, libro.getAutor().getNombre());

            long resAutor = db.insert(UtilidadesDB.AUTOR_TABLA, UtilidadesDB.AUTOR_ID, valuesAutor);

            if (resAutor > 0) {
                ContentValues values = new ContentValues();
                values.put(UtilidadesDB.LIBRO_TITULO, libro.getTitulo());
                values.put(UtilidadesDB.LIBRO_DESCRIPCION, libro.getDescripcion());
                values.put(UtilidadesDB.LIBRO_URL, libro.getUrl());
                values.put(UtilidadesDB.LIBRO_CANTIDAD, libro.getCantidad());
                values.put(UtilidadesDB.LIBRO_IMAGEN, libro.getImagen());
                values.put(UtilidadesDB.LIBRO_FORANEA_AUTOR, resAutor);
                long respuesta = db.update(UtilidadesDB.LIBRO_TABLA, values, UtilidadesDB.LIBRO_ID + " = ?", where);
                db.close();
                cursor.close();
                return respuesta;
            } else {
                db.close();
                cursor.close();
                return resAutor;
            }
        }
    }

    public ArrayList<Libro> consultarLibros(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Libro> listaLibros = new ArrayList<>();
        Libro libro = null;
        Autor autor = null;
        Cursor cursor = db.rawQuery("SELECT l.id, l.titulo,l.imagen, l.descripcion, l.cantidad, l.url, a.id, a.nombre " +
                "FROM " + UtilidadesDB.LIBRO_TABLA + " l " +
                "JOIN " + UtilidadesDB.AUTOR_TABLA + " a ON l.id_autor = a.id", null);
        while (cursor.moveToNext()) {
            libro = new Libro();
            autor = new Autor();
            libro.setId(cursor.getInt(0));
            libro.setTitulo(cursor.getString(1));
            libro.setImagen(cursor.getString(2));
            libro.setDescripcion(cursor.getString(3));
            libro.setCantidad(cursor.getInt(4));
            libro.setUrl(cursor.getString(5));
            autor.setId(cursor.getInt(6));
            autor.setNombre(cursor.getString(7));
            libro.setAutor(autor);
            listaLibros.add(libro);
        }
        cursor.close();
        db.close();
        return listaLibros;
    }

    public ArrayList<Prestamo> consultarLibrosPrestados(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Prestamo> listaLibrosPrestados = new ArrayList<>();
        Libro libro = null;
        Prestamo prestamo = null;
        Autor autor = null;
        Usuario usuario = null;

        Cursor cursor = db.rawQuery("SELECT DISTINCT l.titulo, l.imagen, l.descripcion, a.nombre, u.nombre, u.telefono, u.correo_electronico, l.id " +
                "FROM libro l " +
                "JOIN autor a ON a.id = l.id_autor " +
                "JOIN prestamo p ON p.id_libro = l.id " +
                "JOIN usuario u ON u.id = p.id_usuario", null);
        while (cursor.moveToNext()) {
            libro = new Libro();
            prestamo = new Prestamo();
            autor = new Autor();
            usuario = new Usuario();

            libro.setTitulo(cursor.getString(0));
            libro.setImagen(cursor.getString(1));
            libro.setDescripcion(cursor.getString(2));
            autor.setNombre(cursor.getString(3));
            usuario.setNombre(cursor.getString(4));
            usuario.setTelefono(cursor.getString(5));
            usuario.setCorreo_electronico(cursor.getString(6));
            libro.setId(cursor.getInt(7));
            libro.setAutor(autor);

            prestamo.setLibro(libro);
            prestamo.setUsuario(usuario);

            listaLibrosPrestados.add(prestamo);

        }
        cursor.close();
        db.close();
        return listaLibrosPrestados;
    }

}
