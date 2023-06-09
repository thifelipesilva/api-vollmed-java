package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.domain.user.DataUser;
import med.voll.api.domain.user.User;
import med.voll.api.infra.security.DataTokenJWT;
import med.voll.api.infra.security.TokenService;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired 
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DataUser data) {
        var authenticationtoken = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var authentication = manager.authenticate(authenticationtoken);
        var tokenJWT = tokenService.tokenGenerated((User) authentication.getPrincipal());
        return ResponseEntity.ok(new DataTokenJWT(tokenJWT));
    }
}
