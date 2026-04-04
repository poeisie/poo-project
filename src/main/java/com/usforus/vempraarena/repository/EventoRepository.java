package com.usforus.vempraarena.repository;

import java.time.LocalDate;
import java.util.List;

import com.usforus.vempraarena.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findByDataGreaterThanEqualOrderByDataAsc(LocalDate dataAtual);
}
