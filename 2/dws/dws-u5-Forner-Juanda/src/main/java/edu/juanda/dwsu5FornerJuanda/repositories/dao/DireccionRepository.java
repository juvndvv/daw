package edu.juanda.dwsu5FornerJuanda.repositories.dao;

import edu.juanda.dwsu5FornerJuanda.repositories.entities.Direccion;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Transactional
@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long> {
    @Query(value = "SELECT A.* FROM direcciones A, clientes_direcciones B " +
            "WHERE A.id=B.id_direccion AND B.id_cliente = :idcliente", nativeQuery = true)
    public Set<Direccion> findAllByCliente(@Param("idcliente") Long idCliente);
}
