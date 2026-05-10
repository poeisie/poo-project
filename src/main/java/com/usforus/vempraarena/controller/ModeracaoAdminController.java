package com.usforus.vempraarena.controller;

import com.usforus.vempraarena.service.EventoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/eventos")
@PreAuthorize("hasRole('ADMIN')")
public class ModeracaoAdminController {

    private final EventoService eventoService;

    public ModeracaoAdminController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping("/pendentes")
    public String painelAceite(Model model) {
        model.addAttribute("eventosPendentes", eventoService.listarEventosPendentes());
        return "aceite-eventos";
    }

    @PostMapping("/aprovar/{id}")
    public String aprovar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            eventoService.aprovarEvento(id);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/admin/eventos/pendentes";
    }

    // 3. Botão de Rejeitar
    @PostMapping("/rejeitar/{id}")
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