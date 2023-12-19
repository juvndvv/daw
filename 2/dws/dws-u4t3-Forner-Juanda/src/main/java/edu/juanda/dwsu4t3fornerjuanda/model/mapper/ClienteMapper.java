package edu.juanda.dwsu4t3fornerjuanda.model.mapper;

import edu.juanda.dwsu4t3fornerjuanda.model.dto.ClienteDTO;
import edu.juanda.dwsu4t3fornerjuanda.model.dto.RecomendacionDTO;
import edu.juanda.dwsu4t3fornerjuanda.repository.entity.Cliente;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class ClienteMapper {
    //Convierte una entidad a un objeto DTO
    public ClienteDTO convertToDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNif(cliente.getNif());
        clienteDTO.setNombre(cliente.getNombre());
        clienteDTO.setApellidos(cliente.getApellidos());
        clienteDTO.setClaveSeguridad(cliente.getClaveSeguridad());
        clienteDTO.setEmail(cliente.getEmail());
        clienteDTO.setRecomendacionDTO(RecomendacionDTO.convertToDTO(cliente.getRecomendacion(), clienteDTO));

        return clienteDTO;
    }
    // Convierte un objeto DTO a una entidad
    public Cliente convertToEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setNif(clienteDTO.getNif());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellidos(clienteDTO.getApellidos());
        cliente.setClaveSeguridad(clienteDTO.getClaveSeguridad());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setRecomendacion(RecomendacionDTO.convertToEntity(clienteDTO.getRecomendacionDTO(), cliente));

        return cliente;
    }
}
