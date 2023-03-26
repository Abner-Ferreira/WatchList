package br.fiap.com.watchlist.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.fiap.com.watchlist.models.Filmes;
import br.fiap.com.watchlist.repository.FilmeRepository;


@RestController
@RequestMapping("/api/filme")
public class FilmesController {
    
    Logger log = LoggerFactory.getLogger(FilmesController.class);

    List<Filmes> filmes = new ArrayList<>();

    @Autowired
    FilmeRepository repository;

    //Listar Filmes
    @GetMapping
    public List<Filmes> index(){

        //var filme = new Filmes("The Godfather", 2, 1, "Francis Ford Coppola", "173 min");

        return repository.findAll();

    }

    //Criar Serie
    @PostMapping
    public  ResponseEntity<Filmes> create(@RequestBody Filmes filme ){
        log.info("Cadastrando filme: " + filme);

        repository.save(filme);

        return ResponseEntity.status(HttpStatus.CREATED).body(filme);
    }

    //Detalhes Filme
    @GetMapping("{id}")
    public ResponseEntity<Filmes> show(@PathVariable Long id){
        log.info(("Buscando filme com id "+id));
        var filmeEncontrado = repository.findById(id);
        if (filmeEncontrado.isEmpty())
            return ResponseEntity.notFound().build();
        return  ResponseEntity.ok(filmeEncontrado.get());
    }

    //Apagar Filme
    @DeleteMapping("{id}")
    public ResponseEntity<Filmes> destroy(@PathVariable Long id){
        log.info("Apagando filme com id "+id);
        var filmeEncontrado = repository.findById(id);
        if(filmeEncontrado.isEmpty())
            return  ResponseEntity.notFound().build();

        repository.delete(filmeEncontrado.get());
        return ResponseEntity.noContent().build();
    }

    //Atualizar Filme
    @PutMapping("{id}")
    public ResponseEntity<Filmes> update(@PathVariable Long id, @RequestBody Filmes filme){
        log.info("Alterando filme com id "+id);
        var filmeEncontardo = repository.findById(id);

        if(filmeEncontardo.isEmpty())
            return ResponseEntity.notFound().build();

        filme.setId(id);
        repository.save(filme);

        return ResponseEntity.ok(filme);
    }
}
