package edu.juanda.dwsu5FornerJuanda.repositories.dao;

import edu.juanda.dwsu5FornerJuanda.repositories.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Set<Pedido> findPedidosByClienteId(Long idCliente);
    Set<Pedido> findPedidosByProveedorId(Long idProveedor);
}
