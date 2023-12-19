import java.util.Arrays;
import java.util.List;

import VideoClub.Alquiler;
import VideoClub.Cliente;
import VideoClub.Dvd;

public class PruebaVideoClub {
    public static void main(String[] args) {

        Cliente c = new Cliente("Juan Daniel Forner");
        Cliente c1 = new Cliente("Jorge Gabriel");
        
        Dvd dvd1 = new Dvd("Coca La Exploradora", Dvd.NOVEDAD);
        Dvd dvd2 = new Dvd("Princesa me la pone tiesa", Dvd.NOVEDAD);
        Dvd dvd3 = new Dvd("House", Dvd.NOVEDAD);
        
        Alquiler a1 = new Alquiler(dvd1, 2);
        Alquiler a2 = new Alquiler(dvd2, 4);
        Alquiler a3 = new Alquiler(dvd3, 1);
        Alquiler a4 = new Alquiler(dvd1, 1);
        Alquiler a5 = new Alquiler(dvd2, 2);
        Alquiler a6 = new Alquiler(dvd3, 9);
        
        c.nuevoAlquiler(a1);
        c.nuevoAlquiler(a2);
        c.nuevoAlquiler(a3);
        c1.nuevoAlquiler(a4);
        c1.nuevoAlquiler(a5);
        c1.nuevoAlquiler(a6);
        
        List<Cliente> clientes= Arrays.asList(c, c1);
        clientes.forEach((cliente) -> {
            System.out.println(cliente.informe());
        });
        
    }
}
