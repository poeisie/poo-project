package com.usforus.vempraarena.service;

import com.usforus.vempraarena.dto.EventoDTO;
import com.usforus.vempraarena.entities.Evento;
import com.usforus.vempraarena.entities.StatusEvento;
import com.usforus.vempraarena.entities.Usuario;
import com.usforus.vempraarena.repository.EventoRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class EventoService {

    private final EventoRepository repository;

    public EventoService(EventoRepository repository){
        this.repository = repository;
    }

    public List<Evento> listarEvento(){
        return repository.findByDataGreaterThanEqualOrderByDataAsc(LocalDate.now());
    }

    public List<Evento> listarEventosProdutor(Long produtorId){ return repository.findByProdutorId(produtorId);}

    public List<Evento> buscarPorCategoria(String categoria){ return repository.findByCategoriasContaining(categoria); }

    public List<Evento> listarEventosSemana(){
        LocalDate hoje = LocalDate.now();
        LocalDate inicioSemana = hoje.with(DayOfWeek.MONDAY);
        LocalDate fimSemana = hoje.with(DayOfWeek.SUNDAY);
        return repository.findByDataBetweenOrderByDataAsc(inicioSemana, fimSemana);
    }

    public List<Evento> listarEventosSemanaAprovados(){
        LocalDate hoje = LocalDate.now();
        LocalDate inicioSemana = hoje.with(DayOfWeek.MONDAY);
        LocalDate fimSemana = hoje.with(DayOfWeek.SUNDAY);
        List<Evento> eventosSemana = repository.findByDataBetweenOrderByDataAsc(inicioSemana, fimSemana);
        return eventosSemana.stream()
                .filter(evento -> evento.getStatus() == StatusEvento.CONFIRMADO)
                .toList();
    }


    public void criarEvento(EventoDTO dto, Usuario produtor) throws Exception{
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
        novoEvento.setSobreProdutor(dto.getSobreProdutor());

        novoEvento.setProdutor(produtor);

        Map<com.usforus.vempraarena.entities.TipoIngresso, Integer> ingressos = dto.getIngressosDisponiveisPorTipo();
        if (ingressos == null) {
            ingressos = new HashMap<>();
        }
        novoEvento.setIngressosDisponiveisPorTipo(ingressos);

        novoEvento.setIngressosDisponiveisPorTipo(dto.getIngressosDisponiveisPorTipo());
        novoEvento.setPrecoIngresso(dto.getPrecoIngresso());

        if (dto.getImagem() != null && !dto.getImagem().isEmpty()) {
            try {
                String originalFilename = dto.getImagem().getOriginalFilename();
                String safeName = (originalFilename == null) ? "img" : originalFilename.replaceAll("[^a-zA-Z0-9._-]", "_");
                String filename = System.currentTimeMillis() + "_" + UUID.randomUUID() + "_" + safeName;

                Path imagesDir = Paths.get("static", "images", "eventos");
                if (!Files.exists(imagesDir)) {
                    Files.createDirectories(imagesDir);
                }

                Path target = imagesDir.resolve(filename);
                Files.copy(dto.getImagem().getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

                // caminho público usado nas views (static monta em /)
                novoEvento.setImagemPath("/images/eventos/" + filename);
            } catch (IOException e) {
                throw new Exception("Falha ao salvar a imagem do evento: " + e.getMessage());
            }
        }

        repository.save(novoEvento);
    }

    public List<Evento> listarEventosAprovados() {
        return repository.findByStatusOrderByDataAsc(StatusEvento.CONFIRMADO);
    }

    public List<Evento> listarEventosPendentes() {
        return repository.findByStatus(StatusEvento.PENDENTE);
    }

    public void aprovarEvento(Long id) throws Exception {
        Evento evento = repository.findById(id)
                .orElseThrow(() -> new Exception("Evento não encontrado."));

        evento.setStatus(StatusEvento.CONFIRMADO);
        repository.save(evento);
    }

    public void rejeitarEvento(Long id) throws Exception {
        Evento evento = repository.findById(id)
                .orElseThrow(() -> new Exception("Evento não encontrado."));

        evento.setStatus(StatusEvento.REJEITADO);
        repository.save(evento);
    }

    public Evento buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Evento não encontrado com o ID: " + id));
    }
}