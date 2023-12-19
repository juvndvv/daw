package edu.juanda.dwsu5t1fornerjuanda.model.mapper;

import edu.juanda.dwsu5t1fornerjuanda.model.dto.ClienteDTO;
import edu.juanda.dwsu5t1fornerjuanda.model.dto.DireccionDTO;
import edu.juanda.dwsu5t1fornerjuanda.repository.entity.Cliente;
import edu.juanda.dwsu5t1fornerjuanda.repository.entity.Direccion;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class DireccionMapper {
    public DireccionDTO toDto(Direccion direccion, ClienteDTO clienteDTO) {
        DireccionDTO direccionDTO = new DireccionDTO();
        direccionDTO.setIdDireccion(direccion.getIdDireccion());
        direccionDTO.setDescripcion(direccion.getDescripcion());
        direccionDTO.setPais(direccion.getPais());
        direccionDTO.setCp(direccion.getCp());
        direccionDTO.getListaClientesDTO().add(clienteDTO);

        return direccionDTO;
    }

    public Direccion toEntity(DireccionDTO direccionDTO, Cliente cliente) {
        Direccion direccion = new Direccion();
        direccion.setIdDireccion(direccionDTO.getIdDireccion());
        direccion.setDescripcion(direccionDTO.getDescripcion());
        direccion.setPais(direccionDTO.getPais());
        direccion.setCp(direccionDTO.getCp());
        direccion.getListaClientes().add(cliente);

        return direccion;
    }
}
