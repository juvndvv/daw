package edu.electricidad;

/**
 * Esta clase representa un usuario genérico con un ID, nombre y apellido.
 * 
 * @author Juan Daniel Forner Garriga
 * @since 22/02/2023
 */
public class Usuario {

    private int id;
    private String firstName;
    private String lastName;

    /**
     * Crea un nuevo usuario con el ID, primer nombre y apellido especificados.
     * 
     * @param id        El ID del usuario.
     * @param firstName El primer nombre del usuario.
     * @param lastName  El apellido del usuario.
     */
    public Usuario(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Obtiene el ID del usuario.
     * 
     * @return El ID del usuario.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el primer nombre del usuario.
     * 
     * @return El primer nombre del usuario.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Establece el primer nombre del usuario.
     * 
     * @param firstName El nuevo primer nombre del usuario.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Obtiene el apellido del usuario.
     * 
     * @return El apellido del usuario.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Establece el apellido del usuario.
     * 
     * @param lastName El nuevo apellido del usuario.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
