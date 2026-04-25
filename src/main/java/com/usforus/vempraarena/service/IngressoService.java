package com.usforus.vempraarena.service;


import com.usforus.vempraarena.entities.Ingresso;
import com.usforus.vempraarena.repository.IngressoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngressoService {

    private final IngressoRepository ingressoRepository;

    public IngressoService(IngressoRepository ingressoRepository) {
        this.ingressoRepository = ingressoRepository;
    }

    public List<Ingresso> listarPorUsuario(Long usuarioId){
        return ingressoRepository.findByUsuarioId(usuarioId);
    }

}
