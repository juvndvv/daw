//Autor: Juan Daniel Forner

/*
    Pregunta 1: Olores y malas practicas

        - Olor1: Se ha detectado un monstruo en el metodo recalculaBonificacion() y la clase miClase en si
         
        - Olor2: Abuso de la orientacion a objetos en la combinacion de if larga del metodo renovar
    
        - Olor3: Superfluo. Codigo duplicado que se podia resumir en el metodo recalculaSegunTramoCoste()
*/

/*
    Pregunta2: Refactorizacion

        - Refactorización 1: se han extraido metodos auxiliares del metodo recalculaBonificacion(), todos los que empiezan
                    recalculaSegun...()
        
        - Refactorización 2: se ha creado un enhanced switch que facilita la lectura del codigo
        
        - Refactorización 3: se han renombrado las variables para mayor legibilidad del codigo
*/

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 ** La siguiente clase define las polizas de una compañia de seguros, se guarda la información del vehiculo asegurado, del cliente, los partes de accidentes, datos de poliza:
 * Vehiculo: Marca(String), modelo(String), km(Double), matricula(String), fecha_matriculacion(LocalDate)
 * Cliente: Dni(String), nombre(String), apellidos(String), fecha_carnet(LocalDate), tipo_carnet(String), antiguedad(Integer)
 * Parte: fecha(LocalDate), culpable(Boolean), reparacion(Double)
 * Datos de poliza: tipo(String), fecha_renovacion (LocalDate), prima(Double), bonificacion(Double)
 * Los metodos de la clase son:
 *   - Constructor: recibe todos los parametros y los asigna. Inicia la información de los partes como listas vacias
 *   - metodo1: renueva una poliza incrementando. Incrementa la fecha_renovacion entre 1 y 5 años.
 *   - metodo2: calcula el coste de la poliza en base a la prima y la bonificacion
 *   - metodo3: recalcula la bonificacion de la poliza en base a la antiguedad del cliente y los partes de acciente
 *   - metodo4: añade un nuevo parte de accidente
 **/
public class Poliza {
    private Vehiculo vehiculo;
    private Cliente cliente;

    private List<LocalDate> fechasPartes;
    private List<Boolean> culpabilidadPartes;
    private List<Double> costesReparaciones;

    private String tipo;
    private LocalDate fechaRenovacion;
    private Double prima;
    private Double bonificacion;

    /**
     * Constructor de clase: recibbe los parámetros de la poliza
     **/
    public Poliza(Vehiculo vehiculo,
                  Cliente cliente,
                  String tipo, LocalDate fechaRenovacion, Double prima, Double bonificacion)
    {
        this.vehiculo = vehiculo;
        this.cliente = cliente;

        this.fechasPartes = new ArrayList<LocalDate>();
        this.culpabilidadPartes = new ArrayList<Boolean>();
        this.costesReparaciones = new ArrayList<Double>();

        this.tipo = tipo;
        this.fechaRenovacion = fechaRenovacion;
        this.prima = prima;
        this.bonificacion = bonificacion;
    }

    /**
     * Renovar: este metodo renueva una poliza de seguro durante (1-5) años.
     **/
    public void renovar(String tiempoStr)
    {
        switch (tiempoStr) {
            case "1 year" -> this.fechaRenovacion = this.fechaRenovacion.plusYears(1);
            case "2 year" -> this.fechaRenovacion = this.fechaRenovacion.plusYears(2);
            case "3 year" -> this.fechaRenovacion = this.fechaRenovacion.plusYears(3);
            case "4 year" -> this.fechaRenovacion = this.fechaRenovacion.plusYears(4);
            case "5 year" -> this.fechaRenovacion = this.fechaRenovacion.plusYears(5);
        }
    }

    /**
     * costeRenovacion: este metodo devuelve el coste de renovar la poliza
     * El coste de renovar una poliza es: prima * bonificacion
     **/
    public Double getCosteRenovacion()
    {
        return (this.prima * this.bonificacion);
    }



