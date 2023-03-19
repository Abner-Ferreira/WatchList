package br.fiap.com.watchlist.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.fiap.com.watchlist.models.Filmes;


@RestController
public class FilmesController {
    
    Logger log = LoggerFactory.getLogger(FilmesController.class);

    List<Filmes> filmes = new ArrayList<>();

    //Listar Filmes
    @GetMapping("/api/filme")
    public List<Filmes> index(){

        //var filme = new Filmes("The Godfather", 2, 1, "Francis Ford Coppola", "173 min");

        return filmes;

    }

    //Criar Serie
    @PostMapping("/api/filme")
    public  ResponseEntity<Filmes> create(@RequestBody Filmes filme ){
        log.info("Cadastrando filme: " + filme);
        filme.setId(filmes.size() + 1l);
        filmes.add(filme);

        return ResponseEntity.status(HttpStatus.CREATED).body(filme);
    }

    //Detalhes Filme
    @GetMapping("/api/filme/{id}")
    public ResponseEntity<Filmes> show(@PathVariable Long id){
        log.info(("buscando filme com id "+id));
        var filmeEncontrado = filmes.stream().filter(d -> d.getId().equals(id)).findFirst();
        if (filmeEncontrado.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return  ResponseEntity.ok(filmeEncontrado.get());
    }

    //Apagar Filme
    @DeleteMapping("/api/filme/{id}")
    public ResponseEntity<Filmes> destroy(@PathVariable Long id){
        log.info("apagando filme com id "+id);
        var filmeEncontrado = filmes.stream().filter(d -> d.getId().equals(id)).findFirst();
        if(filmeEncontrado.isEmpty())
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        filmes.remove(filmeEncontrado.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //Atualizar Filme
    @PutMapping("/api/filme/{id}")
    public ResponseEntity<Filmes> update(@PathVariable Long id, @RequestBody Filmes filme){
        log.info("alterando filme com id "+id);
        var filmeEncontardo = filmes.stream().filter(d->d.getId().equals(id)).findFirst();
        if(filmeEncontardo.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        filmes.remove(filmeEncontardo.get());
        filme.setId(id);
        filmes.add(filme);

        return ResponseEntity.ok(filme);
    }
}
