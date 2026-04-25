package com.usforus.vempraarena.repository;

import com.usforus.vempraarena.entities.Ingresso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngressoRepository  extends JpaRepository<Ingresso, Long> {

    List<Ingresso> findByUsuarioId(Long usuarioId);

}
