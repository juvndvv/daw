package edu.juanda.dwsu4t4fornerjuanda.repository.dao;

import edu.juanda.dwsu4t4fornerjuanda.database.FakeDatabase;
import edu.juanda.dwsu4t4fornerjuanda.repository.entity.Cliente;
import edu.juanda.dwsu4t4fornerjuanda.repository.entity.Cuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CuentaRepositoryImpl implements CuentaRepository {
    @Autowired
    FakeDatabase database;

    @Override
    public List<Cuenta> findAll() {
        return database.getCuentas();
    }

    @Override
    public Cuenta findById(Cuenta cuenta) {
        return database.findCuentaById(cuenta);
    }

    @Override
    public List<Cuenta> findByIdCliente(Cliente cliente) {
        return database.findCuentasByIdCliente(cliente);
    }

    @Override
    public void save(Cuenta cuenta) {
        database.saveCuenta(cuenta);
    }

    @Override
    public void delete(Cuenta cuenta) {
        database.deleteCuenta(cuenta);
    }
}
