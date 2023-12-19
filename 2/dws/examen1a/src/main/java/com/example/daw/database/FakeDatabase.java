package com.example.daw.database;

import com.example.daw.repository.entity.Amarre;
import com.example.daw.repository.entity.Barco;
import com.example.daw.repository.entity.Zona;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FakeDatabase {

    private Long CURR_ZONA_ID = 1L;
    private Long CURR_AMARRE_ID = 1L;

    @Getter
    private ArrayList<Zona> zonas = new ArrayList<>();

    public FakeDatabase() {
        // Zona z1
        Zona z1 = new Zona();
        z1.setId(CURR_ZONA_ID++);
        z1.setLetra("A");
        z1.setProfundidad(67);
        z1.setDimensiones("350");
        zonas.add(z1);

        // Amarres de z1
        Amarre a1 = new Amarre();
        a1.setId(CURR_AMARRE_ID++);
        a1.setNumero(1);
        a1.setTipo("Boya");
        a1.setDimensiones("14x5,5");
        a1.setZona(z1);
        z1.getListaAmarres().add(a1);

        Amarre a2 = new Amarre();
        a2.setId(CURR_AMARRE_ID++);
        a2.setNumero(2);
        a2.setTipo("Local");
        a2.setDimensiones("12x5");
        a2.setZona(z1);
        z1.getListaAmarres().add(a2);

        Amarre a3 = new Amarre();
        a3.setId(CURR_AMARRE_ID++);
        a3.setNumero(2);
        a3.setTipo("Boya");
        a3.setDimensiones("11x7,5");
        a3.setZona(z1);
        z1.getListaAmarres().add(a3);

        // Zona z2
        Zona z2 = new Zona();
        z2.setId(CURR_ZONA_ID++);
        z2.setLetra("B");
        z2.setProfundidad(89);
        z2.setDimensiones("245");
        zonas.add(z2);

        // Amarres de z2
        Amarre a4 = new Amarre();
        a4.setId(CURR_AMARRE_ID);
        a4.setNumero(3);
        a4.setTipo("Agarre");
        a4.setDimensiones("5x5");
        a4.setZona(z2);
        z2.getListaAmarres().add(a4);

        Amarre a5 = new Amarre();
        a5.setId(CURR_AMARRE_ID++);
        a5.setNumero(2);
        a5.setTipo("Local");
        a5.setDimensiones("25x25");
        a5.setZona(z2);
        z2.getListaAmarres().add(a5);

        Amarre a6 = new Amarre();
        a6.setId(CURR_AMARRE_ID++);
        a6.setNumero(1);
        a6.setTipo("Boya");
        a6.setDimensiones("2,5x2,5");
        a6.setZona(z2);
        z2.getListaAmarres().add(a6);

        // Zona z3
        Zona z3 = new Zona();
        z3.setId(CURR_ZONA_ID++);
        z3.setLetra("C");
        z3.setProfundidad(110);
        z3.setDimensiones("450");
        zonas.add(z3);

        // Amarres de z3
        Amarre a7 = new Amarre();
        a7.setId(CURR_AMARRE_ID++);
        a7.setNumero(1);
        a7.setTipo("Global");
        a7.setDimensiones("35x25");
        a7.setZona(z3);
        z3.getListaAmarres().add(a7);

        Amarre a8 = new Amarre();
        a8.setId(CURR_AMARRE_ID++);
        a8.setNumero(1);
        a8.setTipo("Local");
        a8.setDimensiones("35x25");
        a8.setZona(z3);
        z3.getListaAmarres().add(a8);

        // Zona z4
        Zona z4 = new Zona();
        z4.setId(CURR_ZONA_ID++);
        z4.setLetra("D");
        z4.setProfundidad(130);
        z4.setDimensiones("550");
        zonas.add(z4);

        // Amarres de z4
        Amarre a9 = new Amarre();
        a9.setId(CURR_AMARRE_ID++);
        a9.setNumero(3);
        a9.setTipo("Boya");
        a9.setDimensiones("15x25");
        a9.setZona(z4);
        z4.getListaAmarres().add(a9);

        // Zona z5
        Zona z5 = new Zona();
        z5.setId(CURR_ZONA_ID++);
        z5.setLetra("E");
        z5.setProfundidad(150);
        z5.setDimensiones("750");
        zonas.add(z5);

        // Amarres de z5
        Amarre a10 = new Amarre();
        a10.setId(CURR_AMARRE_ID++);
        a10.setNumero(1);
        a10.setTipo("Boya");
        a10.setDimensiones("105x250");
        a10.setZona(z5);
        z5.getListaAmarres().add(a10);
    }

    // ZONAS
    public Zona findZonaById(Zona zona) {
        int index = zonas.indexOf(zona);

        if (index == -1)
            return null;

        return zonas.get(index);
    }

    public void saveZona(Zona zona) {
        // Si se añade una zona
        if (zona.getId() == null) {
            zona.setId(CURR_ZONA_ID++);
            zonas.add(zona);

        // Si se modifica una zona
        } else {
            int index = zonas.indexOf(zona);

            // Se le añade la lista de amarres
            Zona old = zonas.get(index);
            zona.setListaAmarres(old.getListaAmarres());

            zonas.set(index, zona);
        }
    }

    public void deleteZona(Zona zona) {
        zonas.remove(zona);
    }

    // AMARRES
    public List<Amarre> findAllAmarres() {
        List<Amarre> amarres = new ArrayList<>();

        for (Zona zona : zonas) {
            amarres.addAll(zona.getListaAmarres());
        }

        return amarres;
    }

    public List<Amarre> findAmarresByIdZona(Zona zona) {
        return findAllAmarres().stream().filter(amarre -> amarre.getZona().equals(zona)).toList();
    }

    public void deleteAmarre(Amarre amarre) {
        for (Zona zona : zonas) {
            if (zona.getListaAmarres().remove(amarre))
                return;
        }
    }

    public List<Barco> findAllBarcos() {
        List<Barco> barcos = new ArrayList<>();

        for (Zona zona : zonas) {
            for (Amarre amarre : findAllAmarres())
                barcos.addAll(amarre.getListaBarcos());
        }

        return barcos;
    }

    public List<Barco> findBarcoByIdAmarre(Amarre amarre) {
        return findAllBarcos().stream().filter(barco -> barco.getAmarre().equals(amarre)).toList();
    }
}
