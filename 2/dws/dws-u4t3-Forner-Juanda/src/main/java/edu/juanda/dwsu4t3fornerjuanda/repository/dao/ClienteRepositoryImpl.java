package edu.juanda.dwsu4t3fornerjuanda.repository.dao;

import edu.juanda.dwsu4t3fornerjuanda.repository.entity.Cliente;
import edu.juanda.dwsu4t3fornerjuanda.repository.entity.Cuenta;
import edu.juanda.dwsu4t3fornerjuanda.repository.entity.Recomendacion;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {
    private static Long CURR_ID_CLIENTE = 1L;
    public static Long CURR_ID_CUENTA = 1L;

    public static final List<Cliente> datos = new ArrayList<>();

    static {
        // Cliente 1
        Cliente c1 = new Cliente();
        c1.setId(CURR_ID_CLIENTE++);
        c1.setNombre("Antonio");
        c1.setApellidos("López");
        c1.setNif("11111111A");
        c1.setEmail("antonio.lopez@prueba.com");
        c1.setClaveSeguridad("12345");

        Recomendacion r1 = new Recomendacion();
        r1.setId((long) (datos.size() + 1));
        r1.setObservaciones("No tiene ninguna");
        r1.setCliente(c1);
        c1.setRecomendacion(r1);
        datos.add(c1);

        Cuenta cuenta = new Cuenta();
        cuenta.setId(CURR_ID_CUENTA++);
        cuenta.setBanco("Bankia");
        cuenta.setSucursal("Estivella");
        cuenta.setDc("12");
        cuenta.setNumeroCuenta("ES1111111111111111111111");
        cuenta.setPropietario(c1);

        Cuenta cuenta2 = new Cuenta();
        cuenta2.setId(CURR_ID_CUENTA++);
        cuenta2.setBanco("Santander");
        cuenta2.setSucursal("Valencia");
        cuenta2.setDc("13");
        cuenta2.setNumeroCuenta("ES2222222222222222222222");
        cuenta2.setPropietario(c1);

        c1.getCuentas().add(cuenta);
        c1.getCuentas().add(cuenta2);

        // Cliente 2
        Cliente c2 = new Cliente();
        c2.setId(CURR_ID_CLIENTE++);
        c2.setNombre("Belen");
        c2.setApellidos("Cuenca");
        c2.setNif("22222222B");
        c2.setEmail("belen.cuenca@prueba.com");
        c2.setClaveSeguridad("67890");

        Recomendacion r2 = new Recomendacion();
        r2.setId((long) (datos.size() + 1));
        r2.setObservaciones("Muy recomendada");
        r2.setCliente(c2);
        c2.setRecomendacion(r2);
        datos.add(c2);

        Cuenta cuenta3 = new Cuenta();
        cuenta3.setId(CURR_ID_CUENTA++);
        cuenta3.setBanco("BBVA");
        cuenta3.setSucursal("Castellón");
        cuenta3.setDc("12");
        cuenta3.setNumeroCuenta("ES3333333333333333333333");
        cuenta3.setPropietario(c2);

        Cuenta cuenta4 = new Cuenta();
        cuenta4.setId(CURR_ID_CUENTA++);
        cuenta4.setBanco("CaixaBank");
        cuenta4.setSucursal("Alicante");
        cuenta4.setDc("12");
        cuenta4.setNumeroCuenta("ES4444444444444444444444");
        cuenta4.setPropietario(c2);

        c2.getCuentas().add(cuenta3);
        c2.getCuentas().add(cuenta4);

        // Cliente 3
        Cliente c3 = new Cliente();
        c3.setId(CURR_ID_CLIENTE++);
        c3.setNombre("Juan");
        c3.setApellidos("Pérez");
        c3.setNif("33333333C");
        c3.setEmail("juan.perez@prueba.com");
        c3.setClaveSeguridad("02468");

        Recomendacion r3 = new Recomendacion();
        r3.setId((long) (datos.size() + 1));
        r3.setObservaciones("Sin comentarios");
        r3.setCliente(c3);
        c3.setRecomendacion(r3);
        datos.add(c3);

        // Cliente 4
        Cliente c4 = new Cliente();
        c4.setId(CURR_ID_CLIENTE++);
        c4.setNombre("María");
        c4.setApellidos("Rodríguez");
        c4.setNif("44444444D");
        c4.setEmail("maria.rodriguez@prueba.com");
        c4.setClaveSeguridad("13579");

        Recomendacion r4 = new Recomendacion();
        r4.setId((long) (datos.size() + 1));
        r4.setObservaciones("Le encanta The Mandalorian");
        r4.setCliente(c4);
        c4.setRecomendacion(r4);
        datos.add(c4);

        // Cliente 5
        Cliente c5 = new Cliente();
        c5.setId(CURR_ID_CLIENTE++);
        c5.setNombre("Juan");
        c5.setApellidos("Gómez-Jurado");
        c5.setNif("55555555E");
        c5.setEmail("juan.gomez-jurado@prueba.com");
        c5.setClaveSeguridad("98765");

        Recomendacion r5 = new Recomendacion();
        r5.setId((long) (datos.size() + 1));
        r5.setObservaciones("Escritor famoso");
        r5.setCliente(c5);
        c5.setRecomendacion(r5);
        datos.add(c5);
    }

    @Override
    public List<Cliente> findAll() {
        return datos;
    }

    @Override
    public Cliente findById(Cliente cliente) {
        int posicion = datos.indexOf(cliente);

        if (posicion == -1)
            return null;

        return datos.get(posicion);
    }

    @Override
    public void save(Cliente cliente) {
        if(cliente.getId() != null) {
            int posicion = datos.indexOf(cliente);
            datos.set(posicion, cliente);
        }else {
            Long id = CURR_ID_CLIENTE++;
            cliente.setId(id);
            cliente.getRecomendacion().setId(0L);
            System.out.println(cliente);
            datos.add(cliente);
        }
    }

    @Override
    public void delete(Cliente cliente) {
        // TODO: al eliminar el cliente se deberian de eliminar las cuentas relacionadas con ella
        datos.remove(cliente);
    }
}