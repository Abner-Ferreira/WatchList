package br.fiap.com.watchlist.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    UsuarioRepository repository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    // Listar Usuarios
    @GetMapping
    public CollectionModel<EntityModel<Usuario>> index() {
        List<EntityModel<Usuario>> usuarios = repository.findAll().stream()
                .map(Usuario::toEntityModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                usuarios,
                linkTo(methodOn(UsuarioController.class).index()).withSelfRel()
        );
    }


    // Criar Usuario
    @PostMapping
    public ResponseEntity<EntityModel<Usuario>> create(@RequestBody @Valid Usuario usuario) {
        repository.save(usuario);

        EntityModel<Usuario> entityModel = usuario.toEntityModel();
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }


    // Detalhes Usuario
    @GetMapping("{id}")
    public EntityModel<Usuario> show(@PathVariable Long id) {
        Usuario usuario = getUsuario(id);

        return usuario.toEntityModel();
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
    public ResponseEntity<EntityModel<Usuario>> update(@PathVariable Long id, @RequestBody @Valid Usuario usuario) {
        getUsuario(id);

        usuario.setId(id);
        repository.save(usuario);

        EntityModel<Usuario> entityModel = usuario.toEntityModel();
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }


    private Usuario getUsuario(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuário não encontrado"));
    }
}
