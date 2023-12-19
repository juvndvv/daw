package edu.juanda.dwsu5t1fornerjuanda.exceptions;

public class CuentaNotFoundException extends DataNotFoundException {
    public CuentaNotFoundException(Long idCuenta) {
        super("No se ha encontrado la cuenta con id: " + idCuenta);
    }
}
