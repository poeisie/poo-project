package com.usforus.vempraarena.repository;

import java.time.LocalDate;
import java.util.List;

import com.usforus.vempraarena.entities.Evento;
import com.usforus.vempraarena.entities.StatusEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findByStatus(StatusEvento status);
    List<Evento> findByStatusOrderByDataAsc(StatusEvento status);
    List<Evento> findByDataGreaterThanEqualOrderByDataAsc(LocalDate dataAtual);
    List<Evento> findByDataBetweenOrderByDataAsc(LocalDate inicioSemana, LocalDate fimSemana);
    List<Evento> findByCategoriasContaining(String categoria);
}
