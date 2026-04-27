package com.usforus.vempraarena.service;
import com.usforus.vempraarena.dto.UsuarioCadastroDTO;
import com.usforus.vempraarena.entities.Usuario;
import com.usforus.vempraarena.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    SegurancaLoginService segurancaLoginService;

    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void cadastrarUsuario(UsuarioCadastroDTO dto) throws Exception {

        if (repository.existsByEmail(dto.getEmail()) || repository.existsByCpf(dto.getCpf())) {
            throw new Exception("O e-mail ou CPF informado(s) já está cadastrado na nossa base de dados.");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setName(dto.getNome());
        novoUsuario.setCpf(dto.getCpf());
        novoUsuario.setEmail(dto.getEmail());
        novoUsuario.setPassword(passwordEncoder.encode(dto.getPassword()));

        //aplicar 50 moedinhas

        novoUsuario.setSaldoMoedas(50);

        repository.save(novoUsuario);
    }

    public Usuario buscarPorEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = repository.findByEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }

        if (!usuario.isAccountNonLocked()) {
            segurancaLoginService.destravarSeTempoExpirou(usuario);
        }

        return new org.springframework.security.core.userdetails.User(
                usuario.getEmail(),
                usuario.getPassword(),
                true, // enabled (conta ativa?)
                true, // accountNonExpired (conta não expirou?)
                true, // credentialsNonExpired (senha não expirou?)
                usuario.isAccountNonLocked(), // 🚨 AGORA O SPRING LÊ O BLOQUEIO DO BANCO!
                List.of(new SimpleGrantedAuthority(usuario.getRole()))
        );
    }
}
