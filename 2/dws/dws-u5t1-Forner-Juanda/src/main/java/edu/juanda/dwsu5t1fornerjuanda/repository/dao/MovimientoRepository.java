package edu.juanda.dwsu5t1fornerjuanda.repository.dao;

import edu.juanda.dwsu5t1fornerjuanda.repository.entity.Movimiento;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findMovimientosByCuentaOrigenIdCuentaOrCuentaDestinoIdCuenta(Long idCuentaOrigen, Long idCuentaDestino);
}
