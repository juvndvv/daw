package com.example.daw.repository.dao;


import com.example.daw.database.FakeDatabase;
import com.example.daw.repository.entity.Amarre;
import com.example.daw.repository.entity.Zona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AmarreRepositoryImpl implements AmarreRepository {

    @Autowired
    FakeDatabase database;

    @Override
    public List<Amarre> findByIdZona(Zona zona) {
        return database.findAmarresByIdZona(zona);
    }

    @Override
    public void delete(Amarre amarre) {
        database.deleteAmarre(amarre);
    }
}
