package com.jotade.Database;

import java.util.HashMap;

public class ErrorCodes {
    public static String getMessage(int code) {
        return ErrorCodes.codes().get(code);
    }

    private static HashMap<Integer, String> codes() {
        return new HashMap<>() {
            {
                // own codes
                put(800, "edicion ya existente en articulos");
                put(801, "edicion ya existente en revistas");
                put(802, "edicion ya existente en libros");

                put(810, "no existe en la base de datos");
                put(811, "edicion no existe en la base de datos");

                put(820, "la  persona no esta registrada como autor");
                put(821, "la  persona no esta registrada como cliente");
                put(822, "la  persona no esta registrada como socio");

                // mariaDB codes
                put(1062, "clave principal duplicada");
                put(1042, "clave foranea violada");
            }
        };
    }
}
