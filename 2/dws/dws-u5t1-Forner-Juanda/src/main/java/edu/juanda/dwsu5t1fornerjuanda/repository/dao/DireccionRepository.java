package edu.juanda.dwsu5t1fornerjuanda.repository.dao;

import edu.juanda.dwsu5t1fornerjuanda.repository.entity.Direccion;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface DireccionRepository extends JpaRepository<Direccion, Long> {

    @Query(value = "SELECT A.* FROM direcciones A, clientes_direcciones B " +
    "WHERE A.id_direccion=B.id_direccion AND B.id_cliente = :idcliente", nativeQuery = true)
    public List<Direccion> findAllByCliente(@Param("idcliente") Long idCliente);
}
