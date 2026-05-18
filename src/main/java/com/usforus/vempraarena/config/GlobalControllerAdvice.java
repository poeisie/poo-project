package com.usforus.vempraarena.config;

import com.usforus.vempraarena.entities.Usuario;
import com.usforus.vempraarena.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @ModelAttribute("usuarioLogado")
    public Usuario usuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
            String email = authentication.getName();
            return usuarioRepository.findByEmail(email);
        }
        return null;
    }
}
