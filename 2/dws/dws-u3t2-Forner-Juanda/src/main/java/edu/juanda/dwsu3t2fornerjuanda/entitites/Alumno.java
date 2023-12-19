package edu.juanda.dwsu3t2fornerjuanda.entitites;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Alumno {
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String dni;

    public Alumno(String dni) {
        this.dni = dni;
    }
}
