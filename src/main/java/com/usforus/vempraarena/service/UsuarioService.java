package com.usforus.vempraarena.service;
import com.usforus.vempraarena.dto.UsuarioCadastroDTO;
import com.usforus.vempraarena.dto.UsuarioCadastroProdutorDTO;
import com.usforus.vempraarena.entities.Usuario;
import com.usforus.vempraarena.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;


    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void cadastrarUsuario(UsuarioCadastroDTO dto) throws Exception {

        if (repository.existsByEmail(dto.getEmail())) {
            throw new Exception("O e-mail informado já está cadastrado na nossa base de dados.");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setName(dto.getNome());
        novoUsuario.setEmail(dto.getEmail());
        novoUsuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        novoUsuario.setRole("USER");

        //aplicar 50 moedinhas

        novoUsuario.setSaldoMoedas(50);

        repository.save(novoUsuario);
    }

    public void cadastrarProdutorCpf(Long usuarioId, UsuarioCadastroProdutorDTO dto) throws Exception {

        boolean cpfVazio = dto.getCpf() == null || dto.getCpf().isBlank();

        if(cpfVazio){
            throw new Exception("O CPF é obrigatório para este cadastro.");
        }

        if(repository.existsByCpf(dto.getCpf())){
            throw new Exception("O CPF informado já está cadastrado na nossa base de dados.");
        }

        Usuario usuario = repository.findById(usuarioId)
                .orElseThrow(() -> new Exception("Usuário não encontrado."));


        usuario.setCpf(dto.getCpf());
        usuario.setCnpj(null);
        usuario.setRazaoSocial(null);
        usuario.setInscricaoMunicipal(null);
        usuario.setRole("PROD");

        repository.save(usuario);

    }

    public void cadastrarProdutorCnpj(Long usuarioId, UsuarioCadastroProdutorDTO dto) throws Exception {

        boolean cnpjVazio = dto.getCnpj() == null || dto.getCnpj().isBlank();

        if (cnpjVazio) {
            throw new Exception("O CNPJ é obrigatório para este cadastro.");
        }

        if (repository.existsByCnpj(dto.getCnpj())) {
            throw new Exception("O CNPJ informado já está cadastrado na nossa base de dados.");
        }

        Usuario usuario = repository.findById(usuarioId)
                .orElseThrow(() -> new Exception("Usuário não encontrado."));

        usuario.setCnpj(dto.getCnpj());
        usuario.setCpf(null);
        usuario.setRazaoSocial(dto.getRazaoSocial());
        usuario.setInscricaoMunicipal(dto.getInscricaoMunicipal());
        usuario.setRole("PROD");

        repository.save(usuario);

    }

    public Usuario buscarPorEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = repository.findByEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }

        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getPassword())
                .roles(usuario.getRole())
                .build();

    }
}