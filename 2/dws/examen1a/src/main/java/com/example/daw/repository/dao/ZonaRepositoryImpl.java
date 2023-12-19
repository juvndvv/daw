package com.example.daw.repository.dao;

import com.example.daw.database.FakeDatabase;
import com.example.daw.repository.entity.Zona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class ZonaRepositoryImpl implements ZonaRepository {

	@Autowired
	FakeDatabase database;

	@Override
	public List<Zona> findAll() {
		return database.getZonas();
	}

	@Override
	public Zona findById(Zona zona) {
		return database.findZonaById(zona);
	}

	@Override
	public void save(Zona zona) {
		database.saveZona(zona);
	}

	@Override
	public void delete(Zona zona) {
		database.deleteZona(zona);
	}
}
