package edu.electricidad;

import java.util.List;

/**
 * Representa una red eléctrica compuesta por una lista de centralitas y un
 * estado.
 * 
 * @author Juan Daniel Forner Garriga
 * @since 22/02/2023
 */
public class RedElectrica {

    /** Lista de centralitas que conforman la red eléctrica. */
    public List<Centralita> centralitas;

    /** Estado de la red eléctrica. */
    public int estado;

    /**
     * Constructor de la clase RedElectrica.
     * 
     * @param centralitas la lista de centralitas que conforman la red eléctrica
     * @param estado      el estado de la red eléctrica
     */
    public RedElectrica(List<Centralita> centralitas, int estado) {
        this.centralitas = centralitas;
        this.estado = estado;
    }

    /**
     * Obtiene la lista de centralitas que conforman la red eléctrica.
     * 
     * @return la lista de centralitas que conforman la red eléctrica
     */
    public List<Centralita> getCentralitas() {
        return centralitas;
    }

    /**
     * Inserta una nueva centralita en la red eléctrica.
     * 
     * @param c la centralita a insertar
     * @return true si la inserción ha sido exitosa, false en caso contrario
     */
    public boolean insertCentralita(Centralita c) {
        return true;
    }

    /**
     * Elimina una centralita de la red eléctrica.
     * 
     * @param c la centralita a eliminar
     * @return true si la eliminación ha sido exitosa, false en caso contrario
     */
    public boolean deleteCentralita(Centralita c) {
        return true;
    }
}
