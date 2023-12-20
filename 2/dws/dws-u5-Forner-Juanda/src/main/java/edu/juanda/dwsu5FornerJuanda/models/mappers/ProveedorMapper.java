package edu.juanda.dwsu5FornerJuanda.models.mappers;

import edu.juanda.dwsu5FornerJuanda.models.dto.ProveedorDTO;
import edu.juanda.dwsu5FornerJuanda.repositories.entities.Proveedor;
import org.springframework.stereotype.Component;

@Component
public class ProveedorMapper {

    public ProveedorDTO toDTO(Proveedor proveedor) {
        ProveedorDTO proveedorDTO = new ProveedorDTO();
        proveedorDTO.setId(proveedor.getId());
        proveedorDTO.setNombre(proveedor.getNombre());
        proveedorDTO.setCif(proveedor.getCif());
        return proveedorDTO;
    }

    public Proveedor toEntity(ProveedorDTO proveedorDTO) {
        Proveedor proveedor = new Proveedor();
        proveedor.setId(proveedorDTO.getId());
        proveedor.setNombre(proveedorDTO.getNombre());
        proveedor.setCif(proveedorDTO.getCif());
        return proveedor;
    }
}
