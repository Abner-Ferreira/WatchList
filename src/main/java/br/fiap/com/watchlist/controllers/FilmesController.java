package br.fiap.com.watchlist.controllers;

import java.util.ArrayList;
import java.util.List;

import br.fiap.com.watchlist.repository.UsuarioRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.fiap.com.watchlist.models.Filmes;
import br.fiap.com.watchlist.repository.FilmeRepository;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/filme")
public class FilmesController {
    
    Logger log = LoggerFactory.getLogger(FilmesController.class);

    List<Filmes> filmes = new ArrayList<>();

    @Autowired
    FilmeRepository filmeRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    //Listar Filmes
    @GetMapping
    public Page<Filmes> index(@RequestParam(required = false) String diretor , @PageableDefault(size = 5) Pageable pageable) {
        if (diretor==null)
            return filmeRepository.findAll(pageable);
        return filmeRepository.findByDiretorContaining(diretor,pageable);
    }

    //Criar Serie
    @PostMapping
    public  ResponseEntity<Filmes> create(@RequestBody @Valid Filmes filme){
        log.info("Cadastrando filme: " + filme);
        filmeRepository.save(filme);
        filme.setUsuario(usuarioRepository.findById(filme.getUsuario().getId()).get());
        return ResponseEntity.status(HttpStatus.CREATED).body(filme);
    }

    //Detalhes Filme
    @GetMapping("{id}")
    public ResponseEntity<Filmes> show(@PathVariable Long id){
        log.info(("Buscando filme com id "+id));
        return  ResponseEntity.ok(getFilme(id));
    }

    //Apagar Filme
    @DeleteMapping("{id}")
    public ResponseEntity<Filmes> destroy(@PathVariable Long id){
        log.info("Apagando filme com id "+id);
        filmeRepository.delete(getFilme(id));
        return ResponseEntity.noContent().build();
    }

    //Atualizar Filme
    @PutMapping("{id}")
    public ResponseEntity<Filmes> update(@PathVariable Long id, @RequestBody @Valid Filmes filme){
        log.info("Alterando filme com id "+id);
        getFilme(id);
        filme.setId(id);
        filmeRepository.save(filme);

        return ResponseEntity.ok(filme);
    }

    private Filmes getFilme(Long id){
        return filmeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Filme não encontrado"));
    }
}
