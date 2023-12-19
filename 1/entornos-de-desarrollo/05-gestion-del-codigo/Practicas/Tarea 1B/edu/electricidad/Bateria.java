package edu.electricidad;

/**
 * Esta clase representa una bateria con un ID, un nivel de carga y un estado
 * 
 * @author Juan Daniel Forner Garriga
 * @since 22/02/2023
 */
public class Bateria {

    private int id;
    private int nivelCarga;
    private int estado;

    /**
     * Constructor de la clase Bateria que inicializa sus atributos.
     * 
     * @param id         Identificador único de la batería.
     * @param nivelCarga Nivel de carga actual de la batería.
     * @param estado     Estado actual de la batería. Debe ser 1, 2 o 3 (1:
     *                   Cargando, 2: Descargando, 3: En espera).
     * @throws IllegalArgumentException si el estado proporcionado no es válido.
     */
    public Bateria(int id, int nivelCarga, int estado) throws IllegalArgumentException {
        if (estado != 1 && estado != 2 && estado != 3)
            throw new IllegalArgumentException("Estado inválido");

        this.id = id;
        this.nivelCarga = nivelCarga;
        this.estado = estado;
    }

    /**
     * Método que devuelve el identificador único de la batería.
     * 
     * @return Identificador único de la batería.
     */
    public int getId() {
        return id;
    }

    /**
     * Método que devuelve el nivel de carga actual de la batería.
     * 
     * @return Nivel de carga actual de la batería.
     */
    public int getNivelCarga() {
        return nivelCarga;
    }

    /**
     * Método que devuelve el estado actual de la batería.
     * 
     * @return Estado actual de la batería.
     */
    public int getEstado() {
        return estado;
    }

    /**
     * Método que establece el estado actual de la batería.
     * 
     * @param estado Nuevo estado de la batería. Debe ser 1, 2 o 3 (1: Cargando, 2:
     *               Descargando, 3: En espera).
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }
}
