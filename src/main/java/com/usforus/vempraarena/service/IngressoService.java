package com.usforus.vempraarena.service;

import com.usforus.vempraarena.dto.CompraIngressoDTO;
import com.usforus.vempraarena.entities.Evento;
import com.usforus.vempraarena.entities.Ingresso;
import com.usforus.vempraarena.entities.TipoIngresso;
import com.usforus.vempraarena.entities.Usuario;
import com.usforus.vempraarena.repository.EventoRepository;
import com.usforus.vempraarena.repository.IngressoRepository;
import com.usforus.vempraarena.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IngressoService {

    private final IngressoRepository ingressoRepository;
    private final EventoRepository eventoRepository;
    private final UsuarioRepository usuarioRepository;

    public IngressoService(IngressoRepository ingressoRepository, EventoRepository eventoRepository, UsuarioRepository usuarioRepository) {
        this.ingressoRepository = ingressoRepository;
        this.eventoRepository = eventoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public void realizarCompra(CompraIngressoDTO dto, String emailUsuario) throws Exception {

        Evento evento = eventoRepository.findById(dto.getEventoId())
                .orElseThrow(() -> new Exception("Evento não encontrado."));

        Usuario usuario = usuarioRepository.findByEmail(emailUsuario);

        if (dto.getQuantidade() == null || dto.getQuantidade() <= 0) {
            throw new Exception("A quantidade deve ser de pelo menos 1 ingresso.");
        }

        TipoIngresso tipo = dto.getTipoIngresso();
        int quantidade = dto.getQuantidade();
        int disponivel = evento.getQuantidadeDisponivelPorTipo(tipo);
        if (quantidade > disponivel) {
            throw new Exception("Estoque insuficiente para o tipo " + tipo + "! Disponível: " + disponivel);
        }

        int precoUnitarioCobrado = evento.getPrecoIngresso();
        if (tipo == TipoIngresso.MEIA) {
            precoUnitarioCobrado = evento.getPrecoIngresso() / 2;
        }

        int precoTotalCompra = quantidade * precoUnitarioCobrado;

        if (usuario.getSaldoMoedas() < precoTotalCompra) {
            throw new Exception("Saldo insuficiente! A compra custa " + precoTotalCompra + " moedas, mas você tem " + usuario.getSaldoMoedas() + ".");
        }
        usuario.setSaldoMoedas(usuario.getSaldoMoedas() - precoTotalCompra);
        usuarioRepository.save(usuario);

        Ingresso novoIngresso = new Ingresso(usuario, evento, quantidade, precoUnitarioCobrado, tipo);
        ingressoRepository.save(novoIngresso);

        boolean atualizou = evento.reduzirEstoque(tipo, quantidade);
        if (!atualizou) {
            throw new Exception("Erro ao atualizar o estoque do evento.");
        }
        eventoRepository.save(evento);
    }

    public List<Ingresso> listarPorUsuario(Long usuarioId){
        return ingressoRepository.findByUsuarioId(usuarioId);
    }
}