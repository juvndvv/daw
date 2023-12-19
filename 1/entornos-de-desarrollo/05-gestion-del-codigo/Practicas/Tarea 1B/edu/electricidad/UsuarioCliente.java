package edu.electricidad;

/**
 * Esta clase representa un usuario cliente que puede demandar energía.
 * 
 * @author Juan Daniel Forner Garriga
 * @since 22/02/2023
 */
public class UsuarioCliente extends Usuario {

    private int idCliente;

    /**
     * Crea un nuevo usuario cliente con un ID de cliente único.
     * 
     * @param id        El ID del usuario.
     * @param firstName El primer nombre del usuario.
     * @param lastName  El apellido del usuario.
     * @param idCliente El ID de cliente único del usuario.
     */
    public UsuarioCliente(int id, String firstName, String lastName, int idCliente) {
        super(idCliente, firstName, lastName);
        this.idCliente = idCliente;
    }

    /**
     * Obtiene el ID de cliente único del usuario.
     * 
     * @return El ID de cliente único del usuario.
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Demanda energía al sistema.
     * 
     * @return true si la demanda se realizó correctamente, false en caso contrario.
     */
    public boolean demandarEnergia() {
        return true;
    }
}
