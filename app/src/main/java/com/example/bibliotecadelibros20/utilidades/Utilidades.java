package com.example.bibliotecadelibros20.utilidades;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilidades {
    public static String dateDMY(String fecha){
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = formatter.parse(fecha);
            DateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
            String dateFormatted = formatter2.format(date);
            return dateFormatted;
        } catch (ParseException e) {
            e.printStackTrace();
            return "Error";
        }
    }

}
