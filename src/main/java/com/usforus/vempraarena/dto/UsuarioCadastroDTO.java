package com.usforus.vempraarena.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UsuarioCadastroDTO {
    @NotBlank(message = "Por favor, preencha todos os campos obrigatórios.")
    private String nome;

    @NotBlank(message = "Por favor, preencha todos os campos obrigatórios.")
    @Email(message = "Formato de e-mail inválido.")
    private String email;

    @NotBlank(message = "Por favor, preencha todos os campos obrigatórios.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$",
            message = "A senha deve ter no mínimo 8 caracteres, incluindo letras maiúsculas, minúsculas, números e caracteres especiais (ex: @, #, $, !, &).")
    private String password;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
