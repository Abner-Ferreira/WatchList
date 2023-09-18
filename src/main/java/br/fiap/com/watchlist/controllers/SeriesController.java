package br.fiap.com.watchlist.controllers;



import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.fiap.com.watchlist.repository.UsuarioRepository;
import jakarta.validation.Valid;
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

import br.fiap.com.watchlist.models.Series;
import br.fiap.com.watchlist.repository.SerieRepository;
import org.springframework.web.server.ResponseStatusException;



@RestController
@RequestMapping("/api/serie")
public class SeriesController {
    Logger log = LoggerFactory.getLogger(SeriesController.class);

    @Autowired
    SerieRepository serieRepository;
    @Autowired
    PagedResourcesAssembler<Object> assembler;
    
    
    // Listar Series
    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String diretor, @PageableDefault(size = 5)Pageable pageable){
        Page<Series> series = (diretor==null)?
                serieRepository.findAll(pageable):
                serieRepository.findByDiretorContaining(diretor, pageable);
        return  assembler.toModel(series.map(Series::toEntityModel));
    }

    // Criar Serie
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid Series serie) {
        log.info("Cadastrando serie: " + serie);
        serieRepository.save(serie);
        return ResponseEntity
                .created(serie.toEntityModel().getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(serie.toEntityModel().getContent());
    }


    // Detalhes Serie
    @GetMapping("{id}")
    public EntityModel<Series> show(@PathVariable Long id) {
        log.info("Buscando serie com o id: " + id);
        return getSerie(id).toEntityModel();
    }


     // Apagar Serie
     @DeleteMapping("{id}")
     public ResponseEntity<Series> destroy(@PathVariable Long id) {
         log.info("Apagando usuario com id: " + id);
         serieRepository.delete(getSerie(id));
         return ResponseEntity.noContent().build();
 
     }
     //Atualizar Serie
     @PutMapping("{id}")
    public EntityModel<Series> update(@PathVariable Long id,@RequestBody @Valid Series serie){
        log.info("alterando serie com id "+id);
        getSerie(id);
        serie.setId(id);
        serieRepository.save(serie);

        return serie.toEntityModel();
     }

     private Series getSerie(Long id){
         return serieRepository.findById(id)
                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Série não encontrado"));
     }
}
