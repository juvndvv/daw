package edu.juanda.dwsu4t4fornerjuanda.repository.dao;

import edu.juanda.dwsu4t4fornerjuanda.repository.entity.Cuenta;
import edu.juanda.dwsu4t4fornerjuanda.repository.entity.Movimiento;

import java.util.List;

public interface MovimientoRepository {
    List<Movimiento> findAll();
    Movimiento findById(Movimiento movimiento);
    List<Movimiento> findByIdCuenta(Cuenta cuenta);
    void save(Movimiento movimiento);
    void delete(Movimiento movimiento);
}
