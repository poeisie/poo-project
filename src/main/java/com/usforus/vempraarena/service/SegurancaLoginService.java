package com.usforus.vempraarena.service;

import com.usforus.vempraarena.entities.Usuario;
import com.usforus.vempraarena.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class SegurancaLoginService {

    public static final int MAX_TENTATIVAS = 5;
    public static final long TEMPO_BLOQUEIO_MINUTOS = 15;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void registrarFalhaLogin(Usuario usuario) {
        int novasFalhas = usuario.getTentativasFalhas() + 1;
        usuario.setTentativasFalhas(novasFalhas);

        if (novasFalhas >= MAX_TENTATIVAS) {
            usuario.setContaNaoBloqueada(false);
            usuario.setTempoBloqueio(LocalDateTime.now());
        }
        usuarioRepository.save(usuario);
    }

    public void resetarFalhas(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null) {
            usuario.setTentativasFalhas(0);
            usuario.setContaNaoBloqueada(true);
            usuario.setTempoBloqueio(null);
            usuarioRepository.save(usuario);
        }
    }

    public boolean destravarSeTempoExpirou(Usuario usuario) {
        if (usuario.getTempoBloqueio() == null) return false;

        LocalDateTime tempoLiberacao = usuario.getTempoBloqueio().plusMinutes(TEMPO_BLOQUEIO_MINUTOS);

        if (LocalDateTime.now().isAfter(tempoLiberacao)) {
            usuario.setContaNaoBloqueada(true);
            usuario.setTempoBloqueio(null);
            usuario.setTentativasFalhas(0);
            usuarioRepository.save(usuario);
            return true;
        }
        return false;
    }
}