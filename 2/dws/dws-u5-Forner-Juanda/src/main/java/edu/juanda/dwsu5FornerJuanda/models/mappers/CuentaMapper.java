package edu.juanda.dwsu5FornerJuanda.models.mappers;

import edu.juanda.dwsu5FornerJuanda.models.dto.ClienteDTO;
import edu.juanda.dwsu5FornerJuanda.models.dto.CuentaDTO;
import edu.juanda.dwsu5FornerJuanda.repositories.entities.Cliente;
import edu.juanda.dwsu5FornerJuanda.repositories.entities.Cuenta;
import org.springframework.stereotype.Component;

@Component
public class CuentaMapper {

    public CuentaDTO toDTO(Cuenta cuenta, ClienteDTO clienteDTO) {
        CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setId(cuenta.getId());
        cuentaDTO.setBanco(cuenta.getBanco());
        cuentaDTO.setSucursal(cuenta.getSucursal());
        cuentaDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
        cuentaDTO.setDc(cuenta.getDc());
        cuentaDTO.setSaldo(cuenta.getSaldo());
        cuentaDTO.setClienteDTO(clienteDTO);
        return cuentaDTO;
    }

    public Cuenta toEntity(CuentaDTO cuentaDTO, Cliente cliente) {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(cuentaDTO.getId());
        cuenta.setBanco(cuentaDTO.getBanco());
        cuenta.setSucursal(cuentaDTO.getSucursal());
        cuenta.setNumeroCuenta(cuentaDTO.getNumeroCuenta());
        cuenta.setDc(cuentaDTO.getDc());
        cuenta.setSaldo(cuentaDTO.getSaldo());
        cuenta.setCliente(cliente);
        return cuenta;
    }
}
