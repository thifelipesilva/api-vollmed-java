package med.voll.api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import med.voll.api.domain.user.User;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String tokenGenerated(User user) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Voll.med")
                    .withSubject(user.getUsername())
                    .withExpiresAt(expiresAtDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("generated token failed", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                            .withIssuer("API Voll.med")
                            .build()
                            .verify(tokenJWT)
                            .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT invalid or expired!");
        }
    }

    private Instant expiresAtDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
