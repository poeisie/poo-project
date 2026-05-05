package com.usforus.vempraarena.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.usforus.vempraarena.dto.UsuarioCadastroProdutorDTO;
import com.usforus.vempraarena.entities.Evento;
import com.usforus.vempraarena.entities.Usuario;
import com.usforus.vempraarena.repository.EventoRepository;
import com.usforus.vempraarena.repository.UsuarioRepository;
import com.usforus.vempraarena.service.UsuarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/produtor")
public class ProdutorController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final EventoRepository eventoRepository;

    public ProdutorController(UsuarioService usuarioService, UsuarioRepository usuarioRepository, EventoRepository eventoRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
        this.eventoRepository = eventoRepository;
    }

    @GetMapping("/cadastro")
    public String formularioCPFOuCNPJ(Model model, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }

        Usuario usuario = usuarioRepository.findByEmail(authentication.getName());
        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("usuarioInicial", resolverIniciais(authentication));
        return "cadastro-produtor";
    }

    @GetMapping("/meus-eventos")
    public String exibirMeusEventos(Model model, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }

        Usuario usuario = usuarioRepository.findByEmail(authentication.getName());
        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("usuarioInicial", resolverIniciais(authentication));

        List<Evento> eventos = eventoRepository.findByDataGreaterThanEqualOrderByDataAsc(java.time.LocalDate.now());

        // Descomentar caso queira mostrar apenas eventos mockados para visualização dos cards
        // List<Evento> eventos = List.of(
        //     criarEventoMock(1L, "Show de Rock",          LocalDate.now().plusDays(3),  LocalTime.of(20, 0),  "Arena Pernambuco, Recife",   "Uma noite incrível de rock nacional e internacional.", "Música",       500),
        //     criarEventoMock(2L, "Workshop de Java",      LocalDate.now().plusDays(7),  LocalTime.of(9, 0),   "Arena Pernambuco, Recife",                    "Aprenda Spring Boot do zero ao deploy.",               "Tecnologia",   100),
        //     criarEventoMock(3L, "Feira de Tecnologia",   LocalDate.now().plusDays(15), LocalTime.of(10, 0),  "Arena Pernambuco, Recife",  "Os maiores lançamentos de tecnologia do ano.",        "Tecnologia",   2000),
        //     criarEventoMock(4L, "Festival de Comida",    LocalDate.now().plusDays(20), LocalTime.of(12, 0),  "Arena Pernambuco, Recife",  "Sabores do mundo reunidos em um só lugar.",           "Gastronomia",  800),
        //     criarEventoMock(5L, "Maratona de Séries",    LocalDate.now().plusDays(30), LocalTime.of(18, 0),  "Arena Pernambuco, Recife",  "Assista às melhores séries em uma maratona épica.",   "Entretenimento", 300),
        //     criarEventoMock(6L, "Palestra de Empreendedorismo", LocalDate.now().plusDays(5), LocalTime.of(14, 0), "Arena Pernambuco, Recife", "Dicas e insights para quem quer empreender.",         "Negócios",     150)   
        // );
        model.addAttribute("eventos", eventos);
        return "meus-eventos";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }

        Usuario usuario = usuarioRepository.findByEmail(authentication.getName());
        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("usuarioInicial", resolverIniciais(authentication));
        return "dashboard";
    }

    @GetMapping("cadastro/cpf")
    public String formularioCpf(Model model) {
        model.addAttribute("usuarioCadastroProdutorDTO", new UsuarioCadastroProdutorDTO());
        return "cadastro-cpf";
    }

    @PostMapping("cadastro/cpf")
    public String processarCpf(
            @Valid @ModelAttribute("usuarioCadastroProdutorDTO") UsuarioCadastroProdutorDTO dto,
            BindingResult result,
            Authentication authentication,
            Model model) {

        if (result.hasErrors()) {
            return "cadastro-cpf";
        }

        try {
            Usuario usuario = usuarioRepository.findByEmail(authentication.getName());
            usuarioService.cadastrarProdutorCpf(usuario.getId(), dto);
            return "redirect:/home?produtor";
        } catch (Exception e) {
            model.addAttribute("erroDuplicidade", e.getMessage());
            return "cadastro-cpf";
        }
    }

    @GetMapping("cadastro/cnpj")
    public String formularioCnpj(Model model) {
        model.addAttribute("usuarioCadastroProdutorDTO", new UsuarioCadastroProdutorDTO());
        return "cadastro-cnpj";
    }

    @PostMapping("cadastro/cnpj")
    public String processarCnpj(
            @Valid @ModelAttribute("usuarioCadastroProdutorDTO") UsuarioCadastroProdutorDTO dto,
            BindingResult result,
            Authentication authentication,
            Model model) {

        if (result.hasErrors()) {
            return "cadastro-cnpj";
        }

        try {
            Usuario usuario = usuarioRepository.findByEmail(authentication.getName());
            usuarioService.cadastrarProdutorCnpj(usuario.getId(), dto);
            return "redirect:/home?produtor";
        } catch (Exception e) {
            model.addAttribute("erroDuplicidade", e.getMessage());
            return "cadastro-cnpj";
        }
    }

    private String resolverIniciais(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            Usuario usuario = usuarioRepository.findByEmail(authentication.getName());
            if (usuario != null && usuario.getName() != null) {
                return criarIniciais(usuario.getName());
            }
        }
        return "UA";
    }

    private String criarIniciais(String nomeCompleto) {
        if (nomeCompleto == null || nomeCompleto.isBlank()) return "UA";

        String[] partes = nomeCompleto.trim().split("\\s+");
        if (partes.length == 1) return partes[0].substring(0, 1).toUpperCase();

        return partes[0].substring(0, 1).toUpperCase()
             + partes[partes.length - 1].substring(0, 1).toUpperCase();
    }

    // Descomentar caso queira mostrar apenas eventos mockados para visualização dos cards
    // private Evento criarEventoMock(Long id, String nome, LocalDate data, LocalTime horario,
    //                             String local, String descricao, String categorias,
    //                             int capacidadeMaximaParticipantes) {
    //     Evento e = new Evento();
    //     e.setId(id);
    //     e.setNome(nome);
    //     e.setData(data);
    //     e.setHorario(horario);
    //     e.setLocal(local);
    //     e.setDescricao(descricao);
    //     e.setCategorias(categorias);
    //     e.setCapacidadeMaximaParticipantes(capacidadeMaximaParticipantes);
    //     return e;
    // }

}
