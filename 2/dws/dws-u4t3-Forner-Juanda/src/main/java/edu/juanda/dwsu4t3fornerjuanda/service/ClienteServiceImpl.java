package edu.juanda.dwsu4t3fornerjuanda.service;

import java.util.ArrayList;
import java.util.List;

import edu.juanda.dwsu4t3fornerjuanda.model.mapper.ClienteMapper;
import edu.juanda.dwsu4t3fornerjuanda.repository.dao.ClienteRepository;
import edu.juanda.dwsu4t3fornerjuanda.repository.entity.Cliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.juanda.dwsu4t3fornerjuanda.model.dto.ClienteDTO;

@Service
public class ClienteServiceImpl implements ClienteService {

    private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);

    private final ClienteRepository clienteRepository;

    private final ClienteMapper clienteMapper;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public List<ClienteDTO> findAll() {
        log.info("ClienteServiceImpl - findAll: Lista de todos los cliente");

        List<ClienteDTO> listaClientesDTO = new ArrayList<>();
        List<Cliente> listaClientes = clienteRepository.findAll();
        for (Cliente cliente : listaClientes) {
            ClienteDTO clienteDTO = clienteMapper.convertToDTO(cliente);
            listaClientesDTO.add(clienteDTO);
        }

        return listaClientesDTO;
    }

    @Override
    public ClienteDTO findById(ClienteDTO clienteDTO) {

        log.info("ClienteServiceImpl - findById: Buscar cliente por id: " +
                clienteDTO.getId());

        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());

        cliente = clienteRepository.findById(cliente);
        if (cliente != null) {
            clienteDTO = clienteMapper.convertToDTO(cliente);
            return clienteDTO;
        } else {
            return null;
        }
    }

    @Override
    public List<String> save(ClienteDTO clienteDTO) {
        log.info("ClienteServiceImpl - save: Validando el cliente");

        List<String> errores = validarClienteDTO(clienteDTO);

        if (errores.isEmpty()) {
            log.info("ClienteServiceImpl - save: Salvamos el cliente: " + clienteDTO);

            clienteRepository.save(clienteMapper.convertToEntity(clienteDTO));

        } else {
            log.warn("ClienteServiceImpl - save: El cliente no es valido");
        }

        return errores;
    }

    @Override
    public void delete(ClienteDTO clienteDTO) {
        log.info("ClienteServiceImpl - delete: Borramos el cliente: " +
                clienteDTO.getId());

        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        clienteRepository.delete(cliente);
    }

    private static List<String> validarClienteDTO(ClienteDTO clienteDTO) {
        List<String> errores = new ArrayList<>();

        validarNIF(clienteDTO.getNif(), errores);
        validarNombre(clienteDTO.getNombre(), errores);
        validarApellidos(clienteDTO.getApellidos(), errores);
        validarClaveSeguridad(clienteDTO.getClaveSeguridad(), errores);
        validarEmail(clienteDTO.getEmail(), errores);

        return errores;
    }

    /*
     * NIF
     * @NotBlank(message = "El NIF es obligatorio")
     * @Pattern(regexp = "^[0-9]{8}[A-Za-z]{1}$", message = "El NIF debe tener 8 numeros y una letra")
     * */
    private static void validarNIF(String NIF, List<String> errores) {
        if (NIF.isEmpty())
            errores.add("El NIF es obligatorio");

        else if (!NIF.matches("^[0-9]{8}[A-Za-z]$"))
            errores.add("El NIF debe tener 8 numeros y una letra");
    }

    /*
     * Nombre
     * @NotBlank(message = "El nombre es obligatorio")
     * */
    private static void validarNombre(String name, List<String> errores) {
        if (name.isEmpty())
            errores.add("El nombre es obligatorio");
    }

    /*
     * Apellidos
     * @NotBlank(message = "Los apellidos son obligatorios")
     * */
    private static void validarApellidos(String apellidos, List<String> errores) {
        if (apellidos.isEmpty())
            errores.add("El apellido es obligatorio");
    }

    /*
     * Clave de seguridad
     * @NotBlank(message = "La contraseña es obligatoria")
     * @Min(value = 8, message = "La contraseña debe tener al menos 8 caracteres")
     * */
    private static void validarClaveSeguridad(String claveSeguridad, List<String> errores) {
        if (claveSeguridad.isEmpty())
            errores.add("La contraseña es obligatoria");

        else if (claveSeguridad.length() < 8)
            errores.add("La longitud de la contraseña debe ser mayor a 8");
    }

    /*
     * Email
     * @NotBlank(message = "El email es obligatorio")
     * @Email(message = "El email no es valido")
     * */
    private static void validarEmail(String email, List<String> errores) {
        if (email.isEmpty())
            errores.add("El email es obligatorio");

        else if (!email.matches("^\\S+@\\S+\\.\\S+$"))
            errores.add("El email no es válido");
    }
}