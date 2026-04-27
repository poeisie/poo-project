package com.usforus.vempraarena.controller;
import com.usforus.vempraarena.dto.UsuarioCadastroDTO;
import com.usforus.vempraarena.entities.Usuario;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import com.usforus.vempraarena.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class AuthController {
    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/cadastro")
    public String exibirFormularioCadastro(Model model){
        model.addAttribute("usuarioCadastroDTO", new UsuarioCadastroDTO());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String processarCadastro(
            @Valid @ModelAttribute("usuarioCadastroDTO") UsuarioCadastroDTO usuarioCadastroDTO,
                BindingResult result, Model model){

        if(result.hasErrors()){
            return "cadastro";
        }

        try{
            usuarioService.cadastrarUsuario(usuarioCadastroDTO);
            return "redirect:/login?cadastrado";
        } catch(Exception e){
            model.addAttribute("erroDuplicidade", e.getMessage());
            return "cadastro";
        }
    }

    @GetMapping("/home")
    public String home(){
        return "redirect:/eventos/listar";
    }

    @GetMapping("/")
    public String paginaInicial() {
        return "redirect:/eventos/listar";
    }
}
