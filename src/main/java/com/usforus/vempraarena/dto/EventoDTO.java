package com.usforus.vempraarena.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalTime;
import java.time.LocalDate;

public class EventoDTO {

    @NotBlank(message = "Por favor, preencha todos os campos obrigatórios.")
    private String nome;

    @NotBlank(message = "Por favor, preencha todos os campos obrigatórios.")
    private String local;

    @NotBlank(message = "Por favor, preencha todos os campos obrigatórios.")
    private String descricao;

    @NotBlank(message = "Por favor, preencha todos os campos obrigatórios.")
    private LocalDate data;

    @NotBlank(message = "Por favor, preencha todos os campos obrigatórios.")
    private LocalTime horario;

    @NotBlank(message = "Por favor, preencha todos os campos obrigatórios.")
    private int capacidadeMaximaParticipantes;

    @NotBlank(message = "Por favor, preencha todos os campos obrigatórios.")
    private String categorias;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public int getCapacidadeMaximaParticipantes() {
        return capacidadeMaximaParticipantes;
    }

    public void setCapacidadeMaximaParticipantes(int capacidadeMaximaParticipantes) {
        this.capacidadeMaximaParticipantes = capacidadeMaximaParticipantes;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getCategorias() {
        return categorias;
    }

    public void setCategorias(String categorias) {
        this.categorias = categorias;
    }

}
