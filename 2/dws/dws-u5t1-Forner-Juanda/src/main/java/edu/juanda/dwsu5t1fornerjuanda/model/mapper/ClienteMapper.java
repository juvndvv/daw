package edu.juanda.dwsu5t1fornerjuanda.model.mapper;

import edu.juanda.dwsu5t1fornerjuanda.model.dto.ClienteDTO;
import edu.juanda.dwsu5t1fornerjuanda.repository.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@Lazy
public class ClienteMapper {
    @Autowired
    RecomendacionMapper recomendacionMapper;

    @Autowired
    CuentaMapper cuentaMapper;

    @Autowired
    DireccionMapper direccionMapper;

    // Convierte una entidad a un objeto DTO
    public ClienteDTO toDto(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getIdCliente());
        clienteDTO.setNif(cliente.getNif());
        clienteDTO.setNombre(cliente.getNombre());
        clienteDTO.setApellidos(cliente.getApellidos());
        clienteDTO.setClaveSeguridad(cliente.getClaveSeguridad());
        clienteDTO.setEmail(cliente.getEmail());

        // Mapea la recomendacion
        clienteDTO.setRecomendacionDto(
                recomendacionMapper.toDto(
                        cliente.getRecomendacion(),
                        clienteDTO
                )
        );

        // Mapea las cuentas
        /*
        clienteDTO.getCuentasDto()
                .addAll(
                        cliente.getCuentas()
                            .stream()
                            .map(cuenta -> cuentaMapper.toDto(cuenta, clienteDTO))
                            .toList()
                );

        // Mapea las direcciones
        clienteDTO.getDireccionesDTO()
                .addAll(
                        cliente.getListaDirecciones()
                                .stream()
                                .map(direccion -> direccionMapper.toDto(direccion, clienteDTO))
                                .toList()
                );
         */

        return clienteDTO;
    }

    // Convierte un objeto DTO a una entidad
    public Cliente toEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(clienteDTO.getId());
        cliente.setNif(clienteDTO.getNif());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellidos(clienteDTO.getApellidos());
        cliente.setClaveSeguridad(clienteDTO.getClaveSeguridad());
        cliente.setEmail(clienteDTO.getEmail());

        // Mapea la recomendacion
        cliente.setRecomendacion(
                recomendacionMapper.toEntity(clienteDTO.getRecomendacionDto(), cliente)
        );

        // Mapea las cuentas
        /*
        cliente.getCuentas()
                .addAll(
                        clienteDTO.getCuentasDto()
                                .stream()
                                .map(cuentaDTO -> cuentaMapper.toEntity(cuentaDTO, cliente))
                                .toList()
                );

        // Mapea las direcciones
        cliente.getListaDirecciones()
                .addAll(
                        clienteDTO.getDireccionesDTO()
                                .stream()
                                .map(direccionDTO -> direccionMapper.toEntity(direccionDTO, cliente))
                                .toList()
                );
         */

        return cliente;
    }
}
