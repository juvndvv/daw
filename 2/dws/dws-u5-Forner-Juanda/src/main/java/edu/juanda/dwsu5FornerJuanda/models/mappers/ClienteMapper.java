package edu.juanda.dwsu5FornerJuanda.models.mappers;

import edu.juanda.dwsu5FornerJuanda.models.dto.ClienteDTO;
import edu.juanda.dwsu5FornerJuanda.models.dto.RecomendacionDTO;
import edu.juanda.dwsu5FornerJuanda.repositories.entities.Cliente;
import edu.juanda.dwsu5FornerJuanda.repositories.entities.Recomendacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    @Autowired
    private RecomendacionMapper recomendacionMapper;

    public ClienteDTO toDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNif(cliente.getNif());
        clienteDTO.setNombre(cliente.getNombre());
        clienteDTO.setApellidos(cliente.getApellidos());
        clienteDTO.setClaveSeguridad(cliente.getClaveSeguridad());
        clienteDTO.setEmail(cliente.getEmail());

        RecomendacionDTO recomendacionDTO = recomendacionMapper.toDTO(cliente.getRecomendacion(),
                clienteDTO);
        clienteDTO.setRecomendacionDTO(recomendacionDTO);

        return clienteDTO;
    }

    public Cliente toEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setNif(clienteDTO.getNif());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellidos(clienteDTO.getApellidos());
        cliente.setClaveSeguridad(clienteDTO.getClaveSeguridad());
        cliente.setEmail(clienteDTO.getEmail());

        Recomendacion recomendacion = recomendacionMapper.toEntity(clienteDTO.getRecomendacionDTO(),
                cliente);
        cliente.setRecomendacion(recomendacion);

        return cliente;
    }
}
