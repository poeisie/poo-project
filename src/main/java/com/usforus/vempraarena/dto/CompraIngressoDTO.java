package com.usforus.vempraarena.dto;

import com.usforus.vempraarena.entities.TipoIngresso;

public class CompraIngressoDTO {

    private Long eventoId;
    private Integer quantidade;
    private TipoIngresso tipoIngresso;
    private String formaPagamento;

    public CompraIngressoDTO() {}

    public Long getEventoId() { return eventoId; }
    public void setEventoId(Long eventoId) { this.eventoId = eventoId; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public TipoIngresso getTipoIngresso() { return tipoIngresso; }
    public void setTipoIngresso(TipoIngresso tipoIngresso) { this.tipoIngresso = tipoIngresso; }

    public String getFormaPagamento() { return formaPagamento; }
    public void setFormaPagamento(String formaPagamento) { this.formaPagamento = formaPagamento; }
}