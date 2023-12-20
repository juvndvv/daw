package edu.juanda.dwsu5FornerJuanda.repositories.dao;

import edu.juanda.dwsu5FornerJuanda.repositories.entities.Proveedor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Transactional
@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    // Busca los proveedores de un cliente teniendo en cuenta que se relacionan mediante la tabla pedidos
    @Query(value = "SELECT p FROM Proveedor p JOIN p.pedidos pe JOIN pe.cliente c WHERE c.id = :idCliente")
    Set<Proveedor> findByIdCliente(@Param("idCliente") Long idCliente);
}
