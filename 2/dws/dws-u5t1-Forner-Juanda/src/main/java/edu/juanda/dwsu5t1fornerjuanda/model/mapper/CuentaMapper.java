package edu.juanda.dwsu5t1fornerjuanda.model.mapper;

import edu.juanda.dwsu5t1fornerjuanda.model.dto.ClienteDTO;
import edu.juanda.dwsu5t1fornerjuanda.model.dto.CuentaDTO;
import edu.juanda.dwsu5t1fornerjuanda.repository.entity.Cliente;
import edu.juanda.dwsu5t1fornerjuanda.repository.entity.Cuenta;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class CuentaMapper {

    public CuentaDTO toDto(Cuenta cuenta, ClienteDTO clienteDTO) {
        CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setId(cuenta.getIdCuenta());
        cuentaDTO.setBanco(cuenta.getBanco());
        cuentaDTO.setSucursal(cuenta.getSucursal());
        cuentaDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
        cuentaDTO.setDc(cuenta.getDc());
        cuentaDTO.setSaldo(cuenta.getSaldo());
        cuentaDTO.setPropietarioDto(clienteDTO);
        return cuentaDTO;
    }

    public Cuenta toEntity(CuentaDTO cuentaDTO, Cliente cliente) {
        Cuenta cuenta = new Cuenta();
        cuenta.setIdCuenta(cuentaDTO.getId());
        cuenta.setBanco(cuentaDTO.getBanco());
        cuenta.setSucursal(cuentaDTO.getSucursal());
        cuenta.setNumeroCuenta(cuentaDTO.getNumeroCuenta());
        cuenta.setDc(cuentaDTO.getDc());
        cuenta.setSaldo(cuentaDTO.getSaldo());
        cuenta.setPropietario(cliente);
        return cuenta;
    }
}
