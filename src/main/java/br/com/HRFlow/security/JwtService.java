package br.com.HRFlow.security;

import br.com.HRFlow.exception.ErroAoTentarCriarTokenException;
import br.com.HRFlow.exception.ErroAoTentarRecuperarInfoDoTokenException;
import br.com.HRFlow.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtService {
    //chaves secretas, salvas no application.properties-dev
    @Value("${key.token-secret}")
    private String tokenSecret;
    @Value("${key.token-issuer}")
    private String tokenIssuer;

    //criar um bearer token
    public String generateToken(Usuario usuario) throws ErroAoTentarCriarTokenException {
        try{
            Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
            return JWT.create()
                    .withIssuer(tokenIssuer)
                    .withSubject(usuario.getEmail())
                    .withClaim("role", usuario.getRole().name())
                    .withExpiresAt(generateExpiresDate())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new ErroAoTentarCriarTokenException("Erro ao tentar criar o bearer token");
        }
    }

    //extrai o e-mail do usuario autenticado para futuras querries
    public String getTokenSubject(String token) throws ErroAoTentarRecuperarInfoDoTokenException {
        try{
            Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
            return JWT.require(algorithm)
                    .withIssuer(tokenIssuer)
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException e){
            throw new ErroAoTentarRecuperarInfoDoTokenException("Erro ao tentar recuperar {Subjet} do token");
        }
    }

    //verifica se o bearer token repassado é valido
    public boolean isTokenValid(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
            JWT.require(algorithm)
                    .withIssuer(tokenIssuer)
                    .build()
                    .verify(token);

            return true;

        }catch (JWTVerificationException e){
            return false;
        }
    }

    private Instant generateExpiresDate() {
        //retorna um LocalDateTime com fuso horário do local da requisição
        return Instant.now().plusSeconds(7200);
    }
}
