package com.example.daw.repository.dao;

import com.example.daw.repository.entity.Zona;

import java.util.List;

public interface ZonaRepository {
    List<Zona> findAll();
    Zona findById(Zona zona);
    void save(Zona zona);
    void delete(Zona zona);
}
