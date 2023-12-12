package com.senac.extensao.repository;

import com.senac.extensao.models.Agricultor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgricultorRepository extends JpaRepository<Agricultor, Long> {
}
