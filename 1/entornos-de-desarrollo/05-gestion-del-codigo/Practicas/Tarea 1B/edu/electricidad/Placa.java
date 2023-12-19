package edu.electricidad;

/**
 * Esta clase representa una placa solar con un ID y una potencia.
 * 
 * @author Juan Daniel Forner Garriga
 * @since 22/02/2023
 */
public class Placa {

    private int id;
    private int potencia;

    /**
     * Crea una nueva placa solar con el ID y la potencia especificados.
     * 
     * @param id       El ID de la placa solar.
     * @param potencia La potencia de la placa solar.
     */
    public Placa(int id, int potencia) {
        this.id = id;
        this.potencia = potencia;
    }

    /**
     * Establece el ID de la placa solar.
     * 
     * @param id El nuevo ID de la placa solar.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el ID de la placa solar.
     * 
     * @return El ID de la placa solar.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene la potencia de la placa solar.
     * 
     * @return La potencia de la placa solar.
     */
    public int getPotencia() {
        return potencia;
    }

    /**
     * Establece la potencia de la placa solar.
     * 
     * @param potencia La nueva potencia de la placa solar.
     */
    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    /**
     * Recarga la batería especificada con la potencia de la placa solar.
     * 
     * @param b La batería a recargar.
     * @return La cantidad de energía recargada en la batería.
     */
    public int recargarBateria(Bateria b) {
        return 0;
    }
}
