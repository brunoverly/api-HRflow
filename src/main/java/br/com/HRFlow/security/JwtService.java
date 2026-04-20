package br.com.HRFlow.security;

import br.com.HRFlow.exception.ErroAoTentarCriarTokenException;
import br.com.HRFlow.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtService {
    @Value("${key.token-secret}")
    private String tokenSecret;

    public String generateToken(Usuario usuario) throws ErroAoTentarCriarTokenException {
        try{
            Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
            return JWT.create()
                    .withIssuer("HRFlow")
                    .withSubject(usuario.getEmail())
                    .withClaim("role", usuario.getRole().name())
                    .withExpiresAt(generateExpiresDate())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new ErroAoTentarCriarTokenException("Erro ao tentar criar o bearer token");
        }
    }

    private Instant generateExpiresDate() {
        //retorna um LocalDateTime com fuso horário de Brasília
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
