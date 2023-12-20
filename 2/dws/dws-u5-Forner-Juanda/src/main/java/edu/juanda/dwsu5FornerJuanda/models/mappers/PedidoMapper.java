package edu.juanda.dwsu5FornerJuanda.models.mappers;

import edu.juanda.dwsu5FornerJuanda.models.dto.PedidoDTO;
import edu.juanda.dwsu5FornerJuanda.repositories.entities.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {

    @Autowired
    ClienteMapper clienteMapper;

    @Autowired
    ProveedorMapper proveedorMapper;

    public PedidoDTO toDTO(Pedido pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(pedido.getId());
        pedidoDTO.setArticulo(pedido.getArticulo());
        pedidoDTO.setCantidad(pedido.getCantidad());
        pedidoDTO.setClienteDTO(clienteMapper.toDTO(pedido.getCliente()));
        pedidoDTO.setProveedorDTO(proveedorMapper.toDTO(pedido.getProveedor()));

        return pedidoDTO;
    }

    public Pedido toEntity(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setId(pedidoDTO.getId());
        pedido.setArticulo(pedidoDTO.getArticulo());
        pedido.setCantidad(pedidoDTO.getCantidad());
        pedido.setCliente(clienteMapper.toEntity(pedidoDTO.getClienteDTO()));
        pedido.setProveedor(proveedorMapper.toEntity(pedidoDTO.getProveedorDTO()));
        return pedido;
    }
}
