package com.usforus.vempraarena.entities;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String password;

    private Integer saldoMoedas = 0;

    private String role = "USER";

    @Column(name = "tentativas_falhas")
    private int tentativasFalhas = 0;

    @Column(name = "conta_nao_bloqueada")
    private boolean contaNaoBloqueada = true;

    @Column(name = "tempo_bloqueio")
    private java.time.LocalDateTime tempoBloqueio;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() {
        return this.contaNaoBloqueada;
    }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSaldoMoedas() {
        return saldoMoedas;
    }

    public void setSaldoMoedas(Integer saldoMoedas) {
        this.saldoMoedas = saldoMoedas;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public int getTentativasFalhas() {
        return tentativasFalhas;
    }

    public void setTentativasFalhas(int tentativasFalhas) {
        this.tentativasFalhas = tentativasFalhas;
    }

    public boolean isContaNaoBloqueada() {
        return contaNaoBloqueada;
    }

    public void setContaNaoBloqueada(boolean contaNaoBloqueada) {
        this.contaNaoBloqueada = contaNaoBloqueada;
    }

    public LocalDateTime getTempoBloqueio() {
        return tempoBloqueio;
    }

    public void setTempoBloqueio(LocalDateTime tempoBloqueio) {
        this.tempoBloqueio = tempoBloqueio;
    }
}
