package edu.electricidad;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase representa un usuario que tiene placas solares y baterías para
 * almacenar energía.
 * 
 * @author Juan Daniel Forner Garriga
 * @since 22/02/2023
 */
public class UsuarioPlaca extends Usuario {

    private List<Placa> placas = new ArrayList<Placa>();
    private List<Bateria> baterias = new ArrayList<Bateria>();

    /**
     * Crea un nuevo usuario con placas solares y baterías.
     * 
     * @param id        El ID del usuario.
     * @param firstName El primer nombre del usuario.
     * @param lastName  El apellido del usuario.
     */
    public UsuarioPlaca(int id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }

    /**
     * Obtiene la lista de placas solares del usuario.
     * 
     * @return La lista de placas solares.
     */
    public List<Placa> getPlacas() {
        return placas;
    }

    /**
     * Agrega una nueva placa solar a la lista del usuario.
     * 
     * @param p La placa solar a agregar.
     * @return true si se agregó correctamente, false en caso contrario.
     */
    public boolean insertPlaca(Placa p) {
        return true;
    }

    /**
     * Elimina una placa solar de la lista del usuario.
     * 
     * @param p La placa solar a eliminar.
     * @return true si se eliminó correctamente, false en caso contrario.
     */
    public boolean deletePlaca(Placa p) {
        return true;
    }

    /**
     * Establece la lista de placas solares del usuario.
     * 
     * @param placas La nueva lista de placas solares.
     */
    public void setPlacas(List<Placa> placas) {
        this.placas = placas;
    }

    /**
     * Obtiene la lista de baterías del usuario.
     * 
     * @return La lista de baterías.
     */
    public List<Bateria> getBaterias() {
        return baterias;
    }

    /**
     * Agrega una nueva batería a la lista del usuario.
     * 
     * @param b La batería a agregar.
     * @return true si se agregó correctamente, false en caso contrario.
     */
    public boolean insertBateria(Bateria b) {
        return true;
    }

    /**
     * Elimina una batería de la lista del usuario.
     * 
     * @param b La batería a eliminar.
     * @return true si se eliminó correctamente, false en caso contrario.
     */
    public boolean deleteBateria(Bateria b) {
        return true;
    }

    /**
     * Recarga las baterías del usuario.
     * 
     * @return true si se recargaron correctamente, false en caso contrario.
     */
    public boolean recargarBatereria() {
        return true;
    }

    /**
     * Dona energía a otros usuarios que la necesiten.
     * 
     * @return true si se donó correctamente, false en caso contrario.
     */
    public boolean donarEnergia() {
        return true;
    }

    /**
     * Envía energia a otros usuarios que la necesiten
     * 
     * @return true si se envió correctamente, false en caso contrario
     */
    public boolean enviarEnergia() {
        return true;
    }

}
