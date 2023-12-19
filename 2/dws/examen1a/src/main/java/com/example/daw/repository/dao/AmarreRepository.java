package com.example.daw.repository.dao;

import com.example.daw.repository.entity.Amarre;
import com.example.daw.repository.entity.Zona;

import java.util.List;

public interface AmarreRepository {
    List<Amarre> findByIdZona(Zona zona);
    void delete(Amarre amarre);
}
