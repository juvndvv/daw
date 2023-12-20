package edu.juanda.dwsu5FornerJuanda.repositories.dao;

import edu.juanda.dwsu5FornerJuanda.repositories.entities.Cliente;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query(value = "SELECT clientes.* FROM clientes JOIN cuentas ON clientes.id = cuentas.id_cliente WHERE cuentas.ID = ?1", nativeQuery = true)
    Cliente findClienteByCuentaId(Long idCuenta);
}
