package edu.juanda.dwsu5FornerJuanda.repositories.dao;

import edu.juanda.dwsu5FornerJuanda.repositories.entities.Cuenta;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Transactional
@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    Set<Cuenta> findCuentasByClienteId(Long idCliente);
}
