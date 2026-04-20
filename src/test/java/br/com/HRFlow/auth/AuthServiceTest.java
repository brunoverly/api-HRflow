package br.com.HRFlow.auth;

import br.com.HRFlow.auth.dto.CreateLoginDto;
import br.com.HRFlow.auth.dto.LoginRequestDto;
import br.com.HRFlow.exception.AcessoNaoAutorizadoException;
import br.com.HRFlow.exception.DadosDuplicadosException;
import br.com.HRFlow.security.JwtService;
import br.com.HRFlow.usuario.Usuario;
import br.com.HRFlow.usuario.UsuarioRepository;
import br.com.HRFlow.usuario.UsuarioRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private UsuarioRepository repository;

    @Mock
    private BCryptPasswordEncoder encoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthService service;

    private CreateLoginDto createLoginDto() {
        return new CreateLoginDto(
                "teste",
                "teste@email.com",
                "123456",
                "123456");
    }

    private Usuario createUsuario() {
            return Usuario.builder()
                .nome("teste")
                .email("teste@email.com")
                .senha("123456")
                .dataCriacao(LocalDateTime.now())
                .role(UsuarioRole.USER)
                .build();
    }

    private LoginRequestDto createLoginRequestDto() {
        return new LoginRequestDto(
                "teste@email.com",
                "123456"
        );
    }

    @Test
    void deveCriarUmUsuarioComSucesso(){
        //ARRANGE
        CreateLoginDto dto = createLoginDto();

        when(repository.existsByEmail(dto.email())).thenReturn(false);

        //ACT
        when(jwtService.generateToken(any(Usuario.class))).thenReturn("token");
        when(encoder.encode(dto.senha())).thenReturn("senha-criptografada");
        service.create(dto);

        //ASSERT
        verify(repository).existsByEmail(dto.email());
        verify(encoder).encode(dto.senha());
        verify(repository).save(any(Usuario.class));
        verify(jwtService).generateToken(any(Usuario.class));
    }

    @Test
    void deveCriarUmUsuarioComErroDeEmail(){
        //ARRANGE
        CreateLoginDto dto = new CreateLoginDto(
                "teste",
                "teste@email.com",
                "123456",
                "123456");

        when(repository.existsByEmail(dto.email())).thenReturn(true);

        //ACT+ASSERT
        assertThrows(DadosDuplicadosException.class, () -> service.create(dto));
        verify(repository).existsByEmail(dto.email());
        verify(repository, never()).save(any());
    }

    @Test
    void deveCriarUmUsuarioComErroDeSenha(){
        //ARRANGE
        CreateLoginDto dto = new CreateLoginDto(
                "teste",
                "teste@email.com",
                "123456",
                "1234568");

        //ACT+ASSERT
        assertThrows(IllegalArgumentException.class, () -> service.create(dto));
        verify(repository, never()).save(any());
    }

    @Test
    void deveFazerLoginComSucesso(){
        //ARRANGE
        LoginRequestDto dto = createLoginRequestDto();
        Usuario usuario = createUsuario();

        when(repository.findByEmail(dto.email())).thenReturn(Optional.of(usuario));
        when(encoder.matches(dto.senha(), usuario.getSenha())).thenReturn(true);
        when(jwtService.generateToken(usuario)).thenReturn("token");

        //ACT
        service.login(dto);

        //ASSERT
        verify(repository).findByEmail(dto.email());
        verify(jwtService).generateToken(usuario);
    }

    @Test
    void deveFazerLoginComErroDeEmail(){
        //ARRANGE
        LoginRequestDto dto = createLoginRequestDto();

        when(repository.findByEmail(dto.email())).thenReturn(Optional.empty());

        //ACT+ARRANGE
        assertThrows(AcessoNaoAutorizadoException.class, () -> service.login(dto));
        verify(repository).findByEmail(dto.email());
    }

    @Test
    void deveFazerLoginComErroDeSenha(){
        //ARRANGE
        LoginRequestDto dto = createLoginRequestDto();
        Usuario usuario = createUsuario();

        when(repository.findByEmail(dto.email())).thenReturn(Optional.of(usuario));
        when(encoder.matches(dto.senha(), usuario.getSenha())).thenReturn(false);

        //ACT+ASSERT
        assertThrows(AcessoNaoAutorizadoException.class, () -> service.login(dto));
        verify(repository).findByEmail(dto.email());
        verify(encoder).matches(dto.senha(), usuario.getSenha());
    }
}
