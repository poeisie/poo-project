package com.usforus.vempraarena.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "eventos")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private LocalTime horario;

    @Column(nullable = false)
    private String local;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String categorias;

    @ElementCollection
    @CollectionTable(name = "evento_ingressos_disponiveis",
                     joinColumns = @JoinColumn(name = "evento_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "quantidade")
    private Map<TipoIngresso, Integer> ingressosDisponiveisPorTipo = new HashMap<>();

    @Column(nullable = false)
    private Integer precoIngresso = 0;

    public Map<TipoIngresso, Integer> getIngressosDisponiveisPorTipo() {
        return ingressosDisponiveisPorTipo;
    }

    public void setIngressosDisponiveisPorTipo(Map<TipoIngresso, Integer> ingressosDisponiveisPorTipo) {
        this.ingressosDisponiveisPorTipo = ingressosDisponiveisPorTipo;
    }

    public int getQuantidadeDisponivelPorTipo(TipoIngresso tipo) {
        return ingressosDisponiveisPorTipo.getOrDefault(tipo, 0);
    }

    public boolean reduzirEstoque(TipoIngresso tipo, int quantidade) {
        Integer disponivel = ingressosDisponiveisPorTipo.get(tipo);
        if (disponivel != null && disponivel >= quantidade) {
            ingressosDisponiveisPorTipo.put(tipo, disponivel - quantidade);
            return true;
        }
        return false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategorias() {
        return categorias;
    }

    public void setCategorias(String categorias) {
        this.categorias = categorias;
    }

    public Integer getPrecoIngresso() {
        return precoIngresso;
    }

    public void setPrecoIngresso(Integer precoIngresso) {
        this.precoIngresso = precoIngresso;
    }
}

