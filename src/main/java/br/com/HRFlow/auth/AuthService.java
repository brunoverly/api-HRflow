package br.com.HRFlow.auth;

import br.com.HRFlow.auth.dto.CreateLoginDto;
import br.com.HRFlow.auth.dto.LoginResonseDto;
import br.com.HRFlow.security.JwtService;
import br.com.HRFlow.usuario.Usuario;
import br.com.HRFlow.usuario.UsuarioRepository;
import org.apache.coyote.BadRequestException;
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


    public LoginResonseDto create(CreateLoginDto dto) throws BadRequestException {
        if(!dto.senha().equals(dto.confirmarSenha())) {
            throw new BadRequestException("Senhas informadas não coincidem");
        }
        if(repository.existsByEmail(dto.email())) {
            throw new BadRequestException("Email informado já em uso");
        }

        Usuario usuario = Usuario.builder()
                .nome(dto.nome())
                .email(dto.email())
                .senha(encoder.encode(dto.senha()))
                .build();

        repository.save(usuario);

        return new LoginResonseDto(dto.nome(), dto.email(), jwtService.generateToken(usuario));
    }
}
