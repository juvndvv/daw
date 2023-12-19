package edu.juanda.dwsu4t4fornerjuanda.repository.dao;

import edu.juanda.dwsu4t4fornerjuanda.database.FakeDatabase;
import edu.juanda.dwsu4t4fornerjuanda.repository.entity.Cuenta;
import edu.juanda.dwsu4t4fornerjuanda.repository.entity.Movimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovimientoRepositoryImpl implements MovimientoRepository {
    @Autowired
    FakeDatabase database;

    @Override
    public List<Movimiento> findAll() {
        return database.getMovimientos();
    }

    @Override
    public Movimiento findById(Movimiento movimiento) {
        return database.findMovimientoById(movimiento);
    }

    @Override
    public List<Movimiento> findByIdCuenta(Cuenta cuenta) {
        return database.findMovimientosByIdCuenta(cuenta);
    }

    @Override
    public void save(Movimiento movimiento) {
        database.saveMovimiento(movimiento);
    }

    @Override
    public void delete(Movimiento movimiento) {
        database.deleteMovimiento(movimiento);
    }
}
