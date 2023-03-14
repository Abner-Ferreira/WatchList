package br.fiap.com.watchlist.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.fiap.com.watchlist.models.Usuario;

@RestController
public class UsuarioController {

    Logger log = LoggerFactory.getLogger(UsuarioController.class);

    List<Usuario> usuarios = new ArrayList<>();

    // Listar Usuarios
    @GetMapping("/api/usuario")
    public List<Usuario> index() {
        // var usuario = new Usuario("Kleber",
        // "https://picsum.photos/seed/picsum/200/300", "kleber@gmail.com", "+55 (11)
        // 98765-4321");
        return usuarios;
    }

    // Criar Usuario
    @PostMapping("/api/usuario")
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        log.info("Cadastrando usuario: " + usuario);
        usuario.setId(usuarios.size() + 1l);
        usuarios.add(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);

    }

    // Detalhes Usuario
    @GetMapping("/api/usuario/{id}")
    public ResponseEntity<Usuario> show(@PathVariable Long id) {
        log.info("Buscando usuario com o id: " + id);

        var usuarioEncontrado = usuarios.stream().filter(u -> u.getId().equals(id)).findFirst();

        if (usuarioEncontrado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(usuarioEncontrado.get());
    }

    // Apagar Usuario
    @DeleteMapping("/api/usuario/{id}")
    public ResponseEntity<Usuario> destroy(@PathVariable Long id) {
        log.info("Apagando usuario com id: " + id);
        var usuarioEncontrado = usuarios.stream().filter(d -> d.getId().equals(id)).findFirst();

        if (usuarioEncontrado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        usuarios.remove(usuarioEncontrado.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    // Alterar Usuario
    @PutMapping("/api/usuario/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario) {
        log.info("Alterando usuario com id: " + id);
        var usuarioEncontrado = usuarios.stream().filter(d -> d.getId().equals(id)).findFirst();

        if (usuarioEncontrado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        usuarios.remove(usuarioEncontrado.get());
        usuario.setId(id);
        usuarios.add(usuario);

        return ResponseEntity.ok(usuario);

    }
}
