package med.voll.api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import med.voll.api.domain.user.User;

@Service
public class TokenService {
    public String tokenGenerated(User user) {
        try {
            var algorithm = Algorithm.HMAC256("12345678");
            return JWT.create()
                    .withIssuer("API Voll.med")
                    .withSubject(user.getUsername())
                    .withExpiresAt(expiresAtDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("generated token failed", exception);
        }
    }

    private Instant expiresAtDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
