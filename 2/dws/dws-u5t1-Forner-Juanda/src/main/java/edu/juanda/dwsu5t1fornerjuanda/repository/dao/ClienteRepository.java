package edu.juanda.dwsu5t1fornerjuanda.repository.dao;

import edu.juanda.dwsu5t1fornerjuanda.repository.entity.Cliente;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}