package dev.elmarchas.foro_backend_Oracle_ONE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.elmarchas.foro_backend_Oracle_ONE.domain.user.DTOAuthenticationUser;
import dev.elmarchas.foro_backend_Oracle_ONE.domain.user.User;
import dev.elmarchas.foro_backend_Oracle_ONE.infra.security.DatosJWTToken;
import dev.elmarchas.foro_backend_Oracle_ONE.infra.security.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DatosJWTToken> autenticarUsuario(@RequestBody @Valid DTOAuthenticationUser dataUser) {
        System.out.println("holabb");
        Authentication authToken = new UsernamePasswordAuthenticationToken(dataUser.name(),
                dataUser.password());
        System.out.println(authToken);
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        System.out.println("USUARIOAUTENTIFICADO");
        System.out.println(usuarioAutenticado);
        var JWTtoken = tokenService.generarToken((User) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }
}
