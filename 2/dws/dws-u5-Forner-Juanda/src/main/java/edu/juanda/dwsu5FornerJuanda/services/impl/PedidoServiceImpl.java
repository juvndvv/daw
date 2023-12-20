package edu.juanda.dwsu5FornerJuanda.services.impl;

import edu.juanda.dwsu5FornerJuanda.models.dto.PedidoDTO;
import edu.juanda.dwsu5FornerJuanda.models.mappers.PedidoMapper;
import edu.juanda.dwsu5FornerJuanda.repositories.dao.PedidoRepository;
import edu.juanda.dwsu5FornerJuanda.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoMapper pedidoMapper;

    @Override
    public List<PedidoDTO> findByCliente(Long idCliente) {
        return pedidoRepository.findPedidosByClienteId(idCliente)
                .stream()
                .map(pedido -> pedidoMapper.toDTO(pedido))
                .toList();
    }

    @Override
    public List<PedidoDTO> findByProveedor(Long idProveedor) {
        return pedidoRepository.findPedidosByProveedorId(idProveedor)
                .stream()
                .map(pedido -> pedidoMapper.toDTO(pedido))
                .toList();
    }

    @Override
    public void save(PedidoDTO pedidoDTO) {
        pedidoRepository.save(pedidoMapper.toEntity(pedidoDTO));
    }

    @Override
    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }
}
