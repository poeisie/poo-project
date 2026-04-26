package com.usforus.vempraarena.controller;

import com.usforus.vempraarena.dto.CompraIngressoDTO;
import com.usforus.vempraarena.entities.Evento;
import com.usforus.vempraarena.entities.Ingresso;
import com.usforus.vempraarena.entities.Usuario;
import com.usforus.vempraarena.repository.UsuarioRepository;
import com.usforus.vempraarena.service.EventoService;
import com.usforus.vempraarena.service.IngressoService;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ingressos")
public class IngressoController {

    private final IngressoService ingressoService;
    private final EventoService eventoService;
    private final UsuarioRepository usuarioRepository;

    public IngressoController(IngressoService ingressoService, EventoService eventoService, UsuarioRepository usuarioRepository){
        this.ingressoService = ingressoService;
        this.eventoService = eventoService;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/comprar/{eventoId}")
    public String telaCompra(@PathVariable Long eventoId, Authentication authentication, Model model) {
        Evento evento = eventoService.buscarPorId(eventoId);
        Usuario usuarioLogado = usuarioRepository.findByEmail(authentication.getName());

        CompraIngressoDTO dto = new CompraIngressoDTO();
        dto.setEventoId(evento.getId());

        model.addAttribute("evento", evento);
        model.addAttribute("usuario", usuarioLogado);
        model.addAttribute("compraDto", dto);

        return "comprar-ingresso";
    }

    @PostMapping("/comprar")
    public String processarCompra(@ModelAttribute("compraDto") CompraIngressoDTO dto, Authentication authentication, Model model, RedirectAttributes attributes) {
        String emailLogado = authentication.getName();

        try {
            ingressoService.realizarCompra(dto, emailLogado);
            attributes.addFlashAttribute("sucesso", "Compra realizada com sucesso! Valor descontado da sua carteira.");
            return "redirect:/ingressos/meus";

        } catch (Exception e) {
            Evento evento = eventoService.buscarPorId(dto.getEventoId());
            Usuario usuarioLogado = usuarioRepository.findByEmail(emailLogado);

            model.addAttribute("evento", evento);
            model.addAttribute("usuario", usuarioLogado);
            model.addAttribute("erroCompra", e.getMessage());

            return "comprar-ingresso";
        }
    }

    @GetMapping("/meus")
    public String meusIngressos(Authentication authentication, Model model){
        Usuario usuario = usuarioRepository.findByEmail(authentication.getName());
        List<Ingresso> ingressos = ingressoService.listarPorUsuario(usuario.getId());
        model.addAttribute("ingressos", ingressos);
        return "meus-ingressos";
    }
}