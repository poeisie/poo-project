package com.usforus.vempraarena.entities;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "ingressos")
public class Ingresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuarioId")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "eventoId")
    private Evento evento;

    @Column(nullable = false)
    private LocalDateTime dataCompra;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private Integer precoUnitario;

    @Column(nullable = false)
    private Integer precoTotal;


    public Ingresso(){

    }

    public Ingresso(Usuario usuario, Evento evento, Integer quantidade, Integer precoUnitario) {
        this.usuario = usuario;
        this.evento = evento;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.precoTotal = precoUnitario*quantidade;
        this.dataCompra = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Integer precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Integer getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(Integer precoTotal) {
        this.precoTotal = precoTotal;
    }
}
