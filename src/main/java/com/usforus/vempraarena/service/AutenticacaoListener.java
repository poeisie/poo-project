package com.usforus.vempraarena.service;

import com.usforus.vempraarena.entities.Usuario;
import com.usforus.vempraarena.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AutenticacaoListener {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SegurancaLoginService segurancaLoginService;

    @EventListener
    public void onLoginSuccess(AuthenticationSuccessEvent event) {
        Object principal = event.getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            segurancaLoginService.resetarFalhas(email);
        }
    }

    @EventListener
    public void onLoginFailure(AuthenticationFailureBadCredentialsEvent event) {
        String email = (String) event.getAuthentication().getPrincipal();
        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario != null && usuario.isAccountNonLocked()) {
            segurancaLoginService.registrarFalhaLogin(usuario);
        }
    }
}