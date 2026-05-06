package com.usforus.vempraarena.controller;

import java.util.List;

import com.usforus.vempraarena.repository.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.usforus.vempraarena.dto.UsuarioCadastroProdutorDTO;
import com.usforus.vempraarena.entities.Evento;
import com.usforus.vempraarena.entities.Usuario;
import com.usforus.vempraarena.service.UsuarioService;
import com.usforus.vempraarena.service.EventoService;


import jakarta.validation.Valid;

@Controller
@RequestMapping("/produtor")
public class ProdutorController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final EventoService eventoService;

    public ProdutorController(UsuarioService usuarioService, UsuarioRepository usuarioRepository, EventoService eventoService) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
        this.eventoService = eventoService;
    }

    @GetMapping("/cadastro")
    public String formularioCPFOuCNPJ(Model model, Authentication authentication) {
        Usuario usuario = usuarioRepository.findByEmail(authentication.getName());
        model.addAttribute("usuario", usuario);
        return "cadastro-produtor";
    }


    @GetMapping("/cadastro/cpf")
    public String formularioCpf(Model model) {
        model.addAttribute("usuarioCadastroProdutorDTO", new UsuarioCadastroProdutorDTO());
        return "cadastro-cpf";
    }

    @PostMapping("/cadastro/cpf")
    public String processarCpf(
            @Valid @ModelAttribute("usuarioCadastroProdutorDTO") UsuarioCadastroProdutorDTO dto,
            BindingResult result,
            Authentication authentication,
            Model model) {

        if (result.hasErrors()) {
            return "cadastro-cpf";
        }

        try {
            Usuario usuario = usuarioRepository.findByEmail(authentication.getName());
            usuarioService.cadastrarProdutorCpf(usuario.getId(), dto);
            return "redirect:/home?produtor";
        } catch (Exception e) {
            model.addAttribute("erroDuplicidade", e.getMessage());
            return "cadastro-cpf";
        }
    }

    @GetMapping("/cadastro/cnpj")
    public String formularioCnpj(Model model) {
        model.addAttribute("usuarioCadastroProdutorDTO", new UsuarioCadastroProdutorDTO());
        return "cadastro-cnpj";
    }

    @PostMapping("/cadastro/cnpj")
    public String processarCnpj(
            @Valid @ModelAttribute("usuarioCadastroProdutorDTO") UsuarioCadastroProdutorDTO dto,
            BindingResult result,
            Authentication authentication,
            Model model) {

        if (result.hasErrors()) {
            return "cadastro-cnpj";
        }

        try {
            Usuario usuario = usuarioRepository.findByEmail(authentication.getName());
            usuarioService.cadastrarProdutorCnpj(usuario.getId(), dto);
            return "redirect:/home?produtor";
        } catch (Exception e) {
            model.addAttribute("erroDuplicidade", e.getMessage());
            return "cadastro-cnpj";
        }
    }

    @GetMapping("/meus-eventos")
    public String exibirMeusEventos(Model model, Authentication authentication) {
        Usuario usuario = usuarioRepository.findByEmail(authentication.getName());
        model.addAttribute("usuario", usuario);

        List<Evento> eventos = eventoService.listarEvento();
        model.addAttribute("eventos", eventos);
        return "meus-eventos";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication) {
        Usuario usuario = usuarioRepository.findByEmail(authentication.getName());
        model.addAttribute("usuario", usuario);
        return "dashboard";
    }

}

