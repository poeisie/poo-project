package com.usforus.vempraarena.repository;

import com.usforus.vempraarena.entities.Ingresso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngressoRepository  extends JpaRepository<Ingresso, Long> {

    List<Ingresso> findByUsuarioId(Long usuarioId);

    @Query("SELECT i FROM Ingresso i WHERE i.evento.id IN :ids")
    List<Ingresso> findByEventoIds(@Param("ids") List<Long> ids);

}
