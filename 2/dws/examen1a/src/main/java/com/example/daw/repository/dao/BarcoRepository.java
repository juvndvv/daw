package com.example.daw.repository.dao;

import com.example.daw.repository.entity.Amarre;
import com.example.daw.repository.entity.Barco;

import java.util.List;

public interface BarcoRepository {
    List<Barco> findByIdAmarre(Amarre amarre);
}
