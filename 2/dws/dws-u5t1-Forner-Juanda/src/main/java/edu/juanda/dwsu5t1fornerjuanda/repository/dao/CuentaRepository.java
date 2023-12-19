package edu.juanda.dwsu5t1fornerjuanda.repository.dao;

import edu.juanda.dwsu5t1fornerjuanda.repository.entity.Cuenta;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    List<Cuenta> findCuentaByPropietarioIdCliente(Long idPropietario);
}
