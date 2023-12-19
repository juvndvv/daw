package edu.juanda.dwsu5t1fornerjuanda.exceptions;

public class ClienteNotFoundException extends DataNotFoundException {
    public ClienteNotFoundException(Long idCliente) {
        super("No se ha encontrado el cliente con id: " + idCliente);
    }
}
