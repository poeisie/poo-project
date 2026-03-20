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
    private String cpf;

    @NotBlank(message = "Por favor, preencha todos os campos obrigatórios.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "A senha deve ter pelo menos 8 caracteres, contendo letras e números e não pode conter simbolos especiais.")
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
