package edu.electricidad;

/**
 * Esta clase representa a un usuario administrador
 * 
 * @author Juan Daniel Forner Garriga
 * @since 22/02/2023
 */
public class Administrador extends Usuario {

    private String adminId;

    /**
     * Constructor de la clase Administrador que inicializa sus atributos.
     * 
     * @param id        Identificador único del administrador.
     * @param firstName Nombre del administrador.
     * @param lastName  Apellido del administrador.
     * @param adminId   Identificador único del administrador.
     */
    public Administrador(int id, String firstName, String lastName, String adminId) {
        super(id, firstName, lastName);
        this.adminId = adminId;
    }

    /**
     * Método que devuelve el identificador único del administrador.
     * 
     * @return Identificador único del administrador.
     */
    public String getAdminId() {
        return adminId;
    }

    /**
     * Método que da de alta a un usuario.
     * 
     * @param u Usuario que se desea dar de alta.
     * @return true si se ha dado de alta al usuario, false en caso contrario.
     */
    public boolean darAlta(Usuario u) {
        return true;
    }

    /**
     * Método que da de baja a un usuario.
     * 
     * @param u Usuario que se desea dar de baja.
     * @return true si se ha dado de baja al usuario, false en caso contrario.
     */
    public boolean darBaja(Usuario u) {
        return true;
    }
}