    /**
     * recalculaBonificacion: Recalcula el valor de la bonificacion
     *   El método de calculo es el siguiente:
     *       - Por cada año de antiguedad: -1%
     *       - Por cada año desde el último parte: -1%
     *       - Por cada parte en que NO es culpable: + 1%
     *       - Por cada parte en que SI es culpable: + 10%
     *       - Según los costes de reparacion, sea o no culpable:
     *                   - Reparacion > 1000: + 10%
     *                   - Reparacion > 2000: + 20%
     *                   - Reparacion > 3000: + 50%
     *                   - Reparacion > 5000: + 100%
     **/
    public void recalculaBonificacion()
    {
        Double bonificacionNueva = this.bonificacion;
        bonificacionNueva = recalculaSegunAniosAntiguedad(bonificacionNueva);
        bonificacionNueva = recalculaSegunAniosDesdeUltimoParte(bonificacionNueva);
        bonificacionNueva = recalculaSegunPartesNoCulpable(bonificacionNueva);
        bonificacionNueva = recalculaSegunPartesSiCulpable(bonificacionNueva);
        bonificacionNueva = recalculaSegunTramoCoste(bonificacionNueva, 1000, 2000, 10);
        bonificacionNueva = recalculaSegunTramoCoste(bonificacionNueva, 2000, 3000, 20);
        bonificacionNueva = recalculaSegunTramoCoste(bonificacionNueva, 3000, 5000, 50);
        bonificacionNueva = recalculaSegunTramoCoste(bonificacionNueva, 5000, Integer.MAX_VALUE, 100);
        this.bonificacion = bonificacionNueva;
    }

    /**
     * addParte: Este método añade un nuevo parte de accidente
     **/
    public void addParte(LocalDate fechaParte, Boolean esCulpable, Double costesReparacion)
    {
        this.fechasPartes.add(fechaParte);
        this.culpabilidadPartes.add(esCulpable);
        this.costesReparaciones.add(costesReparacion);
    }

    private Double recalculaSegunAniosAntiguedad(Double bonificacion) {
        return bonificacion - (cliente.getAntiguedad() /100);
    }

    private Double recalculaSegunAniosDesdeUltimoParte(Double bonificacionNueva) {
        LocalDate fechaActual = LocalDate.now();
        LocalDate ultimoParte = this.fechasPartes.get(this.fechasPartes.size()-1);
        long aniosDesdeUltimoParte = ChronoUnit.Years.between(ultimoParte, fechaActual);
        return bonificacionNueva - (aniosDesdeUltimoParte/100);
    }

    private Double recalculaSegunPartesNoCulpable(Double bonificacionNueva) {
        for(Boolean culpabilidadParte: this.culpabilidadPartes)
        {
            if(!culpabilidadParte)
            {
                bonificacionNueva = bonificacionNueva + 1/100;
            }
        }
        return bonificacionNueva;
    }

    private Double recalculaSegunPartesSiCulpable(Double bonificacionNueva) {
        for(Boolean culpabilidadParte: this.culpabilidadPartes)
        {
            if(culpabilidadParte)
            {
                bonificacionNueva = bonificacionNueva + 10/100;
            }
        }

        return bonificacionNueva;
    }

    private Double recalculaSegunTramoCoste(Double bonificacionNueva, int inicio, int fin, int porcentaje) {
        for(Double coste: this.costesReparaciones)
        {
            if(coste > inicio && coste < fin)
            {
                bonificacionNueva = bonificacionNueva + porcentaje/100;
            }
        }

        return bonificacionNueva;
    }
}

class Vehiculo {
    private String marca;
    private String modelo;
    private Double km;
    private String matricula;
    private LocalDate fechaMatriculacion;

    public Vehiculo(String marca, String modelo, Double km, String matricula, LocalDate fechaMatriculacion) {
        this.marca = marca;
        this.modelo = modelo;
        this.km = km;
        this.matricula = matricula;
        this.fechaMatriculacion = fechaMatriculacion;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public Double getKm() {
        return km;
    }

    public String getMatricula() {
        return matricula;
    }

    public LocalDate getFechaMatriculacion() {
        return fechaMatriculacion;
    }
}

class Cliente {
    private String dni;
    private String nombre;
    private String apellidos;
    private LocalDate fechaCarnet;
    private String tipoCarnet;
    private Integer antiguedad;

    public Cliente(String dni, String nombre, String apellidos, LocalDate fechaCarnet, String tipoCarnet, Integer antiguedad) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaCarnet = fechaCarnet;
        this.tipoCarnet = tipoCarnet;
        this.antiguedad = antiguedad;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public LocalDate getFechaCarnet() {
        return fechaCarnet;
    }

    public String getTipoCarnet() {
        return tipoCarnet;
    }

    public Integer getAntiguedad() {
        return antiguedad;
    }
}