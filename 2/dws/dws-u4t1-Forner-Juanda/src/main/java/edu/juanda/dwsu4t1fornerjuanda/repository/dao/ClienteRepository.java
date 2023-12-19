package edu.juanda.dwsu4t1fornerjuanda.repository.dao;

import java.util.List;
import edu.juanda.dwsu4t1fornerjuanda.repository.entity.Cliente;
public interface ClienteRepository {
    List<Cliente> findAll();
    Cliente findById(Cliente cliente);
    void save(Cliente cliente);
    void delete(Cliente cliente);
}