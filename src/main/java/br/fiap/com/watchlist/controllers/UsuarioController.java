package br.fiap.com.watchlist.controllers;
import br.fiap.com.watchlist.models.Credencial;
import br.fiap.com.watchlist.models.LoginResponse;
import br.fiap.com.watchlist.models.Token;
import br.fiap.com.watchlist.models.Usuario;
import br.fiap.com.watchlist.repository.UsuarioRepository;
import br.fiap.com.watchlist.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "auth")
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    TokenService tokenService;

    @PostMapping("/api/registrar")
    public ResponseEntity<Usuario> registrar(@RequestBody @Valid Usuario usuario){
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        repository.save(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);

    }

    @PostMapping("/api/login")
    public ResponseEntity<Object> login(@RequestBody @Valid Credencial credencial){
        manager.authenticate(credencial.toAuthentication());
        Usuario usuario = repository.findByEmail(credencial.email()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Token token = tokenService.generateToken(credencial);
        var response = new LoginResponse(token, usuario);

        return ResponseEntity.ok(response);
    }

}