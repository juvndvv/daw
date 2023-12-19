package edu.juanda.dwsu4t3fornerjuanda.repository.dao;

import edu.juanda.dwsu4t3fornerjuanda.repository.entity.Cliente;
import edu.juanda.dwsu4t3fornerjuanda.repository.entity.Cuenta;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CuentaRepositoryImpl implements CuentaRepository {
    private final List<Cliente> clientes;

    private final List<Cuenta> cuentas;

    public CuentaRepositoryImpl() {
        clientes = ClienteRepositoryImpl.datos;

        cuentas = new ArrayList<>();

        // Añade las referencias de las cuentas a la lista cuentas
        clientes.forEach(cliente -> cuentas.addAll(cliente.getCuentas()));
    }

    @Override
    public Cuenta findById(Cuenta cuenta) {
        int index = cuentas.indexOf(cuenta);

        // Si no existe la cuenta devuelve null
        if (index == -1)
            return null;

        // Devuelve la cuenta
        return cuentas.get(index);
    }

    @Override
    public List<Cuenta> findByIdCliente(Cliente cliente) {
        int index = clientes.indexOf(cliente);

        // Si no existe el cliente se devuelve null
        if (index == -1)
            return null;

        // Devuelve la lista del cliente
        return clientes.get(index).getCuentas();
    }

    @Override
    public void save(Cuenta cuenta) {
        // Añade la cuenta al propietario
        int index = cuentas.indexOf(cuenta);

        // Si la cuenta no existe se agrega
        if (index == -1) {
            // Asigna el id
            cuenta.setId(ClienteRepositoryImpl.CURR_ID_CUENTA++);

            // Agrega la cuenta al propietario
            int ownerIndex = clientes.indexOf(cuenta.getPropietario());
            clientes.get(ownerIndex).getCuentas().add(cuenta);

            // Agrega la cuenta a la lista de cuentas
            cuentas.add(cuenta);

        } else {
            // Se actualizan los datos de la cuenta
            Cuenta old = cuentas.get(index);

            old.setId(cuenta.getId());
            old.setBanco(cuenta.getBanco());
            old.setSucursal(cuenta.getSucursal());
            old.setDc(cuenta.getDc());
            old.setSaldo(cuenta.getSaldo());
            old.setPropietario(cuenta.getPropietario());
        }
    }

    @Override
    public void delete(Cuenta cuenta) {
        // Se borra de la lista de cuentas
        cuentas.remove(cuenta);

        // Se borra la referencia del cliente
        for (Cliente cliente: clientes) {
            int index = cliente.getCuentas().indexOf(cuenta);
            if (index != -1){
                cliente.getCuentas().remove(cuenta);
                return;
            }
        }
    }
}
