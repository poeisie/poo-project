package com.usforus.vempraarena.controller;

import com.usforus.vempraarena.entities.Usuario;
import com.usforus.vempraarena.repository.UsuarioRepository;
import com.usforus.vempraarena.service.EventoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class ModeracaoAdminController {

    private final EventoService eventoService;
    private final UsuarioRepository usuarioRepository;

    public ModeracaoAdminController(EventoService eventoService, UsuarioRepository usuarioRepository) {
        this.eventoService = eventoService;
        this.usuarioRepository = usuarioRepository;
    }


    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication) {
        Usuario usuario = usuarioRepository.findByEmail(authentication.getName());
        model.addAttribute("usuario", usuario);
        return "admin-area";
    }


    @GetMapping("/eventos/pendentes")
    public String painelAceite(Model model) {
        model.addAttribute("eventosPendentes", eventoService.listarEventosPendentes());
        return "aceite-eventos";
    }

    @PostMapping("/eventos/aprovar/{id}")
    public String aprovar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            eventoService.aprovarEvento(id);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/admin/eventos/pendentes";
    }

    // 3. Botão de Rejeitar
    @PostMapping("/eventos/rejeitar/{id}")
    public String rejeitar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            eventoService.rejeitarEvento(id);
            redirectAttributes.addFlashAttribute("sucesso", "Evento REJEITADO.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/admin/eventos/pendentes";
    }
}