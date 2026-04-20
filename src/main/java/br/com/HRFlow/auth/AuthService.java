package br.com.HRFlow.auth;

import br.com.HRFlow.auth.dto.CreateLoginDto;
import br.com.HRFlow.auth.dto.LoginRequestDto;
import br.com.HRFlow.auth.dto.LoginResponseDto;
import br.com.HRFlow.exception.AcessoNaoAutorizadoException;
import br.com.HRFlow.exception.DadosDuplicadosException;
import br.com.HRFlow.security.JwtService;
import br.com.HRFlow.usuario.Usuario;
import br.com.HRFlow.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private JwtService jwtService;


    public LoginResponseDto create(CreateLoginDto dto){
        if(!dto.senha().equals(dto.confirmarSenha())) {
            throw new IllegalArgumentException("Senhas informadas não coincidem");
        }
        if(repository.existsByEmail(dto.email())) {
            throw new DadosDuplicadosException("Email informado não disponível");
        }

        Usuario usuario = Usuario.builder()
                .nome(dto.nome())
                .email(dto.email())
                .senha(encoder.encode(dto.senha()))
                .build();

        repository.save(usuario);

        return new LoginResponseDto(dto.nome(), dto.email(), jwtService.generateToken(usuario));
    }

    public LoginResponseDto login(LoginRequestDto dto){
        Usuario usuario = repository.findByEmail(dto.email())
                .orElse(null);

        if(usuario == null || !encoder.matches(dto.senha(), usuario.getSenha())) {
            throw new AcessoNaoAutorizadoException("Credenciais inválidas");
        }

        return new LoginResponseDto(usuario.getNome(), usuario.getEmail(), jwtService.generateToken(usuario));
    }
}
