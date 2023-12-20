package edu.juanda.dwsu5FornerJuanda.models.mappers;

import edu.juanda.dwsu5FornerJuanda.models.dto.ClienteDTO;
import edu.juanda.dwsu5FornerJuanda.models.dto.RecomendacionDTO;
import edu.juanda.dwsu5FornerJuanda.repositories.entities.Cliente;
import edu.juanda.dwsu5FornerJuanda.repositories.entities.Recomendacion;
import org.springframework.stereotype.Component;

@Component
public class RecomendacionMapper {

    public RecomendacionDTO toDTO(Recomendacion recomendacion, ClienteDTO clienteDTO) {
        RecomendacionDTO recomendacionDTO = new RecomendacionDTO();
        recomendacionDTO.setId(recomendacion.getId());
        recomendacionDTO.setObservaciones(recomendacion.getObservaciones());
        recomendacionDTO.setClienteDTO(clienteDTO);
        return recomendacionDTO;
    }

    public Recomendacion toEntity(RecomendacionDTO recomendacionDTO, Cliente cliente) {
        Recomendacion recomendacion = new Recomendacion();
        recomendacion.setId(recomendacionDTO.getId());
        recomendacion.setObservaciones(recomendacionDTO.getObservaciones());
        recomendacion.setCliente(cliente);
        return recomendacion;
    }
}
