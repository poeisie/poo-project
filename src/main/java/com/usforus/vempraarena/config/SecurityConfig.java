package com.usforus.vempraarena.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // 1. A MÁGICA ACONTECE AQUI: Liberando as rotas públicas
                        .requestMatchers("/cadastro", "/css/**", "/js/**", "/images/**", "/error").permitAll()

                        // 2. Qualquer outra rota (como /home) vai exigir login
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login") // Diz ao Spring que temos uma tela customizada de login
                        .defaultSuccessUrl("/home", true) // Pra onde ir depois de logar com sucesso
                        .permitAll() // Libera a própria tela de login para todos
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }

    // Configuração do codificador de senhas (BCrypt)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}