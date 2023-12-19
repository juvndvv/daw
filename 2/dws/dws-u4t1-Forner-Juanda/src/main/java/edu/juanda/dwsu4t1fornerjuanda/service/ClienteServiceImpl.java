package edu.juanda.dwsu4t1fornerjuanda.service;

import java.util.ArrayList;
import java.util.List;

import edu.juanda.dwsu4t1fornerjuanda.repository.dao.ClienteRepository;
import edu.juanda.dwsu4t1fornerjuanda.repository.entity.Cliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.juanda.dwsu4t1fornerjuanda.model.dto.ClienteDTO;

@Service
public class ClienteServiceImpl implements ClienteService {

    private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<ClienteDTO> findAll() {
        log.info("ClienteServiceImpl - findAll: Lista de todos los cliente");

        List<ClienteDTO> listaClientesDTO = new ArrayList<ClienteDTO>();
        List<Cliente> listaClientes = clienteRepository.findAll();
        for (int i = 0; i < listaClientes.size(); i++) {
            Cliente cliente = listaClientes.get(i);
            ClienteDTO clienteDTO = ClienteDTO.convertToDTO(cliente);
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
            clienteDTO = ClienteDTO.convertToDTO(cliente);
            return clienteDTO;
        } else {
            return null;
        }
    }

    @Override
    public void save(ClienteDTO clienteDTO) {
        log.info("ClienteServiceImpl - save: Salvamos el cliente: " +
                clienteDTO.toString());

        Cliente cliente = ClienteDTO.convertToEntity(clienteDTO);
        clienteRepository.save(cliente);

    }

    @Override
    public void delete(ClienteDTO clienteDTO) {
        log.info("ClienteServiceImpl - delete: Borramos el cliente: " +
                clienteDTO.getId());

        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        clienteRepository.delete(cliente);
    }
}