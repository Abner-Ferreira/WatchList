package br.fiap.com.watchlist.controllers;

import java.util.ArrayList;
import java.util.List;

import br.fiap.com.watchlist.repository.UsuarioRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.PagedModel;
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

    @Autowired
    FilmeRepository filmeRepository;
    @Autowired
    PagedResourcesAssembler<Object> assembler;

    //Listar Filmes
    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String diretor , @PageableDefault(size = 5) Pageable pageable) {
        Page<Filmes> filmes = (diretor == null)?
            filmeRepository.findAll(pageable):
            filmeRepository.findByDiretorContaining(diretor, pageable);
        return assembler.toModel(filmes.map(Filmes::toEntityModel));
    }

    //Criar Serie
    @PostMapping
    public  ResponseEntity<Object> create(@RequestBody @Valid Filmes filme){
        log.info("Cadastrando filme: " + filme);
        filmeRepository.save(filme);
        return ResponseEntity
                .created(filme.toEntityModel().getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(filme.toEntityModel().getContent());
    }

    //Detalhes Filme
    @GetMapping("{id}")
    public EntityModel<Filmes> show(@PathVariable Long id){
        log.info(("Buscando filme com id "+id));
        return  getFilme(id).toEntityModel();
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
    public EntityModel<Filmes> update(@PathVariable Long id, @RequestBody @Valid Filmes filme){
        log.info("Alterando filme com id "+id);
        getFilme(id);
        filme.setId(id);
        filmeRepository.save(filme);

        return filme.toEntityModel();
    }

    private Filmes getFilme(Long id){
        return filmeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Filme não encontrado"));
    }
}
