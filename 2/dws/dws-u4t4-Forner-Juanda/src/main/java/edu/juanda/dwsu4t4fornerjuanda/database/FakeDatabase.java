package edu.juanda.dwsu4t4fornerjuanda.database;

import edu.juanda.dwsu4t4fornerjuanda.repository.entity.Cliente;
import edu.juanda.dwsu4t4fornerjuanda.repository.entity.Cuenta;
import edu.juanda.dwsu4t4fornerjuanda.repository.entity.Movimiento;
import edu.juanda.dwsu4t4fornerjuanda.repository.entity.Recomendacion;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class FakeDatabase {
    final Logger log = LoggerFactory.getLogger(FakeDatabase.class);
    
    private Long CURR_ID_CLIENTE = 1L;
    private Long CURR_ID_RECOMENDACION = 1L;
    private Long CURR_ID_CUENTA = 1L;
    private Long CURR_ID_MOVIMIENTO = 1L;

    @Getter
    private List<Cliente> clientes = new ArrayList<>();

    public FakeDatabase() {
        addClientesPrueba();
        addRecomendacionesPrueba();
        addCuentasPrueba();
        addMovimientosPrueba();
    }

    // Clientes
    public Cliente findClienteById(Cliente cliente) {
        int index = clientes.indexOf(cliente);

        if (index == -1)
            return null;

        return clientes.get(index);
    }

    public Cliente findClienteByIdCuenta(Cuenta cuenta) {
        for (Cliente cliente: getClientes()) {
            int index = cliente.getCuentas().indexOf(cuenta);

            if (index != -1) {
                return cliente;
            }
        }

        return null;
    }

    public void saveCliente(Cliente cliente) {
        if (cliente.getId() == null) {
            Long id = CURR_ID_CLIENTE++;
            cliente.setId(id);
            cliente.getRecomendacion().setId(CURR_ID_RECOMENDACION++);
            getClientes().add(cliente);

        } else {
            int index = clientes.indexOf(cliente);
            clientes.set(index, cliente);
        }
    }

    public void deleteCliente(Cliente cliente) {
        clientes.remove(cliente);
    }

    // Cuentas
    public List<Cuenta> getCuentas() {
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        clientes.forEach(cliente -> cuentas.addAll(cliente.getCuentas()));
        return cuentas;
    }

    public Cuenta findCuentaById(Cuenta cuenta) {
        List<Cuenta> cuentas = getCuentas();
        int index = cuentas.indexOf(cuenta);

        if (index == -1)
            return null;

        return cuentas.get(index);
    }

    public List<Cuenta> findCuentasByIdCliente(Cliente cliente) {
        int index = clientes.indexOf(cliente);

        // Si no existe el cliente se devuelve null
        if (index == -1)
            return null;

        return clientes.get(index).getCuentas();
    }

    public void saveCuenta(Cuenta cuenta) {
        // Busca el propietario de la cuenta y lo agrega
        Cliente propietario = findClienteById(cuenta.getPropietario());
        cuenta.setPropietario(propietario);

        // Obtiene la index de la cuenta
        List<Cuenta> cuentas = getCuentas();
        int index = cuentas.indexOf(cuenta);

        // Si la cuenta no existe se agrega
        if (index == -1) {
            // Asigna el id
            cuenta.setId(CURR_ID_CUENTA++);

            // Agrega la cuenta al propietario
            propietario.getCuentas().add(cuenta);

        // Se actualizan los datos de una cuenta
        } else {
            // Obtiene las listas del propietario
            List<Cuenta> cuentasCliente = propietario.getCuentas();

            // Obtiene el indice de la cuenta
            int cuentaIndex = cuentasCliente.indexOf(cuenta);

            // Actualiza su lista de cuentas
            cuentasCliente.set(cuentaIndex, cuenta);
        }
    }

    public void deleteCuenta(Cuenta cuenta) {
        Cliente cliente = findClienteByIdCuenta(cuenta);

        List<Cuenta> cuentas = cliente.getCuentas();
        cuentas.remove(cuenta);
    }

    // Movimientos
    public List<Movimiento> getMovimientos() {
        ArrayList<Movimiento> movimientos = new ArrayList<>();
        getCuentas().forEach(cuenta ->  {
            cuenta.getMovimientos().forEach(movimiento -> {
                if (!movimientos.contains(movimiento))
                    movimientos.add(movimiento);
            });
        });

        return movimientos;
    }

    public Movimiento findMovimientoById(Movimiento movimiento) {
        List<Movimiento> movimientos = getMovimientos();
        int index = movimientos.indexOf(movimiento);

        // Si no existe el elemento se devuelve null
        if (index == -1)
            return null;

        return movimientos.get(index);
    }

    public List<Movimiento> findMovimientosByIdCuenta(Cuenta cuenta) {
        List<Cuenta> cuentas = getCuentas();
        int index = cuentas.indexOf(cuenta);

        // Si no existe la cuenta se devuelve null
        if (index == -1)
            return null;

        return cuentas.get(index).getMovimientos();
    }

    public void saveMovimiento(Movimiento movimiento) {
        // Busca las cuentas involucradas
        Cuenta cuentaOrigen = findCuentaById(movimiento.getCuentaOrigen());
        Cuenta cuentaDestino = findCuentaById(movimiento.getCuentaDestino());

        // Agrega las cuentas al movimiento
        movimiento.setCuentaOrigen(cuentaOrigen);
        movimiento.setCuentaDestino(cuentaDestino);

        System.out.println(movimiento.getCuentaOrigen());
        System.out.println(movimiento.getCuentaDestino());

        // Obtiene el importe
        double importe = movimiento.getImporte();

        List<Movimiento> movimientos = getMovimientos();
        int index = movimientos.indexOf(movimiento);

        // Si el movimiento no existe se agrega
        if (index == -1) {
            movimiento.setId(CURR_ID_MOVIMIENTO++);

            // Agrega y aplica el movimiento a la cuenta origen
            findMovimientosByIdCuenta(cuentaOrigen).add(movimiento);
            cuentaOrigen.retirar(importe);

            // Agrega y aplica el movimiento a la cuenta destino
            findMovimientosByIdCuenta(cuentaDestino).add(movimiento);
            cuentaDestino.ingresar(importe);

        // Si el movimiento existe se actualiza
        } else {
            // Revierte el movimiento anterior
            double oldImporte = movimientos.get(index).getImporte();
            cuentaOrigen.ingresar(oldImporte);
            cuentaDestino.retirar(oldImporte);

            // Aplica el movimiento nuevo
            cuentaOrigen.retirar(importe);
            cuentaDestino.ingresar(importe);

            // Obtiene el indice del movimiento de la cuenta origen
            List<Movimiento> movimientosOrigen = findMovimientosByIdCuenta(cuentaOrigen);
            int indexOrigen = movimientosOrigen.indexOf(movimiento);

            // Actualiza el movimiento de la cuenta origen
            movimientosOrigen.set(indexOrigen, movimiento);

            // Obtiene el indice del movimiento de la cuenta destino
            List<Movimiento> movimientosDestino = findMovimientosByIdCuenta(cuentaDestino);
            int indexDestino = movimientosDestino.indexOf(movimiento);

            // Actualiza el movimiento de la cuenta destino
            movimientosDestino.set(indexDestino, movimiento);
        }
    }

    public void deleteMovimiento(Movimiento movimiento) {
        // Obtiene las cuentas involucradas
        Cuenta cuentaOrigen = findCuentaById(movimiento.getCuentaOrigen());
        Cuenta cuentaDestino = findCuentaById(movimiento.getCuentaDestino());

        // Revierte el movimiento
        cuentaOrigen.ingresar(movimiento.getImporte());
        cuentaDestino.retirar(movimiento.getImporte());

        // Elimina los movimientos de las cuentas
        cuentaOrigen.getMovimientos().remove(movimiento);
        cuentaDestino.getMovimientos().remove(movimiento);
    }


    private void addClientesPrueba() {
        log.info("Database - Añadiendo clientes");

        // Cliente 1
        Cliente c1 = new Cliente();
        c1.setId(CURR_ID_CLIENTE++);
        c1.setNombre("Antonio");
        c1.setApellidos("López");
        c1.setNif("11111111A");
        c1.setEmail("antonio.lopez@prueba.com");
        c1.setClaveSeguridad("12345");

        // Cliente 2
        Cliente c2 = new Cliente();
        c2.setId(CURR_ID_CLIENTE++);
        c2.setNombre("Belen");
        c2.setApellidos("Cuenca");
        c2.setNif("22222222B");
        c2.setEmail("belen.cuenca@prueba.com");
        c2.setClaveSeguridad("67890");

        // Cliente 3
        Cliente c3 = new Cliente();
        c3.setId(CURR_ID_CLIENTE++);
        c3.setNombre("Juan");
        c3.setApellidos("Pérez");
        c3.setNif("33333333C");
        c3.setEmail("juan.perez@prueba.com");
        c3.setClaveSeguridad("02468");

        // Cliente 4
        Cliente c4 = new Cliente();
        c4.setId(CURR_ID_CLIENTE++);
        c4.setNombre("María");
        c4.setApellidos("Rodríguez");
        c4.setNif("44444444D");
        c4.setEmail("maria.rodriguez@prueba.com");
        c4.setClaveSeguridad("13579");

        // Cliente 5
        Cliente c5 = new Cliente();
        c5.setId(CURR_ID_CLIENTE++);
        c5.setNombre("Juan");
        c5.setApellidos("Gómez-Jurado");
        c5.setNif("55555555E");
        c5.setEmail("juan.gomez-jurado@prueba.com");
        c5.setClaveSeguridad("98765");

        clientes.addAll(Arrays.asList(c1, c2, c3, c4, c5));
    }

    private void addRecomendacionesPrueba() {
        log.info("Database - Añadiendo recomendaciones");
        Cliente c1 = clientes.get(0);
        Cliente c2 = clientes.get(1);
        Cliente c3 = clientes.get(2);
        Cliente c4 = clientes.get(3);
        Cliente c5 = clientes.get(4);

        Recomendacion r1 = new Recomendacion();
        r1.setId(CURR_ID_RECOMENDACION++);
        r1.setObservaciones("No tiene ninguna");
        r1.setCliente(c1);
        c1.setRecomendacion(r1);

        Recomendacion r2 = new Recomendacion();
        r2.setId(CURR_ID_RECOMENDACION++);
        r2.setObservaciones("Muy recomendada");
        r2.setCliente(c2);
        c2.setRecomendacion(r2);

        Recomendacion r3 = new Recomendacion();
        r3.setId(CURR_ID_RECOMENDACION++);
        r3.setObservaciones("Sin comentarios");
        r3.setCliente(c3);
        c3.setRecomendacion(r3);

        Recomendacion r4 = new Recomendacion();
        r4.setId(CURR_ID_RECOMENDACION++);
        r4.setObservaciones("Le encanta The Mandalorian");
        r4.setCliente(c4);
        c4.setRecomendacion(r4);

        Recomendacion r5 = new Recomendacion();
        r5.setId(CURR_ID_RECOMENDACION++);
        r5.setObservaciones("Escritor famoso");
        r5.setCliente(c5);
        c5.setRecomendacion(r5);
    }

    private void addCuentasPrueba() {
        log.info("Database - Añadiendo cuentas de prueba");
        Cliente c1 = clientes.get(0);
        Cliente c2 = clientes.get(1);

        Cuenta cuenta = new Cuenta();
        cuenta.setId(CURR_ID_CUENTA++);
        cuenta.setBanco("Bankia");
        cuenta.setSucursal("Estivella");
        cuenta.setDc("12");
        cuenta.setNumeroCuenta("ES1111111111111111111111");
        cuenta.setSaldo(20520.98);
        cuenta.setPropietario(c1);
        c1.getCuentas().add(cuenta);

        Cuenta cuenta2 = new Cuenta();
        cuenta2.setId(CURR_ID_CUENTA++);
        cuenta2.setBanco("Santander");
        cuenta2.setSucursal("Valencia");
        cuenta2.setDc("13");
        cuenta2.setNumeroCuenta("ES2222222222222222222222");
        cuenta2.setPropietario(c1);
        c1.getCuentas().add(cuenta2);

        Cuenta cuenta3 = new Cuenta();
        cuenta3.setId(CURR_ID_CUENTA++);
        cuenta3.setBanco("BBVA");
        cuenta3.setSucursal("Castellón");
        cuenta3.setDc("12");
        cuenta3.setNumeroCuenta("ES3333333333333333333333");
        cuenta3.setPropietario(c2);
        c2.getCuentas().add(cuenta3);

        Cuenta cuenta4 = new Cuenta();
        cuenta4.setId(CURR_ID_CUENTA++);
        cuenta4.setBanco("CaixaBank");
        cuenta4.setSucursal("Alicante");
        cuenta4.setDc("12");
        cuenta4.setNumeroCuenta("ES4444444444444444444444");
        cuenta4.setPropietario(c2);
        c2.getCuentas().add(cuenta4);
    }

    private void addMovimientosPrueba() {
        log.info("Database - Añadiendo movimientos de prueba");
        Cuenta origen = clientes.get(0).getCuentas().get(0);
        Cuenta destino = clientes.get(0).getCuentas().get(1);

        Movimiento movimiento = new Movimiento();
        movimiento.setId(CURR_ID_MOVIMIENTO++);
        movimiento.setTipo("tipo");
        movimiento.setFechaOperacion(new Date(System.currentTimeMillis()));
        movimiento.setDescripcion("descripcion");
        movimiento.setImporte(1520);
        movimiento.setCuentaOrigen(origen);
        movimiento.setCuentaDestino(destino);

        origen.getMovimientos().add(movimiento);
        destino.getMovimientos().add(movimiento);
        origen.retirar(movimiento.getImporte());
        destino.ingresar(movimiento.getImporte());
    }
}
