package com.usforus.vempraarena.controller;


import com.usforus.vempraarena.entities.Ingresso;
import com.usforus.vempraarena.entities.Usuario;
import com.usforus.vempraarena.repository.UsuarioRepository;
import com.usforus.vempraarena.service.IngressoService;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ingressos")
public class IngressoController {

    private final IngressoService ingressoService;
    private final UsuarioRepository usuarioRepository;

    public IngressoController(IngressoService ingressoService, UsuarioRepository usuarioRepository){
        this.ingressoService = ingressoService;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/meus")
    public String meusIngressos(Authentication authentication, Model model){

        String email = authentication.getName();
        Usuario usuario = usuarioRepository.findByEmail(email);

        List<Ingresso> ingressos =  ingressoService.listarPorUsuario(usuario.getId());
        model.addAttribute("ingressos", ingressos);

        return "meus-ingressos";
    }
}
