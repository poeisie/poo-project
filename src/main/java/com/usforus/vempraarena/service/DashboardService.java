package com.usforus.vempraarena.service;


import com.usforus.vempraarena.dto.DashboardEstatisticoDTO;
import com.usforus.vempraarena.entities.Evento;
import com.usforus.vempraarena.entities.Ingresso;
import com.usforus.vempraarena.entities.StatusEvento;
import com.usforus.vempraarena.repository.EventoRepository;
import com.usforus.vempraarena.repository.IngressoRepository;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;


@Service
public class DashboardService {

    private final EventoRepository eventoRepository;
    private final IngressoRepository  ingressoRepository;


    public DashboardService(EventoRepository eventoRepository, IngressoRepository ingressoRepository) {
        this.eventoRepository = eventoRepository;
        this.ingressoRepository = ingressoRepository;
    }

    public DashboardEstatisticoDTO calcularMetricas(){

        List<Evento> eventosConfirmados = eventoRepository.findByStatus(StatusEvento.CONFIRMADO);
        int totalEventosConfirmados = eventosConfirmados.size();

        List<Long> eventoId = eventosConfirmados.stream().map(Evento::getId).collect(Collectors.toList());
        List<Ingresso> ingressos = eventoId.isEmpty() ? Collections.emptyList() : ingressoRepository.findByEventoIds(eventoId);

        int totalIngressosVendidos = ingressos.stream().mapToInt(Ingresso::getQuantidade).sum();
        double receitaTotal = ingressos.stream().mapToDouble(Ingresso::getPrecoTotal).sum();

        Map<Long, Integer> vendidosPorEvento = new HashMap<>();
        for(Evento evento : eventosConfirmados){
            vendidosPorEvento.put(evento.getId(), 0);
        }
        for(Ingresso ingresso : ingressos){
            Long idEvento = ingresso.getEvento().getId();
            vendidosPorEvento.merge(idEvento, ingresso.getQuantidade(), Integer::sum);
        }

        List<Integer> serie = new ArrayList<>(vendidosPorEvento.values());
        double media = calcularMedia(serie);
        double variancia = calcularVariancia(serie, media);
        double desvioPadrao = Math.sqrt(variancia);

        String modaCategoria = calcularModaCategoria(eventosConfirmados);

        DashboardEstatisticoDTO dto = new DashboardEstatisticoDTO();
        dto.setMediaIngressosPorEvento(media);
        dto.setModaCategoria(modaCategoria);
        dto.setDesvioPadraoIngressos(desvioPadrao);
        dto.setTotalEventosConfirmados(totalEventosConfirmados);
        dto.setTotalIngressosVendidos(totalIngressosVendidos);
        dto.setReceitaTotal(receitaTotal);

        return dto;

    }

    private double calcularMedia(List<Integer> valores){

        if(valores == null || valores.isEmpty()){
            return 0.0;
        }

        return valores.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }


    private double calcularVariancia(List<Integer> valores, double media) {

        if (valores == null || valores.isEmpty()) return 0.0;

        double soma = 0.0;

        for (Integer valor : valores) {
            double diferenca = valor - media;
            soma += diferenca * diferenca;
        }

        return soma / valores.size();
    }


    private String calcularModaCategoria(List<Evento> eventos) {

        if (eventos == null || eventos.isEmpty()) return "Sem dados";

        Map<String, Integer> frequencias = new HashMap<>();

        for (Evento evento : eventos) {
            String categoria = evento.getCategorias();
            if (categoria == null || categoria.isBlank()) continue;

            frequencias.merge(categoria.trim(), 1, Integer::sum);
        }

        if (frequencias.isEmpty()) return "Sem dados";

        return frequencias.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Sem dados");
    }

}
