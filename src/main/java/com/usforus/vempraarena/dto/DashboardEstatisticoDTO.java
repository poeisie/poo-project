package com.usforus.vempraarena.dto;

public class DashboardEstatisticoDTO {

    private double mediaIngressosPorEvento;
    private double desvioPadraoIngressos;
    private String modaCategoria;

    private int totalEventosConfirmados;
    private int totalIngressosVendidos;
    private double receitaTotal;


    public double getMediaIngressosPorEvento() {
        return mediaIngressosPorEvento;
    }

    public void setMediaIngressosPorEvento(double mediaIngressosPorEvento) {
        this.mediaIngressosPorEvento = mediaIngressosPorEvento;
    }

    public double getDesvioPadraoIngressos() {
        return desvioPadraoIngressos;
    }

    public void setDesvioPadraoIngressos(double desvioPadraoIngressos) {
        this.desvioPadraoIngressos = desvioPadraoIngressos;
    }

    public String getModaCategoria() {
        return modaCategoria;
    }

    public void setModaCategoria(String modaCategoria) {
        this.modaCategoria = modaCategoria;
    }

    public int getTotalEventosConfirmados() {
        return totalEventosConfirmados;
    }

    public void setTotalEventosConfirmados(int totalEventosConfirmados) {
        this.totalEventosConfirmados = totalEventosConfirmados;
    }

    public int getTotalIngressosVendidos() {
        return totalIngressosVendidos;
    }

    public void setTotalIngressosVendidos(int totalIngressosVendidos) {
        this.totalIngressosVendidos = totalIngressosVendidos;
    }

    public double getReceitaTotal() {
        return receitaTotal;
    }

    public void setReceitaTotal(double receitaTotal) {
        this.receitaTotal = receitaTotal;
    }
}
