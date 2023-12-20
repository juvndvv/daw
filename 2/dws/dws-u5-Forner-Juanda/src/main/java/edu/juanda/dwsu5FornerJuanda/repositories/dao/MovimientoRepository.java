package edu.juanda.dwsu5FornerJuanda.repositories.dao;

import edu.juanda.dwsu5FornerJuanda.repositories.entities.Movimiento;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Transactional
@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    Set<Movimiento> findMovimientosByCuentaOrigenIdOrCuentaDestinoId(Long idCuentaOrigen, Long idCuentaDestino);
}
