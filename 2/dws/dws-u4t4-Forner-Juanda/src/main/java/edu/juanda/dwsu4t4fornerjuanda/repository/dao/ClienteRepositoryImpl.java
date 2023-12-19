package edu.juanda.dwsu4t4fornerjuanda.repository.dao;

import edu.juanda.dwsu4t4fornerjuanda.database.FakeDatabase;
import edu.juanda.dwsu4t4fornerjuanda.repository.entity.Cliente;
import edu.juanda.dwsu4t4fornerjuanda.repository.entity.Cuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {
    @Autowired
    FakeDatabase database;

    @Override
    public List<Cliente> findAll() {
        return database.getClientes();
    }

    @Override
    public Cliente findById(Cliente cliente) {
        return database.findClienteById(cliente);
    }

    @Override
    public Cliente findByIdCuenta(Cuenta cuenta) {
        return database.findClienteByIdCuenta(cuenta);
    }

    @Override
    public void save(Cliente cliente) {
        database.saveCliente(cliente);
    }

    @Override
    public void delete(Cliente cliente) {
        database.deleteCliente(cliente);
    }
}