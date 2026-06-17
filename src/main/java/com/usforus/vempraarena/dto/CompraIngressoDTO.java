package com.usforus.vempraarena.dto;

public class CompraIngressoDTO {

    private Long eventoId;
    private Integer quantidadeInteira;
    private Integer quantidadeMeia;
    private String formaPagamento;

    public CompraIngressoDTO() {}

    public Long getEventoId() { return eventoId; }
    public void setEventoId(Long eventoId) { this.eventoId = eventoId; }

    public Integer getQuantidadeInteira() { return quantidadeInteira; }
    public void setQuantidadeInteira(Integer quantidadeInteira) { this.quantidadeInteira = quantidadeInteira; }

    public Integer getQuantidadeMeia() { return quantidadeMeia; }
    public void setQuantidadeMeia(Integer quantidadeMeia) { this.quantidadeMeia = quantidadeMeia; }

    public String getFormaPagamento() { return formaPagamento; }
    public void setFormaPagamento(String formaPagamento) { this.formaPagamento = formaPagamento; }

    public int getQuantidadeInteiraOuZero() {
        return quantidadeInteira != null && quantidadeInteira > 0 ? quantidadeInteira : 0;
    }

    public int getQuantidadeMeiaOuZero() {
        return quantidadeMeia != null && quantidadeMeia > 0 ? quantidadeMeia : 0;
    }

    public int getTotalIngressos() {
        return getQuantidadeInteiraOuZero() + getQuantidadeMeiaOuZero();
    }
}
