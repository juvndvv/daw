package edu.juanda.dwsu4t4fornerjuanda.repository.dao;

import edu.juanda.dwsu4t4fornerjuanda.repository.entity.Cliente;
import edu.juanda.dwsu4t4fornerjuanda.repository.entity.Cuenta;

import java.util.List;

public interface CuentaRepository {
    List<Cuenta> findAll();
    Cuenta findById(Cuenta cuenta);
    List<Cuenta> findByIdCliente(Cliente cliente);
    void save(Cuenta cuenta);
    void delete(Cuenta cuenta);
}
