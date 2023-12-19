package edu.juanda.dwsu3t1fornerjuanda.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class Alquiler {
    @Setter private String nombre;
    @Setter private int dias;
    @Setter private String formaPago;
    @Setter private String extras;
    private String edad;

    public void setEdad(String edad) {
        switch (edad) {
            case "menos7":
                this.edad = "Menos de 7 años";
                break;
            case "menos14":
                this.edad = "Entre 7 y 14 años";
                break;
            case "menos 18":
                this.edad = "Entre 14 y 18 años";
                break;
            case "mayor18":
                this.edad = "Mayor de 18 años";
                break;
            default:
                this.edad = "No se ha seleccionado edad";
                break;
        };
    }
}
