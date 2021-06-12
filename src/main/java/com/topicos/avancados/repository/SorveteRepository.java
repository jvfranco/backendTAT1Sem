package com.topicos.avancados.repository;

import com.topicos.avancados.model.Sorvete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SorveteRepository extends JpaRepository<Sorvete, UUID> {
}
