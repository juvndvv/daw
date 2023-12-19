package edu.electricidad;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una centralita eléctrica, la cual está compuesta de
 * placas solares y baterías.
 * 
 * @author Juan Daniel Forner Garriga
 * @since 22/02/2023
 */
public class Centralita {

    /**
     * Lista de placas solares que se encuentran conectadas a la centralita.
     */
    private List<Placa> placas = new ArrayList<Placa>();

    /**
     * Lista de baterías que se encuentran conectadas a la centralita.
     */
    private List<Bateria> baterias = new ArrayList<Bateria>();

    /**
     * Constructor de la clase Centralita.
     * 
     * @param placas   Lista de placas solares que se conectan a la centralita.
     * @param baterias Lista de baterías que se conectan a la centralita.
     */
    public Centralita(List<Placa> placas, List<Bateria> baterias) {
        this.placas = placas;
        this.baterias = baterias;
    }

    /**
     * Método que devuelve la lista de placas solares conectadas a la centralita.
     * 
     * @return Lista de placas solares.
     */
    public List<Placa> getPlacas() {
        return placas;
    }

    /**
     * Método que permite agregar una nueva placa solar a la centralita.
     * 
     * @param p Placa solar a agregar.
     * @return true si la placa fue agregada correctamente, false en caso contrario.
     */
    public boolean insertPlaca(Placa p) {
        return true;
    }

    /**
     * Método que permite eliminar una placa solar de la centralita.
     * 
     * @param p Placa solar a eliminar.
     * @return true si la placa fue eliminada correctamente, false en caso
     *         contrario.
     */
    public boolean deletePlaca(Placa p) {
        return true;
    }

    /**
     * Método que permite establecer la lista de placas solares conectadas a la
     * centralita.
     * 
     * @param placas Nueva lista de placas solares.
     */
    public void setPlacas(List<Placa> placas) {
        this.placas = placas;
    }

    /**
     * Método que devuelve la lista de baterías conectadas a la centralita.
     * 
     * @return Lista de baterías.
     */
    public List<Bateria> getBaterias() {
        return baterias;
    }

    /**
     * Método que permite agregar una nueva batería a la centralita.
     * 
     * @param b Batería a agregar.
     * @return true si la batería fue agregada correctamente, false en caso
     *         contrario.
     */
    public boolean insertBateria(Bateria b) {
        return true;
    }

    /**
     * Método que permite eliminar una batería de la centralita.
     * 
     * @param b Batería a eliminar.
     * @return true si la batería fue eliminada correctamente, false en caso
     *         contrario.
     */
    public boolean deleteBateria(Bateria b) {
        return true;
    }

    /**
     * Método que permite establecer la lista de baterías conectadas a la
     * centralita.
     * 
     * @param baterias Nueva lista de baterías.
     */
    public void setBaterias(List<Bateria> baterias) {
        this.baterias = baterias;
    }

    /**
     * Método que devuelve el estado de carga de una batería específica.
     * 
     * @param idBateria Identificador único de la batería.
     * @return Estado de carga de la batería.
     */
    public int getEstadoBateria(int idBateria) {
        return 0;
    }

    /**
     * Método que devuelve el estado de carga de la red eléctrica en la que está
     * conectada la centralita.
     * 
     * @return Estado de carga de la red eléctrica.
     */
    public int getEstadoRed() {
        return 0;
    }
}