package com.usforus.vempraarena.service;

import com.usforus.vempraarena.dto.EventoDTO;
import com.usforus.vempraarena.entities.Evento;
import com.usforus.vempraarena.repository.EventoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EventoService {

    private final EventoRepository repository;

    public EventoService(EventoRepository repository){
        this.repository = repository;
    }

    public List<Evento> listarEvento(){
        return repository.findByDataGreaterThanEqualOrderByDataAsc(LocalDate.now());
    }

    public List<Evento> buscarPorCategoria(String categoria){ return repository.findByCategoriasContaining(categoria); }

    public List<Evento> listarEventosSemana(){
        LocalDate hoje = LocalDate.now();
        LocalDate inicioSemana = hoje.with(DayOfWeek.MONDAY);
        LocalDate fimSemana = hoje.with(DayOfWeek.SUNDAY);
        return repository.findByDataBetweenOrderByDataAsc(inicioSemana, fimSemana);
    }

    public void criarEvento(EventoDTO dto) throws Exception{
        if(dto.getData().isBefore(LocalDate.now())){
            throw new Exception("A data do evento não pode ser anterior à data atual.");
        }
        if(dto.getHorario().isBefore(LocalTime.now()) && dto.getData().isEqual(LocalDate.now())){
            throw new Exception("O horário do evento não pode ser anterior ao horário atual.");
        }

        Evento novoEvento = new Evento();
        novoEvento.setNome(dto.getNome());
        novoEvento.setDescricao(dto.getDescricao());
        novoEvento.setLocal(dto.getLocal());
        novoEvento.setData(dto.getData());
        novoEvento.setHorario(dto.getHorario());
        novoEvento.setCategorias(dto.getCategorias());

        Map<com.usforus.vempraarena.entities.TipoIngresso, Integer> ingressos = dto.getIngressosDisponiveisPorTipo();
        if (ingressos == null) {
            ingressos = new HashMap<>();
        }
        novoEvento.setIngressosDisponiveisPorTipo(ingressos);

        novoEvento.setIngressosDisponiveisPorTipo(dto.getIngressosDisponiveisPorTipo());
        novoEvento.setPrecoIngresso(dto.getPrecoIngresso());

        repository.save(novoEvento);
    }

    public Evento buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Evento não encontrado com o ID: " + id));
    }
}