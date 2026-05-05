package com.usforus.vempraarena.controller;

import com.usforus.vempraarena.dto.UsuarioCadastroProdutorDTO;
import com.usforus.vempraarena.entities.Usuario;
import com.usforus.vempraarena.repository.UsuarioRepository;
import com.usforus.vempraarena.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/produtor")
public class ProdutorController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    public ProdutorController(UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("cadastro/cpf")
    public String formularioCpf(Model model) {
        model.addAttribute("usuarioCadastroProdutorDTO", new UsuarioCadastroProdutorDTO());
        return "cadastro-cpf";
    }

    @PostMapping("cadastro/cpf")
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

    @GetMapping("cadastro/cnpj")
    public String formularioCnpj(Model model) {
        model.addAttribute("usuarioCadastroProdutorDTO", new UsuarioCadastroProdutorDTO());
        return "cadastro-cnpj";
    }

    @PostMapping("cadastro/cnpj")
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

}
