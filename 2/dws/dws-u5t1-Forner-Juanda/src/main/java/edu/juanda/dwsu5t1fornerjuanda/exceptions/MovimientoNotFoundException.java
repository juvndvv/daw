package edu.juanda.dwsu5t1fornerjuanda.exceptions;

public class MovimientoNotFoundException extends DataNotFoundException {
    public MovimientoNotFoundException(Long id) {
        super("No se ha encontrado el movimiento con id " + id);
    }
}
