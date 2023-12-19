package edu.juanda.dwsu4t3fornerjuanda.model.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Objects;

@Data
public class CuentaDTO implements java.io.Serializable {
    private Long id;
    private String banco;
    private String sucursal;
    private String numeroCuenta;
    private String dc;
    private double saldo;
    @ToString.Exclude private ClienteDTO propietario;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuentaDTO cuentaDTO = (CuentaDTO) o;
        return Double.compare(saldo, cuentaDTO.saldo) == 0 && Objects.equals(id, cuentaDTO.id) && Objects.equals(banco, cuentaDTO.banco) && Objects.equals(sucursal, cuentaDTO.sucursal) && Objects.equals(dc, cuentaDTO.dc) && Objects.equals(propietario, cuentaDTO.propietario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, banco, sucursal, dc, saldo, propietario);
    }
}
