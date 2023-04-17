package br.fiap.com.watchlist.controllers;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fiap.com.watchlist.models.Usuario;
import br.fiap.com.watchlist.repository.UsuarioRepository;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Slf4j
@RequestMapping("/api/usuario")
public class UsuarioController {
    List<Usuario> usuarios = new ArrayList<>();

    @Autowired
    UsuarioRepository repository;

    // Listar Usuarios
    @GetMapping
    public List<Usuario> index() {
        // var usuario = new Usuario("Kleber",
        // "https://picsum.photos/seed/picsum/200/300", "kleber@gmail.com", "+55 (11)
        // 98765-4321");
        return repository.findAll();
    }

    // Criar Usuario
    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody @Valid Usuario usuario) {
        log.info("Cadastrando usuario: " + usuario);
       
        repository.save(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);

    }

    // Detalhes Usuario
    @GetMapping("{id}")
    public ResponseEntity<Usuario> show(@PathVariable Long id) {
        log.info("Buscando usuario com o id: " + id);
        return ResponseEntity.ok(getUsuario(id));
    }

    // Apagar Usuario
    @DeleteMapping("{id}")
    public ResponseEntity<Usuario> destroy(@PathVariable Long id) {
        log.info("Apagando usuario com id: " + id);
        repository.delete(getUsuario(id));
        return ResponseEntity.noContent().build();

    }

    // Alterar Usuario
    @PutMapping("{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody @Valid Usuario usuario) {
        log.info("Alterando usuario com id: " + id);
        getUsuario(id);
        usuario.setId(id);
        repository.save(usuario);

        return ResponseEntity.ok(usuario);

    }

    private Usuario getUsuario(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuário não encontrado"));
    }
}
