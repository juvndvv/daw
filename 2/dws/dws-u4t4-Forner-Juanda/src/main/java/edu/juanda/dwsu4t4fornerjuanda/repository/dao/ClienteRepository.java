package edu.juanda.dwsu4t4fornerjuanda.repository.dao;

import java.util.List;
import edu.juanda.dwsu4t4fornerjuanda.repository.entity.Cliente;
import edu.juanda.dwsu4t4fornerjuanda.repository.entity.Cuenta;

public interface ClienteRepository {
    List<Cliente> findAll();
    Cliente findById(Cliente cliente);
    Cliente findByIdCuenta(Cuenta cuenta);
    void save(Cliente cliente);
    void delete(Cliente cliente);
}