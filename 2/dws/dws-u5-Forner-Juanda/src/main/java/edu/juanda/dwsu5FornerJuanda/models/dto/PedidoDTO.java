package edu.juanda.dwsu5FornerJuanda.models.dto;

import lombok.Data;

import java.io.Serial;

@Data
public class PedidoDTO implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String articulo;
    private int cantidad;
    private ClienteDTO clienteDTO;
    private ProveedorDTO proveedorDTO;
}
