package edu.juanda.dwsu5FornerJuanda.services.impl;

import edu.juanda.dwsu5FornerJuanda.models.dto.ProveedorDTO;
import edu.juanda.dwsu5FornerJuanda.models.mappers.ProveedorMapper;
import edu.juanda.dwsu5FornerJuanda.repositories.dao.ProveedorRepository;
import edu.juanda.dwsu5FornerJuanda.repositories.entities.Proveedor;
import edu.juanda.dwsu5FornerJuanda.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorServiceImpl implements ProveedorService {
    @Autowired
    ProveedorRepository proveedorRepository;

    @Autowired
    ProveedorMapper proveedorMapper;

    @Override
    public List<ProveedorDTO> findAll() {
        return proveedorRepository.findAll()
                .stream()
                .map(proveedor -> proveedorMapper.toDTO(proveedor))
                .toList();
    }

    @Override
    public ProveedorDTO findById(Long id) {
        return proveedorMapper.toDTO(proveedorRepository.findById(id).orElse(new Proveedor()));
    }

    @Override
    public List<ProveedorDTO> findByIdCliente(Long idCliente) {
        return proveedorRepository.findByIdCliente(idCliente)
                .stream()
                .map(proveedor -> proveedorMapper.toDTO(proveedor))
                .toList();
    }

    @Override
    public void save(ProveedorDTO proveedorDTO) {
        proveedorRepository.save(
                proveedorMapper.toEntity(proveedorDTO)
        );
    }

    @Override
    public void delete(Long id) {
        proveedorRepository.deleteById(id);
    }
}
