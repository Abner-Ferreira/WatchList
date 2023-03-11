package br.fiap.com.watchlist.controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fiap.com.watchlist.models.Usuario;


@RestController
public class UsuarioController {
    
    @GetMapping("/api/usuario")
    public Usuario show(){

        var usuario = new Usuario("Kleber", "https://picsum.photos/seed/picsum/200/300", "kleber@gmail.com", "+55 (11) 98765-4321");

        return usuario;

    }

    @GetMapping("/api/usuario/1")
    public Usuario details(){

        var detalhes = new Usuario("Kleber", "https://picsum.photos/seed/picsum/200/300", "kleber@gmail.com", "+55 (11) 98765-4321");

        return detalhes;
    }

    @GetMapping("/api/usuarios")
    public ArrayList list(){

        var usuarios = new ArrayList<>();

        usuarios.add(new Usuario("Kleber", "https://picsum.photos/seed/picsum/200/300", "kleber@gmail.com", "+55 (11) 98765-4321"));
        usuarios.add(new Usuario("Ubirajar", "https://picsum.photos/ubira/picsum/200/300", "ubirajar@gmail.com", "+55 (21) 97534-0159"));

        return usuarios;
    }

}
