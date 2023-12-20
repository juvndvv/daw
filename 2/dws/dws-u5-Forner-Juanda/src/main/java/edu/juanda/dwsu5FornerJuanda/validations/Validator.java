package edu.juanda.dwsu5FornerJuanda.validations;

import edu.juanda.dwsu5FornerJuanda.models.dto.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Validator {
    public ArrayList<String> validate(ClienteDTO clienteDTO) {
        ArrayList<String> errores = new ArrayList<String>();

        // Valida que no esten vacios los campos
        isVoid(clienteDTO.getNombre(), errores, "nombre");
        isVoid(clienteDTO.getApellidos(), errores, "apellido");
        isVoid(clienteDTO.getNif(), errores, "nif");
        isVoid(clienteDTO.getEmail(), errores, "email");
        isVoid(clienteDTO.getClaveSeguridad(), errores, "clave de seguridad");
        isVoid(clienteDTO.getRecomendacionDTO().getObservaciones(), errores, "recomendacion");

        hasMoreThan(clienteDTO.getNombre(), 100, errores, "nombre");
        hasMoreThan(clienteDTO.getApellidos(), 100, errores, "apellido");
        hasMoreThan(clienteDTO.getNif(), 9, errores, "nif");
        hasMoreThan(clienteDTO.getEmail(), 100, errores, "email");
        hasMoreThan(clienteDTO.getClaveSeguridad(), 10, errores, "clave de seguridad");
        hasMoreThan(clienteDTO.getRecomendacionDTO().getObservaciones(), 100, errores, "recomendacion");

        return errores;
    }

    public ArrayList<String> validate(CuentaDTO cuentaDTO) {
        ArrayList<String> errores = new ArrayList<String>();

        // Valida que no esten vacios los campos
        isVoid(cuentaDTO.getNumeroCuenta(), errores, "numero");
        isVoid(cuentaDTO.getBanco(), errores, "banco");
        isVoid(cuentaDTO.getSucursal(), errores, "sucursal");
        isVoid(cuentaDTO.getDc(), errores, "dc");

        // Valida longitud maxima
        hasMoreThan(cuentaDTO.getNumeroCuenta(), 20, errores, "numero");
        hasMoreThan(cuentaDTO.getBanco(), 100, errores, "banco");
        hasMoreThan(cuentaDTO.getSucursal(), 100, errores, "sucursal");
        hasMoreThan(cuentaDTO.getDc(), 2, errores, "dc");

        return errores;
    }

    public ArrayList<String> validate(DireccionDTO direccionDTO) {
        ArrayList<String> errores = new ArrayList<String>();

        // Valida que no esten vacios los campos
        isVoid(direccionDTO.getDescripcion(), errores, "descripcion");
        isVoid(direccionDTO.getPais(), errores, "pais");
        isVoid(direccionDTO.getCp(), errores, "cp");

        // Valida longitud maxima
        hasMoreThan(direccionDTO.getDescripcion(), 100, errores, "descripcion");
        hasMoreThan(direccionDTO.getPais(), 100, errores, "pais");
        hasMoreThan(direccionDTO.getCp(), 10, errores, "cp");

        return errores;
    }

    public ArrayList<String> validate(MovimientoDTO movimientoDTO) {
        // TODO deberia comprobar que el saldo lo permite
        ArrayList<String> errores = new ArrayList<String>();

        // Valida que no esten vacios los campos
        isVoid(movimientoDTO.getTipo(), errores, "tipo");
        isVoid(movimientoDTO.getDescripcion(), errores, "descripcion");

        // Valida longitud maxima
        hasMoreThan(movimientoDTO.getTipo(), 100, errores, "tipo");
        hasMoreThan(movimientoDTO.getDescripcion(), 100, errores, "descripcion");

        // Valida que la cantidad sea mayor que 0.1
        lessThan(movimientoDTO.getImporte(), 0.1, errores, "cantidad");

        return errores;
    }

    public ArrayList<String> validate(PedidoDTO pedidoDTO) {
        // Campos articulo y cantidad
        ArrayList<String> errores = new ArrayList<String>();

        // Valida que no esten vacios los campos
        isVoid(pedidoDTO.getArticulo(), errores, "articulo");

        // Valida longitud maxima
        hasMoreThan(pedidoDTO.getArticulo(), 100, errores, "articulo");

        // Valida que la cantidad sea mayor que 0
        lessThan(pedidoDTO.getCantidad(), 1, errores, "cantidad");

        return errores;
    }

    public ArrayList<String> validate(ProveedorDTO proveedorDTO) {
        ArrayList<String> errores = new ArrayList<String>();

        // Valida que no esten vacios los campos
        isVoid(proveedorDTO.getNombre(), errores, "nombre");
        isVoid(proveedorDTO.getCif(), errores, "cif");

        // Valida longitud maxima
        hasMoreThan(proveedorDTO.getNombre(), 100, errores, "nombre");
        hasMoreThan(proveedorDTO.getCif(), 9, errores, "cif");

        return errores;
    }

    public static void lessThan(int numero, int cantidad, ArrayList<String> errores, String campo) {
        if (numero < cantidad) {
            errores.add("El campo " + campo + " no puede ser menor que " + cantidad);
        }
    }

    public static void lessThan(double numero, double cantidad, ArrayList<String> errores, String campo) {
        if (numero < cantidad) {
            errores.add("El campo " + campo + " no puede ser menor que " + cantidad);
        }
    }

    public static void isVoid(String cadena, ArrayList<String> errores, String campo) {
        if (cadena.isBlank()) {
            errores.add("El campo " + campo + " no puede estar vacío");
        }
    }

    public static void hasMoreThan(String cadena, int cantidad, ArrayList<String> errores, String campo) {
        if (cadena.length() > cantidad) {
            errores.add("El campo " + campo + " no puede tener más de " + cantidad + " caracteres");
        }
    }
}
