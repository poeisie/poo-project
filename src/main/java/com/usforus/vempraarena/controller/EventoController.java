package com.usforus.vempraarena.controller;

import com.usforus.vempraarena.dto.EventoDTO;
import com.usforus.vempraarena.entities.Evento;
import com.usforus.vempraarena.service.EventoService;
import com.usforus.vempraarena.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.Authentication;

import java.util.List;

@Controller
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping({"/criar", "/novo"})
    public String mostrarFormularioCriacao(Model model) {
        model.addAttribute("EventoDTO", new EventoDTO());
        return "criar-evento";
    }

    @PostMapping("/criar")
    public String criarEvento(@ModelAttribute ("EventoDTO") EventoDTO dto, Model model, RedirectAttributes redirectAttributes) throws Exception {

        try {
            eventoService.criarEvento(dto);
            return "redirect:/eventos/listar";

        } catch (Exception e) {

            model.addAttribute("erroEvento", e.getMessage());
            return "criar-evento";
        }
    }

    @GetMapping("/listar")
    public String listarEvento(Model model, Authentication authentication) {
    Usuario usuario = usuarioRepository.findByEmail(authentication.getName());
    model.addAttribute("nomeUsuario", usuario.getName());
        List<Evento> eventos = eventoService.listarEvento();
        model.addAttribute("eventos", eventos);
        return "listar-eventos";
    }

}
