package com.usforus.vempraarena.dto;

import com.usforus.vempraarena.entities.TipoIngresso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class EventoDTO {

    @NotBlank(message = "Por favor, preencha todos os campos obrigatórios.")
    private String nome;

    @NotBlank(message = "Por favor, preencha todos os campos obrigatórios.")
    private String local;

    @NotBlank(message = "Por favor, preencha todos os campos obrigatórios.")
    private String descricao;

    @NotNull(message = "Por favor, preencha todos os campos obrigatórios.")
    private LocalDate data;

    @NotNull(message = "Por favor, preencha todos os campos obrigatórios.")
    private LocalTime horario;

    @NotBlank(message = "Por favor, preencha todos os campos obrigatórios.")
    private String categorias;

    private Map<TipoIngresso, Integer> ingressosDisponiveisPorTipo;

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

    public Map<TipoIngresso, Integer> getIngressosDisponiveisPorTipo() {
        return ingressosDisponiveisPorTipo;
    }

    public void setIngressosDisponiveisPorTipo(Map<TipoIngresso, Integer> ingressosDisponiveisPorTipo) {
        this.ingressosDisponiveisPorTipo = ingressosDisponiveisPorTipo;
    }
}