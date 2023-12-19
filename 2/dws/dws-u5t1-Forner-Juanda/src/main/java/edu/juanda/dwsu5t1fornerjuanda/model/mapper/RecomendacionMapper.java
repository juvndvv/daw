package edu.juanda.dwsu5t1fornerjuanda.model.mapper;

import edu.juanda.dwsu5t1fornerjuanda.model.dto.ClienteDTO;
import edu.juanda.dwsu5t1fornerjuanda.model.dto.RecomendacionDTO;
import edu.juanda.dwsu5t1fornerjuanda.repository.entity.Cliente;
import edu.juanda.dwsu5t1fornerjuanda.repository.entity.Recomendacion;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class RecomendacionMapper {

    public RecomendacionDTO toDto(Recomendacion recomendacion, ClienteDTO clienteDTO) {
        RecomendacionDTO recomendacionDTO = new RecomendacionDTO();
        recomendacionDTO.setId(recomendacion.getIdRecomendacion());
        recomendacionDTO.setObservaciones(recomendacion.getObservaciones());
        recomendacionDTO.setClienteDTO(clienteDTO);
        return recomendacionDTO;
    }

    public Recomendacion toEntity(RecomendacionDTO recomendacionDTO, Cliente cliente) {
        Recomendacion recomendacion = new Recomendacion();
        recomendacion.setIdRecomendacion(recomendacionDTO.getId());
        recomendacion.setObservaciones(recomendacionDTO.getObservaciones());
        recomendacion.setCliente(cliente);
        return recomendacion;
    }
}
