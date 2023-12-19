package com.example.daw.repository.dao;

import com.example.daw.database.FakeDatabase;
import com.example.daw.repository.entity.Amarre;
import com.example.daw.repository.entity.Barco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BarcoRepositoryImpl implements BarcoRepository {
    @Autowired
    FakeDatabase database;
    @Override
    public List<Barco> findByIdAmarre(Amarre amarre) {
        return database.findBarcoByIdAmarre(amarre);
    }
}
