package com.usforus.vempraarena.service;

import com.usforus.vempraarena.dto.EventoDTO;
import com.usforus.vempraarena.entities.Evento;
import com.usforus.vempraarena.repository.EventoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.util.List;

@Service
public class EventoService {

    private final EventoRepository repository;

    public EventoService(EventoRepository repository){
        this.repository = repository;
    }

    public List<Evento> listarEvento(){
        return repository.findByDataGreaterThanEqualOrderByDataAsc(LocalDate.now());
    }

    public List<Evento> listarEventosSemana(){
        LocalDate hoje = LocalDate.now();
        LocalDate inicioSemana = hoje.with(DayOfWeek.MONDAY);
        LocalDate fimSemana = hoje.with(DayOfWeek.SUNDAY);
        return repository.findByDataBetweenOrderByDataAsc(inicioSemana, fimSemana);
    }

    // ver como lista todos os eventos(até os que passaram) pra admin

    public void criarEvento(EventoDTO dto) throws Exception{

        if(dto.getData().isBefore(LocalDate.now())){
            throw new Exception("A data do evento não pode ser anterior à data atual.");
        }

        if(dto.getHorario().isBefore(java.time.LocalTime.now()) && dto.getData().isEqual(LocalDate.now())){
            throw new Exception("O horário do evento não pode ser anterior ao horário atual.");
        }

        if(dto.getCapacidadeMaximaParticipantes() <= 0){
            throw new Exception("A capacidade máxima de participantes deve ser um número positivo.");
        }

        Evento novoEvento = new Evento();
        novoEvento.setNome(dto.getNome());
        novoEvento.setDescricao(dto.getDescricao());
        novoEvento.setLocal(dto.getLocal());
        novoEvento.setData(dto.getData());
        novoEvento.setHorario(dto.getHorario());
        novoEvento.setCapacidadeMaximaParticipantes(dto.getCapacidadeMaximaParticipantes());
        novoEvento.setCategorias(dto.getCategorias());

        repository.save(novoEvento);



    }




























}

