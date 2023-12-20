package edu.juanda.dwsu5FornerJuanda.models.mappers;

import edu.juanda.dwsu5FornerJuanda.models.dto.ClienteDTO;
import edu.juanda.dwsu5FornerJuanda.models.dto.DireccionDTO;
import edu.juanda.dwsu5FornerJuanda.repositories.entities.Cliente;
import edu.juanda.dwsu5FornerJuanda.repositories.entities.Direccion;
import org.springframework.stereotype.Component;

@Component
public class DireccionMapper {

    public DireccionDTO toDTO(Direccion direccion, ClienteDTO clienteDTO) {
        DireccionDTO direccionDTO = new DireccionDTO();
        direccionDTO.setId(direccion.getId());
        direccionDTO.setDescripcion(direccion.getDescripcion());
        direccionDTO.setPais(direccion.getPais());
        direccionDTO.setCp(direccion.getCp());
        direccionDTO.getClientesDTO().add(clienteDTO);

        return direccionDTO;
    }

    public Direccion toEntity(DireccionDTO direccionDTO, Cliente cliente) {
        Direccion direccion = new Direccion();
        direccion.setId(direccionDTO.getId());
        direccion.setDescripcion(direccionDTO.getDescripcion());
        direccion.setPais(direccionDTO.getPais());
        direccion.setCp(direccionDTO.getCp());
        direccion.getClientes().add(cliente);

        return direccion;
    }
}
