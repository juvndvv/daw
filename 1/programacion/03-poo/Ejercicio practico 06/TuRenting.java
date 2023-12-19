import Vehiculo.Vehiculo;
import Vehiculo.Coche;
import Vehiculo.Moto;
import Vehiculo.Yate;
import Vehiculo.GloboAerostatico;

import java.util.ArrayList;

public class TuRenting {
    public static void main(String[] args) {

        // Instanciamos el ArrayList
        ArrayList<Vehiculo> garaje = new ArrayList<Vehiculo>();

        // Rellenamos el ArrayList
        garaje.add(new Coche("Peugot 207", "6842GHS", 4, true));
        garaje.add(new Coche("Citroen C15", "1234abc", 3, false));
        garaje.add(new Coche("Berlingo", "0000AAA",4, false));
        
        garaje.add(new Moto("Rieju Tango 125i Scrambler 2023", "1234ccc", 2, false));
        garaje.add(new Moto("Bradus 1300R Edition 23", "4321abb", 3, true));

        garaje.add(new Yate("Fly 83", "1234567", 43, false));
        garaje.add(new Yate("Azimut Fly 50", "123456789", 500, true));

        garaje.add(new GloboAerostatico("Mi Jodido Globo 3000", "ABCD878787", 20, 5, true));

        // Recorremos el ArrayList y llamamos al metodo imprimir de todos los vehiculos
        garaje.forEach(Vehiculo::imprimir);
    }
}
